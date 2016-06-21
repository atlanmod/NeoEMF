/*
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.io.input.xmi;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import fr.inria.atlanmod.neoemf.io.impl.AbstractInternalHandler;
import fr.inria.atlanmod.neoemf.logger.NeoLogger;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 */
public class XmiHandler extends AbstractInternalHandler {

    private static final String XPATH_START_EXPR = "//@";
    private static final String XPATH_END_EXPR = "/";
    private static final String XPATH_START_ELT = "/@";
    private static final String XPATH_INDEX_SEPARATOR = ".";

    /**
     * Pattern for detecting nodes which have no index in their path.
     * <p/>
     * Example : {@code .../@nodename/...} instead of {@code .../@nodename.0/...}
     */
    private static final Pattern PATTERN_NODE_WITHOUT_INDEX = Pattern.compile("((@\\w+)(?!\\.\\d+)(\\/|\\z))");

    private final TreePath paths;

    /**
     * The start of an XPath expression in this {@code XmiHandler}.
     * This variable is necessary to replace the malformed XPath reference in XMI files
     */
    private String expressionStart;

    public XmiHandler() {
        this.paths = new TreePath();
    }

    @Override
    public void handleStartDocument() throws Exception {
        super.handleStartDocument();
    }

    @Override
    public void handleStartElement(String prefix, String namespace, String localName, String reference) throws Exception {
        String path = paths.getPath(localName);

        // Increments the number of occurence for this path
        Integer count = paths.createOrIncrement(localName);

        if (expressionStart == null) {
            expressionStart = path + XPATH_INDEX_SEPARATOR + count + XPATH_START_ELT;
        }

        super.handleStartElement(prefix, namespace, localName, path + XPATH_INDEX_SEPARATOR + count);
    }

    @Override
    public void handleReference(String namespace, String localName, String reference) throws Exception {
        super.handleReference(namespace, localName, formatPath(reference));
    }

    @Override
    public void handleEndElement() throws Exception {
        paths.clearLast();

        super.handleEndElement();
    }

    @Override
    public void handleEndDocument() throws Exception {
        long size = paths.size();
        if (size > 1) {
            NeoLogger.warn("Some elements have not been cleaned ({0})", size);
        }

        super.handleEndDocument();
    }

    private String formatPath(String path) {
        // Replace the start of the given reference "//@" -> "/@<rootname>.<index>"
        String modifiedReference = path.replaceFirst(XPATH_START_EXPR, expressionStart);

        // Replace elements which has not index
        Matcher matcher = PATTERN_NODE_WITHOUT_INDEX.matcher(modifiedReference);
        while(matcher.find()) {
            modifiedReference = matcher.replaceAll("$2.0$3");
        }

        // Remove the latest '/' character if present
        if (modifiedReference.endsWith(XPATH_END_EXPR)) {
            modifiedReference = modifiedReference.substring(0, modifiedReference.length() - XPATH_END_EXPR.length());
        }

        return modifiedReference;
    }

    private static class TreePath {
        private static final String XPATH_START_ELT = "/@";
        private static final String XPATH_INDEX_SEPARATOR = ".";

        /**
         * The root of this tree. This does not represent the root node path.
         */
        private Node dummyRoot;
        private Deque<Node> currentPath;

        public TreePath() {
            this.dummyRoot = new Node("ROOT");
            this.currentPath = new ArrayDeque<>();
        }

        /**
         * Gets the XPath of the current element.
         * @param localName the last element of the path
         * @return a String representing the XPath of the element
         */
        public String getPath(String localName) {
            checkNotNull(localName);
            StringBuilder str = new StringBuilder(XPATH_START_ELT);
            boolean first = true;
            for (Node node : currentPath) {
                if (!first) {
                    str.append(XPATH_START_ELT);
                }
                str.append(node.getKey()).append(XPATH_INDEX_SEPARATOR).append(node.getValue());
                first = false;
            }
            str.append(first ? "" : XPATH_START_ELT).append(localName);
            return str.toString();
        }


        public Integer createOrIncrement(String localName) {
            // Get the current Node or the root Node if no element exists
            Node node = currentPath.isEmpty() ? dummyRoot : currentPath.getLast();

            if (node.hasChild(localName)) {
                // Try to get and increment the node if it exists
                node = node.getChild(localName);
                node.incrementValue();
            } else {
                // The node doesn't exist : we create him
                node = node.addChild(new Node(localName));
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

            private String key;
            private Integer value;

            private Cache<String, Node> children;

            public Node(String key) {
                this.key = key;
                this.value = 0;
                this.children = CacheBuilder.newBuilder().build();
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
                if (child == null) {
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
                return children.getIfPresent(key) != null;
            }

            public long size() {
                long size = children.size();
                for (Node child : children.asMap().values()) {
                    size += child.size();
                }
                return size;
            }
        }
    }
}
