package com.khaled.bst;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Breadth First Search of a (binary) tree.
 */
public class BFSBinaryTree {

    private static class Node {
        int val;
        Node left;
        Node right;

        Node(int val) {
            this.val = val;
        }
    }

    /**
     * Runtime: O(n)
     * Space:   O(n)
     *
     * @param root
     */
    public static void bfsPrint(Node root) {
        Queue queue = new LinkedList();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node node = (Node) queue.remove();
            System.out.print(node.val + " ");
            if (node.left != null)
                queue.add(node.left);
            if (node.right != null)
                queue.add(node.right);
        }
    }

    /**
     * Runtime: O(nlogn)
     * Space:   O(1)
     *
     * @param root
     */
    public static void bfsPrintNoSpace(Node root) {
        int height = height(root);

        for (int i = 1; i <= height; i++) {
            printLevel(root, i);
        }
    }

    public static void printLevel(Node root, int height) {
        if (root == null)
            return;
        if (height == 1)
            System.out.print(root.val + " ");

        printLevel(root.left, height-1);
        printLevel(root.right, height-1);
    }

    public static int height(Node root) {
        if (root == null)
            return 0;

        int leftH = height(root.left);
        int rightH = height(root.right);

        if (leftH > rightH)
            return 1 + leftH;
        return 1 + rightH;
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

        bfsPrint(root);
        System.out.println();
        bfsPrintNoSpace(root);
    }
}
