package com.khaled.bst;

import javax.swing.*;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Using a Deque so we don't have to store a complete binary tree with null values
 * (like in first implementation). Solution mentioned in Discussion section:
 *
 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/description/
 *
 */
public class SerializeDeserializeBinaryTree2 {


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static String serialize(TreeNode root) {
        if (root == null)
            return null;
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }

    public static void buildString(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("null").append(",");
        } else {
            sb.append(root.val).append(",");
            buildString(root.left, sb);
            buildString(root.right, sb);
        }
    }

    public static TreeNode deserialize(String data) {
        if (data == null)
            return null;
        String[] tokens = data.split(",");
        Deque<String> list = new LinkedList<>(Arrays.asList(tokens));
        TreeNode root = new TreeNode(new Integer(list.remove()));
        buildBT(root, list);
        return root;
    }

    public static void buildBT(TreeNode root, Deque<String> q) {
        if (q.isEmpty())
            return;
        String left = q.remove();
        if (!"null".equals(left)) {
            root.left = new TreeNode(new Integer(left));
            buildBT(root.left, q);
        }

        if (!q.isEmpty()) {
            String right = q.remove();
            if (!"null".equals(right)) {
                root.right = new TreeNode(new Integer(right));
                buildBT(root.right, q);
            }
        }
    }

    public static void main(String[] args) {
        String data = "1,2,3,4,5,6,7,8";
        TreeNode root = deserialize(data);
        String serialized = serialize(root);
        System.out.println(serialized.equals(data));
        System.out.println(data);
        System.out.println(serialized);
    }
}
