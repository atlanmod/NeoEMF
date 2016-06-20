package fr.inria.atlanmod.neoemf.io.input.xmi.util;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 */
public class TreePath {

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
