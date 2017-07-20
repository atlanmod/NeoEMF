/*
 * Copyright (c) 2013-2017 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.io.processor;

import fr.inria.atlanmod.common.Strings;
import fr.inria.atlanmod.common.log.Log;
import fr.inria.atlanmod.neoemf.io.Handler;
import fr.inria.atlanmod.neoemf.io.bean.BasicElement;
import fr.inria.atlanmod.neoemf.io.bean.BasicId;
import fr.inria.atlanmod.neoemf.io.bean.BasicReference;
import fr.inria.atlanmod.neoemf.io.util.XPathConstants;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.RegEx;

import static fr.inria.atlanmod.common.Preconditions.checkNotNull;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * A {@link Processor} that analyses XML elements in order to create and to process XPath references.
 */
@ParametersAreNonnullByDefault
public class XPathProcessor extends AbstractProcessor<Handler> {

    /**
     * Regular expression for detecting nodes which have no index in their path.
     */
    @RegEx
    private static final String REGEX_NO_INDEX = "((@\\w+)(?!\\.\\d+)(/|\\z))";

    /**
     * Pattern for detecting nodes which have no index in their path.
     * <p>
     * <b>Example:</b> {@code .../@name/...} instead of {@code .../@name.0/...}
     */
    private static final Pattern PATTERN_NO_INDEX = Pattern.compile(REGEX_NO_INDEX, Pattern.UNICODE_CASE);

    /**
     * The XPath structure.
     */
    private final XPathTree paths = new XPathTree();

    /**
     * The start of an XPath expression in this {@link XPathProcessor}.
     * <p>
     * This variable is necessary to replace the malformed XPath reference in XMI files
     */
    private String expressionStart;

    /**
     * Defines whether the processed document already contains identifiers. In this case, XPath processing is
     * unnecessary and this processor simply notifies the underlying processor.
     */
    private boolean hasIds;

    /**
     * Constructs a new {@code XPathProcessor} with the given {@code handler}.
     *
     * @param handler the handler to notify
     */
    public XPathProcessor(Handler handler) {
        super(handler);
    }

    @Override
    public void onStartElement(BasicElement element) {
        // If the first element has an identifier, we assume that the file is ID-based.
        if (nonNull(element.id())) {
            hasIds = true;
        }

        if (!hasIds) {
            // Processes the id from the path of the element in XML tree
            String path = paths.path(element.name());

            // Increments the number of occurrence for this path
            int count = paths.createOrIncrement(element.name());

            // Defines the id as '<path>.<index>'
            String id = path + XPathConstants.INDEX_SEPARATOR + count;

            // Defines the XPath start of all elements from the root element
            if (isNull(expressionStart)) {
                expressionStart = id + XPathConstants.START_ELT;
            }

            // Defines the new identifier as identifier of the classifier if it not already exist
            if (isNull(element.id())) {
                element.id(BasicId.generated(id));
            }
        }

        notifyStartElement(element);
    }

    @Override
    public void onReference(BasicReference reference) {
        if (!hasIds) {
            // Format the reference according internal XPath management
            reference.idReference(BasicId.generated(formatPath(reference.idReference().value())));
        }

        notifyReference(reference);
    }

    @Override
    public void onEndElement() {
        if (!hasIds) {
            // Removes children of the last element
            paths.clearLast();
        }

        notifyEndElement();
    }

    @Override
    public void onComplete() {
        if (!hasIds) {
            long size = paths.size();
            if (size > 1) {
                Log.warn("Some elements have not been cleaned ({0})", size);
            }
        }

        notifyComplete();
    }

    /**
     * Formats an XPath expression to take the following form: {@code .../@name.index/@name2.index2}
     *
     * @param path the path to format
     *
     * @return the formatted XPath
     */
    @Nonnull
    private String formatPath(String path) {
        // Replace the start of the given reference "//@" -> "/@<rootname>.<index>"
        String updatedReference = path.replaceFirst(XPathConstants.START_EXPR, expressionStart);

        // Replace elements which has not index : all elements must have an index (default = 0)
        Matcher matcher = PATTERN_NO_INDEX.matcher(updatedReference);
        while (matcher.find()) {
            updatedReference = matcher.replaceAll("$2.0$3");
        }

        // Remove the latest '/' character if present
        if (updatedReference.endsWith(XPathConstants.END_EXPR)) {
            updatedReference = updatedReference.substring(0, updatedReference.length() - XPathConstants.END_EXPR.length());
        }

        return updatedReference;
    }

    /**
     * A structure representing an XPath.
     * <p>
     * It takes the form of a tree that contains nodes: The XPath is updated for each addition/deletion of a node, and
     * only the nodes representing the current XPath are hold. This allows to treat an XPath without keeping all the
     * elements in memory.
     */
    @ParametersAreNonnullByDefault
    private static class XPathTree {

