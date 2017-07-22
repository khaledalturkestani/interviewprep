package com.khaled.array;

/**
 * Given an array, return the length of the longest increasing sub-sequence.
 */
public class LongestIncreasingSubsequence {

    /**
     * Recursive solution.
     *
     * @param arr
     * @return
     */
    public static int lisRec(int[] arr) {
        return _lis(arr, arr.length-1);
    }

    public static int _lis(int[] arr, int curIndx) {
        if (curIndx == 0)
            return 1;

        int maxSoFar = 0;
        for (int i = curIndx-1; i >= 0; i--) {
            if (arr[curIndx] > arr[i]) {
                int max = _lis(arr, i);
                maxSoFar = (max > maxSoFar) ? max : maxSoFar;
            }
        }
        return 1 + maxSoFar;
    }

    public static int lisDP(int[] arr) {
        int[] lisArr = new int[arr.length];
        for (int i = 0; i < lisArr.length; i++) {
            lisArr[i] = 1;
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && lisArr[i] < lisArr[j] + 1) {
                    lisArr[i] = lisArr[j] + 1;
                }
            }
        }
        int maxLen = 0;
        for (int i = 0; i < lisArr.length; i++) {
            if (lisArr[i] > maxLen)
                maxLen = lisArr[i];
        }
        return maxLen;
    }

    public static void main(String args[]) {
        int[] arr = {10, 22, 9, 33, 21, 50, 41, 60, 80};

        // Longest increasing subsequence: 10, 22, 33, 50, 60, 80
        int lis = lisRec(arr);
        System.out.println("Expected length: 6. Got: " + lis);

        int[] arr1 = {10, 22, 12, 33, 14, 44, 16, 55, 18};

        // Longest increasing subsequence: 10, 12, 14, 16, 18
        int lis1 = lisRec(arr1);
        System.out.println("Expected length: 5. Got: " + lis1);

        System.out.println("Testing Dynamic Programming Solution....");

        // Longest increasing subsequence: 10, 22, 33, 50, 60, 80
        int[] arr2 = {10, 22, 9, 33, 21, 50, 41, 60, 80};
        int lis2 = lisDP(arr2);
        System.out.println("Expected length: 6. Got: " + lis2);

        int[] arr3 = {10, 22, 12, 33, 14, 44, 16, 55, 18};

        // Longest increasing subsequence: 10, 12, 14, 16, 18
        int lis3 = lisDP(arr3);
        System.out.println("Expected length: 5. Got: " + lis3);
    }
}
