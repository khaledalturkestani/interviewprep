package com.khaled.bst;

/**
 * Created by khaledalturkestani on 7/13/17.
 */
public class BST<T> {

    public class Node {
        private T value;
        private Node left;
        private Node right;

        public Node(T value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }

    private Node root;

    public BST() {}

    public void insert(T value) {
        Node newNode = new Node(value);
        if (root == null) {
            root = newNode;
            return;
        }

        Node node = root;
        Comparable<? super T> key = (Comparable<? super T>) value;
        while(true) {
            if (key.compareTo(node.getValue()) > 0) {
                if (node.getRight() == null) {
                    node.setRight(newNode);
                    break;
                } else {
                    node = node.getRight();
                }
            } else {
                if (node.getLeft() == null) {
                    node.setLeft(newNode);
                    break;
                } else {
                    node = node.getLeft();
                }
            }
        }
    }

    public Node search(T value) {
        if (root == null)
            return null;

        Comparable<? super T> key = (Comparable<? super T>) value;
        Node node = root;
        while (node != null && !node.getValue().equals(value)) {
            if (key.compareTo(node.getValue()) < 0) {
                node = node.getLeft();
            } else {
                node = node.getRight();
            }
        }

        if (node == null)
            return null;

        return node;
    }

    public Node delete(T value) {
        Comparable<? super T> key = (Comparable<? super T>) value;
        // First search for value
        Node foundValue = search(value);
        if (foundValue == null)
            return null;

        Node parent = null;
        Node node = root;
        while (!node.getValue().equals(value)) {
            if (key.compareTo(node.getValue()) < 0) {
                parent = node;
                node = node.getLeft();
            } else {
                parent = node;
                node = node.getRight();
            }
        }

        // Case1: node is a leaf --> set parent left or right to null
        if (node.getLeft() == null && node.getRight() == null) {
            if (parent != null && parent.getLeft().equals(node)) {
                parent.setLeft(null);
            } else if (parent != null) {
                parent.setRight(null);
            } else {
                root = null;
            }
            return node;
        }

        // Case2: node has one child --> promote child
        if (node.getLeft() != null && node.getRight() == null) {
            if (parent != null && parent.getLeft().equals(node)) {
                parent.setLeft(node.getLeft());
            } else if (parent != null) {
                parent.setRight(node.getLeft());
            } else {
                root = node.getLeft();
            }
            return node;
        } else if (node.getLeft() == null && node.getRight() != null) {
            if (parent != null && parent.getLeft().equals(node)) {
                parent.setLeft(node.getRight());
            } else if (parent != null) {
                parent.setRight(node.getRight());
            } else {
                root.getRight();
            }
            return node;
        }

        // Case3: node has two children -->
        //              remove next inorder largest node (which is min of right child) and set it as parent
        Node nextBiggest = min(node.getRight());
        nextBiggest = delete(nextBiggest.getValue());
        if (parent != null && parent.getLeft().equals(node)) {
            parent.setLeft(nextBiggest);
        } else if (parent != null) {
            parent.setRight(nextBiggest);
        } else {
            root = nextBiggest;
        }
        nextBiggest.setLeft(node.getLeft());
        nextBiggest.setRight(node.getRight());
        return node;
    }


    public void inorderPrint() {
        inorderPrint(root);
    }

    private void inorderPrint(Node node) {
        if (node == null)
            return;

        inorderPrint(node.getLeft());
        System.out.print(node.getValue() + " ");
        inorderPrint(node.getRight());
    }

    private Node min(Node node) {
        Node min = node;
        while (min.getLeft() != null) {
            min = min.getLeft();
        }
        return min;
    }

    public static void main(String args[]) {
        BST<Integer> bst = new BST<Integer>();
        bst.insert(50);
        bst.insert(30);
        bst.insert(20);
        bst.insert(40);
        bst.insert(70);
        bst.insert(60);
        bst.insert(80);

        System.out.println("Inorder traversal of the given tree \n");
        bst.inorderPrint();

        System.out.println("\nDelete 20\n");
        bst.delete(20);
        System.out.println("Inorder traversal of the modified tree \n");
        bst.inorderPrint();

        System.out.println("\nDelete 30\n");
        bst.delete(30);
        System.out.println("Inorder traversal of the modified tree \n");
        bst.inorderPrint();

        System.out.println("\nDelete 50\n");
        bst.delete(50);
        System.out.println("Inorder traversal of the modified tree \n");
        bst.inorderPrint();
    }
}
