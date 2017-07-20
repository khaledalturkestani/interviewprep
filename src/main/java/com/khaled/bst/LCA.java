package com.khaled.bst;

/**
 * Lowest Common Ancestor in a com.khaled.bst.BST.
 */
public class LCA {

    public static Node lca(Node root, Node a, Node b) {
        if (root == null)
            return null;

        if (a.value < root.value && b.value < root.value) {
            return lca(root.left, a, b);
        }

        if (a.value > root.value && b.value > root.value) {
            return lca(root.right, a, b);
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
        root.left             = new Node(8);
        root.right            = new Node(22);
        root.left.left        = new Node(4);
        root.left.right       = new Node(12);
        root.left.right.left  = new Node(10);
        root.left.right.right = new Node(14);

        int n1 = 10;
        int n2 = 14;
        Node t = lca(root, new Node(n1), new Node(n2));
        System.out.println(String.format("com.khaled.bst.LCA of %d and %d is %d \n", n1, n2, t.value));

        n1 = 14;
        n2 = 8;
        t = lca(root, new Node(n1), new Node(n2));
        System.out.println(String.format("com.khaled.bst.LCA of %d and %d is %d \n", n1, n2, t.value));

        n1 = 10;
        n2 = 22;
        t = lca(root, new Node(n1), new Node(n2));
        System.out.println(String.format("com.khaled.bst.LCA of %d and %d is %d \n", n1, n2, t.value));
    }
}

