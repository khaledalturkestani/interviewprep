package com.khaled.bst;

/**
 * Created by khaledalturkestani on 8/5/17.
 */
public class BSTv2<V extends Comparable> {

    private Node root = null;

    private class Node {
        V val;
        Node left = null;
        Node right = null;

        Node(V val) {
            this.val = val;
        }
    }

    public BSTv2() {}

    public void insert(V val) {
        Node node = new Node(val);

        if (root == null) {
            root = node;
            return;
        }

        Node cur = root;
        while (true) {
            if (node.val.compareTo(cur.val) == 0) {
                return;
            }

            if (node.val.compareTo(cur.val) < 0) {
                if (cur.left == null) {
                    cur.left = node;
                    return;
                }
                cur = cur.left;
            } else {
                if (cur.right == null) {
                    cur.right = node;
                    return;
                }
                cur = cur.right;
            }
        }
    }

    public V find(V val) {
        if (root == null)
            return null;

        Node cur = root;
        while (cur != null) {
            if (val.compareTo(cur.val) == 0) {
                return cur.val;
            }

            if (val.compareTo(cur.val) < 0) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return null;
    }

    public V delete(V val) {
        if (root == null)
            return null;

        Node parent = null;
        Node cur = root;
        while (cur != null) {
            if (val.compareTo(cur.val) == 0) {
                break;
            }
            if (val.compareTo(cur.val) < 0) {
                parent = cur;
                cur = cur.left;
            } else {
                parent = cur;
                cur = cur.right;
            }
        }

        // Node not found
        if (cur == null)
            return null;

        if (cur.left == null && cur.right == null)  {
            if (parent != null) {
                if (parent.left.val == cur.val) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            } else {
                root = null;
            }
        } else if (cur.left != null && cur.right != null) {
            // Find next inorder smallest node
            Node inorderSmallest = cur.left;
            while (inorderSmallest.right != null) {
                inorderSmallest = inorderSmallest.right;
            }
            delete(inorderSmallest.val);
            inorderSmallest.left = cur.left;
            inorderSmallest.right = cur.right;
            if (parent != null) {
                if (parent.left.val == cur.val) {
                    parent.left = inorderSmallest;
                } else {
                    parent.right = inorderSmallest;
                }
            } else {
                root = inorderSmallest;
            }
        } else {
            if (parent != null) {
                if (parent.left.val == cur.val) {
                    parent.left = (cur.left != null) ? cur.left : cur.right;
                } else {
                    parent.right = (cur.left != null) ? cur.left : cur.right;
                }
            } else {
                root = (cur.left != null) ? cur.left : cur.right;
            }
        }
        return cur.val;
    }

    public void inorderPrint() {
        inorderPrint(root);
    }

    private void inorderPrint(Node node) {
        if (node == null)
            return;

        inorderPrint(node.left);
        System.out.print(node.val + " ");
        inorderPrint(node.right);
    }

    public static void main(String args[]) {
        BSTv2<Integer> bst = new BSTv2<Integer>();
        bst.insert(50);
        bst.insert(30);
        bst.insert(20);
        bst.insert(40);
        bst.insert(70);
        bst.insert(60);
        bst.insert(80);
        bst.inorderPrint();

        System.out.println("");
        bst.delete(50);
        bst.inorderPrint();

        System.out.println("");
        bst.delete(40);
        bst.inorderPrint();

    }
}
