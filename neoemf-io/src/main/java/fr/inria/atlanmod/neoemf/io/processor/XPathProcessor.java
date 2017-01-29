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

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import fr.inria.atlanmod.neoemf.io.structure.RawClassifier;
import fr.inria.atlanmod.neoemf.io.structure.RawIdentifier;
import fr.inria.atlanmod.neoemf.io.structure.RawReference;
import fr.inria.atlanmod.neoemf.util.logging.NeoLogger;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * A {@link Processor} that analyses XML elements in order to create and to process XPath references.
 */
public class XPathProcessor extends AbstractProcessor {

    /**
     * The string representing the start of a XPath expression.
     */
    private static final String XPATH_START_EXPR = "//@";

    /**
     * The string representing the start of a XPath segment.
     */
    private static final String XPATH_START_ELT = "/@";

    /**
     * The string representing the end of a XPath segment.
     */
    private static final String XPATH_END_EXPR = "/";

    /**
     * The character used as separator between the name and the index of a XPath segment.
     */
    private static final String XPATH_INDEX_SEPARATOR = ".";

    /**
     * Pattern for detecting nodes which have no index in their path.
     * <p>
     * <b>Example:</b> {@code .../@name/...} instead of {@code .../@name.0/...}
     */
    private static final Pattern PATTERN_NODE_WITHOUT_INDEX =
            Pattern.compile("((@\\w+)(?!\\.\\d+)(/|\\z))", Pattern.UNICODE_CASE);

    /**
     * The XPath structure.
     */
    private final XPathTree paths;

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
     * Constructs a new {@code XPathProcessor} on the given {@code processor}.
     *
     * @param processor the underlying processor
     */
    public XPathProcessor(Processor processor) {
        super(processor);
        this.paths = new XPathTree();
        this.hasIds = false;
    }

    @Override
    public void processStartElement(RawClassifier classifier) {
        if (nonNull(classifier.id())) {
            hasIds = true;
        }

        if (!hasIds) {
            // Processes the id from the path of the element in XML tree
            String path = paths.getPath(classifier.localName());

            // Increments the number of occurrence for this path
            Integer count = paths.createOrIncrement(classifier.localName());

            // Defines the id as '<path>.<index>'
            String id = path + XPATH_INDEX_SEPARATOR + count;

            // Defines the XPath start of all elements from the root element
            if (isNull(expressionStart)) {
                expressionStart = id + XPATH_START_ELT;
            }

            // Defines the new identifier as identifier of the classifier if it not already exist
            if (isNull(classifier.id())) {
                classifier.id(RawIdentifier.generated(id));
            }
        }

        super.processStartElement(classifier);
    }

    @Override
    public void processReference(RawReference reference) {
        if (!hasIds) {
            // Format the reference according internal XPath management
            reference.idReference(RawIdentifier.generated(formatPath(reference.idReference().value())));
        }

        super.processReference(reference);
    }

    @Override
    public void processEndElement() {
        if (!hasIds) {
            // Removes children of the last element
            paths.clearLast();
        }

        super.processEndElement();
    }

    @Override
    public void processEndDocument() {
        if (!hasIds) {
            long size = paths.estimatedSize();
            if (size > 1) {
                NeoLogger.warn("Some elements have not been cleaned ({0})", size);
            }
        }

        super.processEndDocument();
    }

    /**
     * Formats an XPath expression to take the following form: {@code .../@name.index/@name2.index2}
     *
     * @param path the path to format
     *
     * @return the formatted XPath
     */
    private String formatPath(String path) {
        // Replace the start of the given reference "//@" -> "/@<rootname>.<index>"
        String modifiedReference = path.replaceFirst(XPATH_START_EXPR, expressionStart);

        // Replace elements which has not index : all elements must have an index (default = 0)
        Matcher matcher = PATTERN_NODE_WITHOUT_INDEX.matcher(modifiedReference);
        while (matcher.find()) {
            modifiedReference = matcher.replaceAll("$2.0$3");
        }

        // Remove the latest '/' character if present
        if (modifiedReference.endsWith(XPATH_END_EXPR)) {
            modifiedReference = modifiedReference.substring(0, modifiedReference.length() - XPATH_END_EXPR.length());
        }

        return modifiedReference;
    }

