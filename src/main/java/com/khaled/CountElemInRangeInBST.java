package com.khaled;

import com.khaled.bst.Node;

/**
 * Count the number of elements in a given range between 'a' and 'b' in a BST.
 */
public class CountElemInRangeInBST {

    public static class MutableInteger {
        int count = 0;
    }

    public static int countInRange(Node root, int a, int b) {
        MutableInteger count = new MutableInteger();
        countInRangeUtil(root, a, b, count);
        return count.count;
    }

    public static void countInRangeUtil(Node root, int a, int b, MutableInteger count) {
        if (root == null)
            return;

        countInRangeUtil(root.left, a, b, count);

        if (root.value >= a && root.value <= b)
            count.count++;

        countInRangeUtil(root.right, a, b, count);
    }


    public static void main(String args[]) {
        /* Let us create following BST
              50
           /     \
          30      70
         /  \    /  \
       20   40  60   80 */
        Node twenty = new Node(20);
        Node thirty = new Node(30);
        Node forty = new Node(40);
        Node root = new Node(50);
        Node sixty = new Node(60);
        Node seventy = new Node(70);
        Node eigthy = new Node(80);

        root.left = thirty;
        root.right = seventy;
        thirty.left = twenty;
        thirty.right = forty;
        seventy.left = sixty;
        seventy.right = eigthy;

        int count = countInRange(root, 15, 20);
        System.out.println("Expected count: 1. Got: " + count);

        count = countInRange(root, 30, 60);
        System.out.println("Expected count: 4. Got: " + count);

        count = countInRange(root, 60, 80);
        System.out.println("Expected count: 3. Got: " + count);
    }
}
