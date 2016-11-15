/*
 * Copyright (c) 2013-2016 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.io.internal.impl;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import fr.inria.atlanmod.neoemf.io.beans.Classifier;
import fr.inria.atlanmod.neoemf.io.beans.Identifier;
import fr.inria.atlanmod.neoemf.io.beans.Reference;
import fr.inria.atlanmod.neoemf.io.internal.InternalHandler;
import fr.inria.atlanmod.neoemf.logging.NeoLogger;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Objects.isNull;

/**
 * An {@link InternalHandler internal handler} that analyses XML elements in order to create and to process XPath
 * references.
 */
public class XPathDelegatedInternalHandler extends AbstractDelegatedInternalHandler {

    private static final String XPATH_START_EXPR = "//@";
    private static final String XPATH_END_EXPR = "/";
    private static final String XPATH_START_ELT = "/@";
    private static final String XPATH_INDEX_SEPARATOR = ".";

    /**
     * Pattern for detecting nodes which have no index in their path.
     * <p/>
     * Example : {@code .../@nodename/...} instead of {@code .../@nodename.0/...}
     */
    private static final Pattern PATTERN_NODE_WITHOUT_INDEX =
            Pattern.compile("((@\\w+)(?!\\.\\d+)(/|\\z))", Pattern.UNICODE_CASE);

    private final TreePath paths;

    /**
     * The start of an XPath expression in this {@code XPathDelegatedInternalHandler}.
     * This variable is necessary to replace the malformed XPath reference in XMI files
     */
    private String expressionStart;

    private boolean hasIds;

    public XPathDelegatedInternalHandler(InternalHandler handler) {
        super(handler);
        this.paths = new TreePath();
        this.hasIds = false;
    }

    @Override
    public void handleStartElement(Classifier classifier) throws Exception {
        if (!isNull(classifier.getId())) {
            hasIds = true;
        }

        if (!hasIds) {
            // Processes the id from the path of the element in XML tree
            String path = paths.getPath(classifier.getLocalName());

            // Increments the number of occurence for this path
            Integer count = paths.createOrIncrement(classifier.getLocalName());

            // Defines the id as '<path>.<index>'
            String id = path + XPATH_INDEX_SEPARATOR + count;

            // Defines the XPath start of all elements from the root element
            if (isNull(expressionStart)) {
                expressionStart = id + XPATH_START_ELT;
            }

            // Defines the new identifier as identifier of the classifier if it not already exist
            if (isNull(classifier.getId())) {
                classifier.setId(Identifier.generated(id));
            }
        }

        super.handleStartElement(classifier);
    }

    @Override
    public void handleReference(Reference reference) throws Exception {
        if (!hasIds) {
            // Format the reference according internal XPath management
            reference.setIdReference(Identifier.generated(formatPath(reference.getIdReference().getValue())));
        }

        super.handleReference(reference);
    }

    @Override
    public void handleEndElement() throws Exception {
        if (!hasIds) {
            // Removes children of the last element
            paths.clearLast();
        }

        super.handleEndElement();
    }

    @Override
    public void handleEndDocument() throws Exception {
        if (!hasIds) {
            long size = paths.size();
            if (size > 1) {
                NeoLogger.warn("Some elements have not been cleaned ({0})", size);
            }
        }

        super.handleEndDocument();
    }

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

    private static class TreePath {

        /**
         * The root of this tree. This does not represent the root node path.
         */
        private final Node dummyRoot;
        private final Deque<Node> currentPath;

        public TreePath() {
            this.dummyRoot = new Node("ROOT");
            this.currentPath = new ArrayDeque<>();
        }

        /**
         * Gets the XPath of the current element.
         *
         * @param name the last element of the path
         *
         * @return a String representing the XPath of the element
         */
        public String getPath(String name) {
            checkNotNull(name);
            StringBuilder str = new StringBuilder(XPATH_START_ELT);
            boolean first = true;
            for (Node node : currentPath) {
                if (!first) {
                    str.append(XPATH_START_ELT);
                }
                str.append(node.getKey()).append(XPATH_INDEX_SEPARATOR).append(node.getValue());
                first = false;
            }
            str.append(first ? "" : XPATH_START_ELT).append(name);
            return str.toString();
        }


        public Integer createOrIncrement(String name) {
            // Get the current Node or the root Node if no element exists
            Node node = currentPath.isEmpty() ? dummyRoot : currentPath.getLast();

            if (node.hasChild(name)) {
                // Try to get and increment the node if it exists
                node = node.getChild(name);
                node.incrementValue();
            }
            else {
                // The node doesn't exist : we create him
                node = node.addChild(new Node(name));
            }
            // Define the node as the current node in path
            currentPath.addLast(node);
            return node.getValue();
        }

        public void clearLast() {
            currentPath.removeLast().removeChildren();
        }

        public long size() {
            return dummyRoot.size();
        }

        private static class Node {

            private final String key;
            private final Cache<String, Node> children;

            private Integer value;

            public Node(String key) {
                this.key = key;
                this.value = 0;
                this.children = Caffeine.newBuilder().build();
            }

            public String getKey() {
                return key;
            }

            public Integer getValue() {
                return value;
            }

            public void incrementValue() {
                value++;
            }

            public Node getChild(String key) {
                Node child = children.getIfPresent(key);
                if (isNull(child)) {
                    throw new NoSuchElementException("No such element '" + key + "' in the element '" + this.key + "'");
                }
                return child;
            }

            public Node addChild(Node child) {
                children.put(child.getKey(), child);
                return child;
            }

            public void removeChildren() {
                children.invalidateAll();
            }

            public boolean hasChild(String key) {
                return !isNull(children.getIfPresent(key));
            }

            public long size() {
                long size = children.estimatedSize();
                for (Node child : children.asMap().values()) {
                    size += child.size();
                }
                return size;
            }
        }
    }
}
