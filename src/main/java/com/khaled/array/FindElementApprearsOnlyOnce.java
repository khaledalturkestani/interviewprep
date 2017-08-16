package com.khaled.array;

/**
 * Given a sorted array of integers, every element appears twice except for one.
 * Find that single one in linear time complexity and without using extra memory.
 *
 * http://www.geeksforgeeks.org/find-the-element-that-appears-once-in-a-sorted-array/
 *
 */
public class FindElementApprearsOnlyOnce {

    public static int findElem(int[] arr) {

        // Binary search
        int lo = 0;
        int hi = arr.length-1;
        int i = arr.length / 2;
        while (lo < hi) {
            if (i % 2 == 0) {
                if (i+1 < arr.length && arr[i] != arr[i+1]) {
                    // go left
                    hi = i;
                } else {
                    // go right
                    lo = i+1;
                }
            } else {
                if (arr[i] != arr[i-1]) {
                    // go left
                    hi = i;
                } else {
                    // go right
                    lo = i+1;
                }
            }
            i = lo + (hi - lo) / 2;
        }
        return arr[i];
    }

    public static int findElemXOR(int[] arr) {
        int xor = 0;
        for (int i = 0; i < arr.length; i++) {
            xor = xor^arr[i];
        }
        return xor;
    }

    public static void main(String args[]) {
        // Expected: 4
        int[] arr1 = {1, 1, 3, 3, 4, 5, 5, 7, 7, 8, 8};
        System.out.println(findElem(arr1));

        // Expected: 8
        int[] arr2 = {1, 1, 3, 3, 4, 4, 5, 5, 7, 7, 8};
        System.out.println(findElem(arr2));

        // Expected: 1
        int[] arr3 = {1, 3, 3, 4, 4, 5, 5, 7, 7, 8, 8};
        System.out.println(findElem(arr3));

        // Expected: 4
        int[] arr4 = {1, 1, 3, 3, 4, 5, 5, 7, 7, 8, 8};
        System.out.println(findElemXOR(arr4));

        // Expected: 8
        int[] arr5 = {1, 1, 3, 3, 4, 4, 5, 5, 7, 7, 8};
        System.out.println(findElemXOR(arr5));

        // Expected: 1
        int[] arr6 = {1, 3, 3, 4, 4, 5, 5, 7, 7, 8, 8};
        System.out.println(findElemXOR(arr6));
    }
}

