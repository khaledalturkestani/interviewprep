package com.khaled.bst;


/**
 *
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it
 * can be stored in a file or memory buffer, or transmitted across a network connection link to be
 * reconstructed later in the same or another computer environment.
 *
 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/description/
 */
public class SerializeDeserializeBinaryTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static String serialize(TreeNode root) {
        int height = computeHeight(root);
        Integer[] list = new Integer[(int)Math.pow(2, height)-1];
        serializeHelper(root, list, 0);
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < list.length; i++) {
            sb = sb.append(list[i]);
            if (i < list.length-1)
                sb = sb.append(",");
        }
        sb = sb.append("]");
        return sb.toString();
    }

    public static TreeNode deserialize(String data) {
        data = data.substring(1, data.length()-1);
        String[] split = data.split(",");
        if (split.length == 1 && split[0].length() == 0)
            return null;
        TreeNode root = new TreeNode(new Integer(split[0]));
        buildBT(root, split, 0);
        return root;
    }

    private static void buildBT(TreeNode root, String[] split, int pos) {
        if (root == null) return;
        int left = pos*2+1;
        int right = pos*2+2;
        if (left < split.length && !"null".equals(split[left]))
            root.left = new TreeNode(new Integer(split[left]));
        if (right < split.length && !"null".equals(split[right]))
            root.right = new TreeNode(new Integer(split[right]));
        buildBT(root.left, split, left);
        buildBT(root.right, split, right);
    }

    private static int computeHeight(TreeNode root) {
        if (root == null)
            return 0;
        int left = 1 + computeHeight(root.left);
        int right = 1 + computeHeight(root.right);
        return (left > right) ? left : right;
    }

    private static void serializeHelper(TreeNode root, Integer[] list, int pos) {
        if (root == null)
            return;
        list[pos] = root.val;
        serializeHelper(root.left, list, pos*2+1);
        serializeHelper(root.right, list, pos*2+2);
    }

    public static void main(String[] args) {

        String data = "[1,2,3,4,5,6,7,8]";
        TreeNode root = deserialize(data);
        String serialized = serialize(root);
        System.out.println(serialized.equals(data));
        System.out.println(data);
        System.out.println(serialized);
    }
}
