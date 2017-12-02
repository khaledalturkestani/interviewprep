package com.khaled.array;

/**
 * In a given array nums of positive integers, find three non-overlapping subarrays with maximum sum.
 * Each subarray will be of size k, and we want to maximize the sum of all 3*k entries.
 * Return the result as a list of indices representing the starting position of each interval (0-indexed).
 * If there are multiple answers, return the lexicographically smallest one.
 * https://leetcode.com/problems/maximum-sum-of-3-non-overlapping-subarrays/description/
 *
 ************************************************
 ************** DOESN'T WORK ********************
 ************************************************
 */
public class MaxSumThreeNonOverlappingSubArrays2 {

    public static int[] maxThreeSubarrays(int[] nums, int k) {
        // Validate input
        if (nums == null || k < 1 || nums.length < k*3)
            throw new RuntimeException("Invalid input");
        // Compute sums
        int[] sums = computeSums(nums, k);
        int a = 0;
        int b = k;
        int c = 2*k;
        int max = sums[a] + sums[b] + sums[c];
        for (int l = 2*k; l < sums.length; l++) {
            for (int j = k; j <= l-k; j++) {
                for (int i = 0; i <= j-k; i++) {
                    int sum = sums[i] + sums[j] + sums[l];
                    if (sum > max) {
                        max = sum;
                        a = i;
                        b = j;
                        c = l;
                    }
                }
            }
        }
        int[] indexes =  {a, b, c};
        return indexes;
    }


    private static int[] computeSums(int[] nums, int k) {
        int[] sums = new int[nums.length - k + 1];
        for (int i = 0; i < k; i++) {
            sums[0] += nums[i];
        }
        for (int i = 1; i < sums.length; i++) {
            sums[i] = sums[i-1] - nums[i-1] + nums[i+k-1];
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
        int[] got = maxThreeSubarrays(arr, 2);
        System.out.println("Exp: " + printArr(expected));
        System.out.println("Got: " + printArr(got));
        int arr2[] = {20,8,16,16,9,7,13,3,1,16,5,17,6,14,20,20,3,14,19,16,4,15,11,11,11,14,20,6,
                6,1,19,16,6,3,4,11,14,5,14,13,5,6,1,8,13,17,11,7,7,14,20,14,11,20,13,20,15,19,17,2,17,1,2,3,16,9,3,
                15,15,9,8,12,7,10,2,9,6,6,19,2,3,11,10,12,13,5,8,2,13,4,2,7,12,5,12,4,14,2,4,6};
        int expected2[] = {11,19,50};
        int[] got2 = maxThreeSubarrays(arr2, 8);
        System.out.println("Exp: " + printArr(expected2));
        System.out.println("Got: " + printArr(got2));
    }
}
