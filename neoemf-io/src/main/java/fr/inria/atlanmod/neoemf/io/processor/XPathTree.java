/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io.processor;

import fr.inria.atlanmod.commons.primitive.Strings;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;
import static fr.inria.atlanmod.neoemf.io.util.XPathConstants.INDEX_SEPARATOR;
import static fr.inria.atlanmod.neoemf.io.util.XPathConstants.START_ELT;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * A structure representing an XPath.
 * <p>
 * It takes the form of a tree that contains nodes: The XPath is updated for each addition/deletion of a node, and
 * only the nodes representing the current XPath are hold. This allows to treat an XPath without keeping all the
 * elements in memory.
 */
@ParametersAreNonnullByDefault
class XPathTree {

    /**
     * The root of this tree.
     * <p>
     * It does not represent the root node path.
     */
    @Nonnull
    private final Node root = new Node(Strings.EMPTY);

    /**
     * A LIFO that holds the current XPath.
     */
    @Nonnull
    private final Deque<Node> currentPath = new ArrayDeque<>();

    /**
     * Gets the XPath of the current element.
     *
     * @param name the name of the last element of the path
     *
     * @return a string representing the XPath of the element
     */
    @Nonnull
    public String path(String name) {
        checkNotNull(name, "name");

        StringBuilder str = new StringBuilder(START_ELT);
        boolean first = true;
        for (Node node : currentPath) {
            if (!first) {
                str.append(START_ELT);
            }
            str.append(node.name()).append(INDEX_SEPARATOR).append(node.index());
            first = false;
        }
        str.append(first ? Strings.EMPTY : START_ELT).append(name);
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
        Node node = currentPath.isEmpty() ? root : currentPath.getLast();

        if (node.hasChild(name)) {
            // Try to get and increment the node if it exists
            node = node.child(name);
            node.incrementIndex();
        }
        else {
            // The node doesn't exist : we create him
            node = node.child(new Node(name));
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
     * A node of the {@link XPathTree}.
     * <p>
     * A node is a segment of an XPath, for example {@code .../@name.index/...}.
     */
    @ParametersAreNonnullByDefault
    private static class Node {

        /**
         * The name of this node.
         */
        @Nonnull
        private final String name;

        /**
         * A map that holds all children of this node, identified by their name.
         */
        @Nonnull
        private final Map<String, Node> children = new HashMap<>();

        /**
         * The index of this node.
         */
        @Nonnull
        private final AtomicInteger index = new AtomicInteger();

        /**
         * Constructs a new {@code Node} with the given {@code name}.
         *
         * @param name the name of this node
         */
        public Node(String name) {
            this.name = checkNotNull(name, "name");
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
        public Node child(String id) {
            Node child = children.get(id);
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
        public Node child(Node child) {
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
    }
}
