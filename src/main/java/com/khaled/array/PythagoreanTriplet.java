package com.khaled.array;

import java.util.Arrays;

/**
 * Given an array of integers, write a function that returns true if
 * there is a triplet (a, b, c) that satisfies a2 + b2 = c2.
 *
 * http://www.geeksforgeeks.org/find-pythagorean-triplet-in-an-unsorted-array/
 */
public class PythagoreanTriplet {

    public static boolean isTriplet(int[] arr) {
        if (arr == null || arr.length < 3)
            return false;

        // First, raise all elements to power of 2
        for (int i =0; i < arr.length; i++) {
            arr[i] = arr[i] * arr[i];
        }

        // Sort in increasing order
        Arrays.sort(arr);

        // This makes the runtime O(n^2)
        for (int i = arr.length-1; i >= 0; i--) {
            int lo = 0;
            int hi = i-1;
            while (lo < hi) {
                if (arr[lo] + arr[hi] == arr[i]) {
                    System.out.println(arr[lo] + " + " + arr[hi] + " = " + arr[i]);
                    return true;
                }
                else if (arr[lo] + arr[hi] < arr[i])
                    lo++;
                else
                    hi--;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 4, 6, 5};
        if (isTriplet(arr))
            System.out.println("Found triplet");
    }
}
