package com.khaled.bst;

/**
 * Given a sorted array, construct a balanced BST.
 * Solution: Insert the middle of the array into the BST first. Then recursively insert the middle of the left
 *           side as the left node, and the right side as the right node. 
 */
public class SortedArrayToBST {

    public static BSTv2<Integer> arrToBst(int[] arr) {
        if (arr == null)
            return null;

        BSTv2<Integer> bst = new BSTv2<Integer>();
        arrToBstRec(bst, arr, 0, arr.length);
        return bst;
    }

    public static void arrToBstRec(BSTv2<Integer> bst, int[] arr, int begin, int end) {
        if (begin >= end)
            return;

        int middle = begin + (end - begin) / 2;
        bst.insert(arr[middle]);

        arrToBstRec(bst, arr, begin, middle);
        arrToBstRec(bst, arr, middle+1, end);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        BSTv2 bst = arrToBst(arr);
    }
}