        /**
         * The root of this tree.
         * <p>
         * It does not represent the root node path.
         */
        @Nonnull
        private final XPathNode root = new XPathNode(Strings.EMPTY);

        /**
         * A LIFO that holds the current XPath.
         */
        @Nonnull
        private final Deque<XPathNode> currentPath = new ArrayDeque<>();

        /**
         * Gets the XPath of the current element.
         *
         * @param name the name of the last element of the path
         *
         * @return a string representing the XPath of the element
         */
        @Nonnull
        public String path(String name) {
            checkNotNull(name);

            StringBuilder str = new StringBuilder(XPathConstants.START_ELT);
            boolean first = true;
            for (XPathNode node : currentPath) {
                if (!first) {
                    str.append(XPathConstants.START_ELT);
                }
                str.append(node.name()).append(XPathConstants.INDEX_SEPARATOR).append(node.index());
                first = false;
            }
            str.append(first ? Strings.EMPTY : XPathConstants.START_ELT).append(name);
            return str.toString();
        }

        /**
         * Creates or increments the node with the specified {@code name}. If a child node has already the {@code name},
         * its index will be incremented, otherwise a new child will be added with {@code index = 0}.
         *
         * @param name the name of the node
         *
         * @return the index of the node
         */
        @Nonnegative
        public int createOrIncrement(String name) {
            // Get the current Node or the root Node if no element exists
            XPathNode node = currentPath.isEmpty() ? root : currentPath.getLast();

            if (node.hasChild(name)) {
                // Try to get and increment the node if it exists
                node = node.child(name);
                node.incrementIndex();
            }
            else {
                // The node doesn't exist : we create him
                node = node.child(new XPathNode(name));
            }

            // Define the node as the current node in path
            currentPath.addLast(node);
            return node.index();
        }

        /**
         * Removes the last child and, recursively its children, from this tree.
         */
        public void clearLast() {
            currentPath.removeLast().clear();
        }

        /**
         * Returns the approximate number of node in this tree.
         *
         * @return an approximate size
         */
        public long size() {
            return root.size();
        }

        /**
         * A node of the {@link XPathTree}.
         * <p>
         * A node is a segment of an XPath, for example {@code .../@name.index/...}.
         */
        @ParametersAreNonnullByDefault
        private static class XPathNode {

            /**
             * The name of this node.
             */
            @Nonnull
            private final String name;

            /**
             * A map that holds all children of this node, identified by their name.
             */
            @Nonnull
            private final Map<String, XPathNode> children = new HashMap<>();

            /**
             * The index of this node.
             */
            @Nonnull
            private final AtomicInteger index = new AtomicInteger();

            /**
             * Constructs a new {@code XPathNode} with the given {@code name}.
             *
             * @param name the name of this node
             */
            public XPathNode(String name) {
                this.name = checkNotNull(name);
            }

            /**
             * Returns the name of this node.
             *
             * @return the name
             */
            @Nonnull
            public String name() {
                return name;
            }

            /**
             * Returns the index of this node.
             *
             * @return the index
             */
            @Nonnegative
            public int index() {
                return index.get();
            }

            /**
             * Increments the index of this node.
             */
            public void incrementIndex() {
                index.incrementAndGet();
            }

            /**
             * Returns the node which has the specified {@code name} among the children of this node.
             *
             * @param id he identifier of the sought child
             *
             * @return the child node if it exists
             *
             * @throws NoSuchElementException if no child has the given {@code name}
             */
            @Nonnull
            public XPathNode child(String id) {
                XPathNode child = children.get(id);
                if (isNull(child)) {
                    throw new NoSuchElementException("No such element '" + id + "' in the element '" + this.name + '\'');
                }
                return child;
            }

            /**
             * Adds a {@code child} to this node.
             *
             * @param child the node to add as child
             *
             * @return the given {@code child} (for chaining)
             */
            @Nonnull
            public XPathNode child(XPathNode child) {
                children.put(child.name(), child);
                return child;
            }

            /**
             * Removes all children of this node.
             */
            public void clear() {
                children.clear();
            }

            /**
             * Defines whether this node has a child with the specified {@code name}.
             *
             * @param name the name of the sought child
             *
             * @return {@code true} if a child node has the specified {@code name}
             */
            public boolean hasChild(String name) {
                return nonNull(children.get(name));
            }

            /**
             * Returns the approximate number of children in this node. It includes the number of children of this node,
             * and, recursively, of its children.
             *
             * @return an approximate size
             */
            @Nonnegative
            public long size() {
                return children.size() + children.values().stream()
                        .mapToLong(XPathNode::size)
                        .sum();
            }
        }
    }
}
