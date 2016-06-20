package fr.inria.atlanmod.neoemf.io.input.xmi.util;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 *
 */
public class TreePath {

    private static final String XPATH_START_ELT = "/@";
    private static final String XPATH_INDEX_SEPARATOR = ".";
    private static final String PATTERN_INDEX_SEPARATOR = "\\.";

    /**
     * The root of this tree. This does not represent the root node path.
     */
    private Node dummyRoot;
    private Deque<Node> currentPath;

    public TreePath() {
        this.dummyRoot = new Node("ROOT");
        this.currentPath = new ArrayDeque<>();
    }

    public String getPath(String localName) {
        StringBuilder str = new StringBuilder(XPATH_START_ELT);
        boolean first = true;
        for (Node node : currentPath) {
            if (!first) {
                str.append(XPATH_START_ELT);
            }
            str.append(node.getKey()).append(XPATH_INDEX_SEPARATOR).append(node.getValue());
            first = false;
        }
        if (localName != null) {
            str.append(first ? "" : XPATH_START_ELT).append(localName);
        }
        return str.toString();
    }

    public Integer createOrIncrement(String path) {
        Node node;
        try {
            // Try to get and increment the node if it exists
            node = getNode(path);
            node.incrementValue();
        }
        catch (NoSuchElementException e) {
            // The node doesn't exist : we create him
            node = getParentNode(path).addChild(new Node(getLastKey(path)));
        }
        currentPath.addLast(node);
        return node.getValue();
    }

    public void clearLast() {
        currentPath.removeLast().removeChildren();
    }

    private Node getNode(String path) {
        Node node = dummyRoot;
        for (String key : getKeys(path)) {
            node = node.getChild(key);
        }
        return node;
    }

    private Node getParentNode(String path) {
        Node node = dummyRoot;
        Deque<String> keys = getKeys(path);
        keys.removeLast();
        for (String key : keys) {
            node = node.getChild(key);
        }
        return node;
    }

    private String getLastKey(String path) {
        return getKeys(path).getLast();
    }

    private Deque<String> getKeys(String path) {
        String[] keys = path.split(XPATH_START_ELT);
        Deque<String> returnKeys = new ArrayDeque<>();
        for (String key : keys) {
            if (!key.trim().isEmpty()) {
                if (key.contains(XPATH_INDEX_SEPARATOR)) {
                    key = key.split(PATTERN_INDEX_SEPARATOR)[0];
                }
                returnKeys.addLast(key.trim());
            }
        }
        return returnKeys;
    }

    public int size() {
        return dummyRoot.size();
    }

    private static class Node {

        private String key;
        private Integer value;

        private Map<String, Node> children;

        public Node(String key) {
            this.key = key;
            this.value = 0;
            this.children = new HashMap<>();
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
            if (children.containsKey(key)) {
                return children.get(key);
            } else {
                throw new NoSuchElementException("No such element '" + key + "' in the element '" + this.key + "'");
            }
        }

        public Node addChild(Node child) {
            children.put(child.getKey(), child);
            return child;
        }

        public void removeChildren() {
            children.clear();
        }

        public int size() {
            int size = children.size();
            for (Node child : children.values()) {
                size += child.size();
            }
            return size;
        }
    }
}
