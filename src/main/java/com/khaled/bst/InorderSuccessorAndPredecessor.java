package com.khaled.bst;

/**
 * You need to find the inorder successor and predecessor of a given key.
 * In case the given key is not found in com.khaled.bst.BST, then return the two values within which this key will lie.
 *
 * http://www.geeksforgeeks.org/inorder-predecessor-successor-given-key-bst/
 */
public class InorderSuccessorAndPredecessor {

    public class PredSucc {
        Node pred;
        Node Succ;
    }

    public static Node insert(Node root, int value) {
        Node node = new Node(value);

        if (root == null) {
            return node;
        }

        Node parentNode = root;
        while (parentNode != null) {
            if (node.value > parentNode.value) {
                if (parentNode.right != null) {
                    parentNode = parentNode.right;
                } else {
                    parentNode.right = node;
                    break;
                }
            } else {
                if (parentNode.left != null) {
                    parentNode = parentNode.left;
                } else {
                    parentNode.left = node;
                    break;
                }
            }
        }
        return root;
    }

    public static Node[] predSucc(Node root, int value) {
        if (root == null)
            return null;

        Node pred = null; // Predecessor
        Node succ = null; // Successor

        Node node = root;
        while (true) {
            if (value == node.value) {
                if (root.left != null) pred = maxNode(node.left);        // Pred. is Max of left node.
                if (root.right != null) succ = minNode(node.right);      // Succ. is Min of right node.
                break;
            } else if (value < node.value) {
                if (node.left == null) {
                    succ = node;     // Pred. is null and Succ. is current node.
                    break;
                } else if (value > maxNode(node.left).value) {
                    pred = node.left;
                    succ = node;
                    break;
                } else {
                    node = node.left;
                }
            } else {        // Value is greater than node's value
                if (node.right == null) {
                    pred = node;
                    break;
                } else if (value < minNode(node.right).value) {
                    pred = node;
                    succ = minNode(node.right);
                    break;
                } else {
                    node = node.right;
                }
            }
        }

        Node[] predSucc = new Node[2];
        predSucc[0] = pred;
        predSucc[1] = succ;
        return predSucc;
    }

    private static Node minNode(Node root) {
        if (root == null)
            return null;

        Node node = root;
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public static Node maxNode(Node root) {
        if (root == null)
            return null;

        Node node = root;
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }


    public static void main(String[] args) {
        Node root = insert(null, 50);
        root = insert(root, 30);
        root = insert(root, 20);
        root = insert(root, 40);
        root = insert(root, 70);
        root = insert(root, 60);
        root = insert(root, 80);

        int key = 65;

        Node[] predSucc = predSucc(root, key);

        System.out.println("Predecessor: " + ((predSucc[0] != null) ? predSucc[0].value : null));
        System.out.println("Successor  : " + ((predSucc[1] != null) ? predSucc[1].value : null));
    }
}
