package com.khaled.array;

/**
 * Given an array of n positive integers. Write a program to find the sum of maximum sum subsequence of the given array
 * such that the integers in the subsequence are sorted in increasing order.
 */
public class MaxSumIncreasingSubseq {


    public static int maxSumRec(int[] arr) {
        return _maxSumRec(arr, arr.length-1);
    }

    private static int _maxSumRec(int[] arr, int curIndx) {
        if (curIndx == 0)
            return arr[curIndx];

        int maxSoFar = 0;
        for (int i = curIndx-1; i >= 0; i--) {
            if (arr[curIndx] > arr[i]) {
                int max = _maxSumRec(arr, i);
                if (max > maxSoFar) {
                    maxSoFar = max;
                }
            }
        }
        return arr[curIndx] + maxSoFar;
    }

    /**
     * Dynamic Programming Solution.
     *
     * @param arr
     * @return
     */
    public static int maxSumDP(int[] arr) {
        int[] sums = new int[arr.length];
        sums[0] = arr[0];
        for (int i = 1; i < sums.length; i++) {
            sums[i] = 0;
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] &&  sums[i] < (sums[j] + arr[i])) {
                    sums[i] = sums[j] + arr[i];
                }
            }
        }

        int maxSum = 0;
        for (int i = 0; i < sums.length; i++) {
            if (sums[i] > maxSum)
                maxSum = sums[i];
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5};
        int max = maxSumRec(arr1);     // Expected: 15
        System.out.println("Expected: 15. Got: " + max);

        int[] arr2 = {1, 2, 3, 4, 1, 10};
        max = maxSumRec(arr2);     // Expected: 20
        System.out.println("Expected: 20. Got: " + max);

        System.out.println("Running Dynamic Programming Solution..");

        int[] arr3 = {1, 2, 3, 4, 5};
        int max3 = maxSumDP(arr3);     // Expected: 15
        System.out.println("Expected: 15. Got: " + max3);

        int[] arr4 = {1, 2, 3, 4, 1, 10};
        int max4 = maxSumDP(arr4);     // Expected: 20
        System.out.println("Expected: 20. Got: " + max4);
    }
}
