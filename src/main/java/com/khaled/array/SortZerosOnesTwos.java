package com.khaled.array;

import java.util.Random;

/**
 * Given an array of 0's, 1's, and 2's, sort it.
 */
public class SortZerosOnesTwos {
    /**
     * Solution Explanations:
     *  Go over the array and count the occurrences of each integer. Then set 3 pivots at indexes such that
     *  all the zeros will fit in the 1st section, all the ones will fit in the middle section, and all the twos
     *  will fit in the third sections. Then do the swaps by scanning the array and keeping track of the last index
     *  of continuous 0's in the first section, the last index of 1's in the middle section, and the last index of
     *  two's in the third section. Use those indices to do swaps.
     */
    public static void sort(int[] arr) {
        int count0 = 0;
        int count1 = 0;
        int count2 = 0;
        int last1 = -1;
        int last2 = -1;
        int pivot1 = 0;
        int pivot2 = 0;
        // Count occurrences
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) count0++;
            else if (arr[i] == 1) count1++;
            else count2++;
        }
        pivot1 = count0;
        pivot2 = count0 + count1;
        // Move all 0's to first section
        for (int i = 0; i < pivot1; i++) {
            if (arr[i] != 0) {
                swap(arr, i, nextValInRange(arr, 0, i));
            }
        }
        // Move all 1's to second section
        for (int i = pivot1; i < pivot2; i++) {
            if (arr[i] != 1) {
                swap(arr, i, nextValInRange(arr, 1, i));
            }
        }
        // Third section should already be sorted
    }

    public static void swap(int arr[], int i1, int i2) {
        int temp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = temp;
    }

    public static int nextValInRange(int[] arr, int val, int begin) {
        for (int i = begin; i < arr.length; i++)
            if (arr[i] == val) return i;
        return -1;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
        System.out.println("");
    }

    public static void main (String[] args) {
        // Generate array
        System.out.println("Case: 0's, 1's, and 2's");
        Random random = new Random();
        int len = 30;
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = random.nextInt(3);
        }
        printArray(arr);
        sort(arr);
        printArray(arr);

        // Case: all 0's
        System.out.println("Case: all 0's");
        arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = 0;
        }
        printArray(arr);
        sort(arr);
        printArray(arr);

        // Case: all 1's
        System.out.println("Case: all 1's");
        arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = 1;
        }
        printArray(arr);
        sort(arr);
        printArray(arr);

        // Case: all 2's
        System.out.println("Case: all 2's");
        arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = 2;
        }
        printArray(arr);
        sort(arr);
        printArray(arr);

        // Case: 0's and 1's
        System.out.println("Case: 0's and 1's");
        arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = random.nextInt(3);
            while (arr[i] == 2)
                arr[i] = random.nextInt(3);
        }
        printArray(arr);
        sort(arr);
        printArray(arr);

        // Case: 0's and 2's
        System.out.println("Case: 0's and 2's");
        arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = random.nextInt(3);
            while (arr[i] == 1)
                arr[i] = random.nextInt(3);
        }
        printArray(arr);
        sort(arr);
        printArray(arr);

        // Case: 1's and 2's
        System.out.println("Case: 1's and 2's");
        arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = random.nextInt(3);
            while (arr[i] == 0)
                arr[i] = random.nextInt(3);
        }
        printArray(arr);
        sort(arr);
        printArray(arr);
    }
}
