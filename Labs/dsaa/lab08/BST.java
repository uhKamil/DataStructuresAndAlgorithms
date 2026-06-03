package dsaa.lab08;

public class BST<T> {
    private class Node {
        T value;
        Node left, right, parent;

        public Node(T v) {
            value = v;
        }

        public Node(T value, Node left, Node right, Node parent) {
            super();
            this.value = value;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }

    private Node root = null;
    private int size = 0;

    public BST() {
    }

    private Node getNode(T value) {
        Node current = root;
        @SuppressWarnings("unchecked")
        Comparable<T> comp = (Comparable<T>) value;

        while (current != null) {
            int cmp = comp.compareTo(current.value);
            if (cmp == 0) {
                return current;
            } else if (cmp < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return null;
    }

    public T getElement(T toFind) {
        Node node = getNode(toFind);
        return node != null ? node.value : null;
    }

    public T successor(T elem) {
        Node targetNode = getNode(elem);

        if (targetNode == null) {
            return null;
        }

        // right subtree exists
        if (targetNode.right != null) {
            Node succ = targetNode.right;
            while (succ.left != null) {
                succ = succ.left;
            }
            return succ.value;
        }

        // right subtree doesn't exist
        Node parent = targetNode.parent;
        Node child = targetNode;

        while (parent != null && child == parent.right) {
            child = parent;
            parent = parent.parent;
        }

        if (parent == null) {
            return null;
        }

        return parent.value;
    }

    public String toStringInOrder() {
        StringBuilder sb = new StringBuilder();
        inOrder(root, sb);

        if (sb.length() >= 2) sb.setLength(sb.length() - 2);
        return sb.toString();
    }

    private void inOrder(Node node, StringBuilder sb) {
        if (node != null) {
            inOrder(node.left, sb);
            sb.append(node.value.toString()).append(", ");
            inOrder(node.right, sb);
        }
    }

    public String toStringPreOrder() {
        StringBuilder sb = new StringBuilder();
        preOrder(root, sb);

        if (sb.length() >= 2) sb.setLength(sb.length() - 2);
        return sb.toString();
    }

    private void preOrder(Node node, StringBuilder sb) {
        if (node != null) {
            sb.append(node.value.toString()).append(", ");
            preOrder(node.left, sb);
            preOrder(node.right, sb);
        }
    }

    public String toStringPostOrder() {
        StringBuilder sb = new StringBuilder();
        postOrder(root, sb);

        if (sb.length() >= 2) sb.setLength(sb.length() - 2);
        return sb.toString();
    }

    private void postOrder(Node node, StringBuilder sb) {
        if (node != null) {
            postOrder(node.left, sb);
            postOrder(node.right, sb);
            sb.append(node.value.toString()).append(", ");
        }
    }

    public boolean add(T elem) {
        if (root == null) {
            root = new Node(elem);
            size++;
            return true;
        }

        Node current = root;
        Node parent = null;
        @SuppressWarnings("unchecked")
        Comparable<T> comp = (Comparable<T>) elem;

        while (current != null) {
            parent = current;
            int cmp = comp.compareTo(current.value);

            if (cmp == 0) {
                return false;
            } else if (cmp < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        Node newNode = new Node(elem, null, null, parent);
        if (comp.compareTo(parent.value) < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }

        size++;
        return true;
    }

    public T remove(T value) {
        Node targetNode = getNode(value);

        if (targetNode == null) {
            return null;
        }

        T removedValue = targetNode.value;

        // two children
        if (targetNode.left != null && targetNode.right != null) {
            Node successorNode = targetNode.right;

            while (successorNode.left != null) {
                successorNode = successorNode.left;
            }

            targetNode.value = successorNode.value;
            targetNode = successorNode;
        }

        Node child = (targetNode.left != null) ? targetNode.left : targetNode.right;

        if (child != null) {
            child.parent = targetNode.parent;
        }

        if (targetNode.parent == null) {
            root = child;
        } else if (targetNode == targetNode.parent.left) {
            targetNode.parent.left = child;
        } else {
            targetNode.parent.right = child;
        }

        size--;
        return removedValue;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }
}