    /**
     * A structure representing an XPath.
     * <p>
     * It takes the form of a tree that contains nodes: The XPath is updated for each addition/deletion of a node, and
     * only the nodes representing the current XPath are hold. This allows to treat an XPath without keeping all the
     * elements in memory.
     */
    private static class XPathTree {

        /**
         * The root of this tree.
         *
         * @note It does not represent the root node path.
         */
        private final XPathNode dummyRoot;

        /**
         * A queue holding the current XPath.
         */
        private final Deque<XPathNode> currentPath;

        /**
         * Constructs a new {@code XPathTree}.
         */
        public XPathTree() {
            this.dummyRoot = new XPathNode("ROOT");
            this.currentPath = new ArrayDeque<>();
        }

        /**
         * Gets the XPath of the current element.
         *
         * @param name the name of the last element of the path
         *
         * @return a string representing the XPath of the element
         */
        public String getPath(String name) {
            checkNotNull(name);
            StringBuilder str = new StringBuilder(XPATH_START_ELT);
            boolean first = true;
            for (XPathNode node : currentPath) {
                if (!first) {
                    str.append(XPATH_START_ELT);
                }
                str.append(node.getName()).append(XPATH_INDEX_SEPARATOR).append(node.getIndex());
                first = false;
            }
            str.append(first ? "" : XPATH_START_ELT).append(name);
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
            XPathNode node = currentPath.isEmpty() ? dummyRoot : currentPath.getLast();

            if (node.hasChild(name)) {
                // Try to get and increment the node if it exists
                node = node.getChild(name);
                node.incrementIndex();
            }
            else {
                // The node doesn't exist : we create him
                node = node.addChild(new XPathNode(name));
            }
            // Define the node as the current node in path
            currentPath.addLast(node);
            return node.getIndex();
        }

        /**
         * Removes the last child and, recursively its children, from this tree.
         */
        public void clearLast() {
            currentPath.removeLast().removeChildren();
        }

        /**
         * Returns the approximate number of node in this tree.
         *
         * @return an approximate size
         */
        public long estimatedSize() {
            return dummyRoot.estimatedSize();
        }

        /**
         * A node of the {@link XPathTree}.
         * <p>
         * A node is a segment of an XPath, for example {@code .../@name.index/...}.
         */
        private static class XPathNode {

            /**
             * The name of this node.
             */
            private final String name;
            /**
             * In-memory cache that holds all children of this node, identified by their name.
             */
            private final Cache<String, XPathNode> childrenCache;
            /**
             * The index of this node.
             */
            private int index;

            /**
             * Constructs a new {@code XPathNode} with the given {@code name}.
             *
             * @param name the name of this node
             */
            public XPathNode(String name) {
                this.name = name;
                this.index = 0;
                this.childrenCache = Caffeine.newBuilder().build();
            }

            /**
             * Returns the name of this node.
             *
             * @return the name
             */
            public String getName() {
                return name;
            }

            /**
             * Returns the index of this node.
             *
             * @return the index
             */
            public int getIndex() {
                return index;
            }

            /**
             * Increments the index of this node.
             */
            public void incrementIndex() {
                index++;
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
            public XPathNode getChild(@Nonnull String id) {
                XPathNode child = childrenCache.getIfPresent(id);
                if (isNull(child)) {
                    throw new NoSuchElementException("No such element '" + id + "' in the element '" + this.name + "'");
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
            public XPathNode addChild(@Nonnull XPathNode child) {
                childrenCache.put(child.getName(), child);
                return child;
            }

            /**
             * Removes all children of this node.
             */
            public void removeChildren() {
                childrenCache.invalidateAll();
            }

            /**
             * Defines whether this node has a child with the specified {@code name}.
             *
             * @param name the name of the sought child
             *
             * @return {@code true} if a child node has the specified {@code name}
             */
            public boolean hasChild(@Nonnull String name) {
                return nonNull(childrenCache.getIfPresent(name));
            }

            /**
             * Returns the approximate number of children in this node. It includes the number of children of this node,
             * and, recursively, of its children.
             *
             * @return an approximate size
             */
            @Nonnegative
            public long estimatedSize() {
                return childrenCache.estimatedSize() + childrenCache.asMap().values().stream().mapToLong(XPathNode::estimatedSize).sum();
            }
        }
    }
}
