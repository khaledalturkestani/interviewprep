package com.khaled.array;

/**
 * In a given array nums of positive integers, find three non-overlapping subarrays with maximum sum.
 * Each subarray will be of size k, and we want to maximize the sum of all 3*k entries.
 * Return the result as a list of indices representing the starting position of each interval (0-indexed).
 * If there are multiple answers, return the lexicographically smallest one.
 * https://leetcode.com/problems/maximum-sum-of-3-non-overlapping-subarrays/description/
 */
public class MaxSumThreeNonOverlappingSubArrays {

    private static int[] maxSum(int[] nums, int k) {
        if (nums == null || k <= 0|| 3*k > nums.length)
            throw new RuntimeException("Invalid input data");

        int a = 0;
        int b = k;
        int c = 2*k;
        int[] sums = computeSums(nums, k);
        int max = sums[a] + sums[b] + sums[c];
        for (int i = 2*k; i < sums.length; i++) {
            for (int j = k; j <= i - k; j++) {
                for (int l = 0; l <= j - k; l++) {
                    int sum = sums[i] + sums[j] + sums[l];
                    if (sum > max) {
                        max = sum;
                        a = l; b = j; c = i;
                    }
                    int[] aux = {l, j, i};
//                    System.out.println(printArr(aux));
                }
                int sum = sums[i] + sums[j] + sums[a];
                if (sum > max) {
                    max = sum; b = j; c = i;
                }
                int[] aux = {a, j, i};
//                System.out.println(printArr(aux));
            }
            int sum = sums[i] + sums[b] + sums[a];
            if (sum > max) {
                max = sum;
                c = i;
            }
            int[] aux = {a, b, i};
//            System.out.println(printArr(aux));
        }
        int[] indexes = {a, b, c};
        return indexes;
    }

    private static int[] computeSums(int[] arr, int k) {
        if (k == 1) {
            return arr;
        }
        int[] sums = new int[arr.length - k + 1];
        for (int j = 0; j < k; j++)
            sums[0] += arr[j];
        for (int i = 1; i < sums.length; i++) {
            sums[i] = sums[i-1] - arr[i-1] + arr[i+k-1];
        }
        return sums;
    }

    public static String printArr(int[] arr) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb = sb.append(arr[i]);
            if (i < arr.length-1)
                sb = sb.append(", ");
        }
        sb = sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        int arr[] = {1,2,1,2,6,7,5,1};
        int expected[] = {0, 3, 5};
        int[] got = maxSum(arr, 2);
        System.out.println("Exp: " + printArr(expected));
        System.out.println("Got: " + printArr(got));
        int arr2[] = {20,8,16,16,9,7,13,3,1,16,5,17,6,14,20,20,3,14,19,16,4,15,11,11,11,14,20,6,
                6,1,19,16,6,3,4,11,14,5,14,13,5,6,1,8,13,17,11,7,7,14,20,14,11,20,13,20,15,19,17,2,17,1,2,3,16,9,3,
                15,15,9,8,12,7,10,2,9,6,6,19,2,3,11,10,12,13,5,8,2,13,4,2,7,12,5,12,4,14,2,4,6};
        int expected2[] = {11,19,50};
        int[] got2 = maxSum(arr2, 8);
        System.out.println("Exp: " + printArr(expected2));
        System.out.println("Got: " + printArr(got2));
    }
}
