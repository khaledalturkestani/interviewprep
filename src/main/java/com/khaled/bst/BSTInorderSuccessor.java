package com.khaled.bst;

/**
 * Created by khaledalturkestani on 7/18/17.
 */
public class BSTInorderSuccessor {

    private static Node inorderSuccessor(Node root, Node node) {
        if (root == null)
            return null;

        // If node has a right child, then the inorder successor is the min of the right child
        if (node.right != null) {
            return minNode(node.right);
        }

        // Otherwise, it's the last predecessor up the com.khaled.bst.BST for which the node lies on the LEFT branch
        Node succ = null;
        while (root != null) {
            // We're going left --> update successor node
            if (node.value < root.value) {
                succ = root;
                root = root.left;
            }
            else if (node.value > root.value) {
                root = root.right;
            }
            else {
                break;
            }
        }
        return succ;
    }

    private static Node minNode(Node root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    public static void main(String[] args) {

        //            20
        //          /   \
        //         8     22
        //        /\
        //       4  12
        //          / \
        //         10  14
        Node root             = new Node(20);
        Node eight            = new Node(8);
        root.left             = eight;
        Node twentyTwo        = new Node(22);
        root.right            = twentyTwo;
        Node four             = new Node(4);
        root.left.left        = four;
        Node twelve           = new Node(12);
        root.left.right       = twelve;
        Node ten              = new Node(10);
        root.left.right.left  = ten;
        Node fourtneen        = new Node(14);
        root.left.right.right = fourtneen;

        Node answer = inorderSuccessor(root, eight);
        System.out.println(String.format("Expected inorder successor of %d is %d. Got: %d", 8, 10, answer.value));

        answer = inorderSuccessor(root, ten);
        System.out.println(String.format("Expected inorder successor of %d is %d. Got: %d", 10, 12, answer.value));

        answer = inorderSuccessor(root, fourtneen);
        System.out.println(String.format("Expected inorder successor of %d is %d. Got: %d", 14, 20, answer.value));
    }
}
