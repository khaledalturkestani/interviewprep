package com.khaled.bst;

/**
 * Created by khaledalturkestani on 7/14/17.
 */
public class IsBST {

    public static boolean isBst(Node root) {
        return isBstUtil(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static boolean isBstUtil(Node node, int min, int max) {
        if (node == null)
            return true;

        if (node.value < min || node.value > max) {
            return false;
        }

        return isBstUtil(node.left, min, node.value-1) && isBstUtil(node.right, node.value+1, max);
    }

    public static void main(String[] args) {
        //           4
        //          /\
        //         2  5
        //        /\
        //       1  3
        Node root       = new Node(4);
        root.left       = new Node(2);
        root.right      = new Node(5);
        root.left.left  = new Node(1);
        root.left.right = new Node(3);

        // Expected: "Is com.khaled.bst.BST"
        if(isBst(root))
            System.out.println("Is com.khaled.bst.BST");
        else
            System.out.println("Not a com.khaled.bst.BST");


        //           4
        //          /\
        //         3  5
        //        /\
        //       1  2
        Node root1       = new Node(4);
        root1.left       = new Node(3);
        root1.right      = new Node(5);
        root1.left.left  = new Node(1);
        root1.left.right = new Node(2);

        // Expected: "Is com.khaled.bst.BST"
        if(isBst(root1))
            System.out.println("Is com.khaled.bst.BST");
        else
            System.out.println("Not a com.khaled.bst.BST");

    }
}
