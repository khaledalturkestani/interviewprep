package com.khaled;

/**
 * Given an unsorted array of non-negative integers, find a continuous sub-array which adds to a given number.
 */
public class SubarrayWithGivenSum {

    public static int[] continuousSum(int[] arr, int sum) {
        if (arr == null || arr.length == 0 || sum <= 0)
            return null;

        int lo = 0;
        int hi = 0;
        int rangeSum = arr[hi];
        /**
         * Explanation: while the sum of the range [lo, hi] is less than 'sum', increment hi and recompute the sum for the new (longer) range.
         *              As soon as the sum of the range is greater than 'sum', decrement lo and recompute the sum for the new (shorter) range.
         */
        while (rangeSum != sum) {
            if (rangeSum < sum || lo == hi) {
                hi++;
                if (hi >= arr.length)
                    break;
                rangeSum += arr[hi];
                if (rangeSum == sum)
                    break;
            } else {
                rangeSum -= arr[lo];
                lo++;
                if (rangeSum == sum)
                    break;
            }
        }
        if (rangeSum != sum)
            return null;

        int[] rangeArr = new int[hi-lo+1];
        for (int i = lo; i <= hi; i++) {
            rangeArr[i-lo] = arr[i];
        }
        return rangeArr;
    }

    public static void main (String[] args) {
        int[] arr1 = {1, 2, 3, 7, 5};
        int sum1 = 12;
        int[] result = continuousSum(arr1, sum1);
        for (int i = 0; i < result.length; i++)
            System.out.print(result[i] + " ");
        System.out.println("");

        int[] arr2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int sum2 = 15;
        result = continuousSum(arr2, sum2);
        for (int i = 0; i < result.length; i++)
            System.out.print(result[i] + " ");
        System.out.println("");
    }
}
