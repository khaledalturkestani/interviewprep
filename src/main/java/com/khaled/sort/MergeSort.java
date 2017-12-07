package com.khaled.sort;

public class MergeSort {

    public static void sort(int[] arr) {
        if (arr == null)
            return;

        sortUtil(arr, 0, arr.length);
    }

    public static void sortUtil(int[] arr, int start, int len) {
        if (len <= 1) return;

        int mid = len/2;
        // Sort left half
        sortUtil(arr, start, mid);
        // Sort right half
        sortUtil(arr, start+mid, len-mid);

        // Make a copy of left half
        int[] left = new int[mid];
        for (int i = 0; i < left.length; i++) {
            left[i] = arr[start+i];
        }
        // Make a copy of right half
        int[] right = new int[len-mid];
        for (int j = 0; j < right.length; j++) {
            right[j] = arr[start+mid+j];
        }

        // Merge the two halves
        int lIdx = 0;
        int rIdx = 0;
        int idx = start;
        while (lIdx < left.length) {
            while (rIdx < right.length && right[rIdx] <= left[lIdx]) {
                arr[idx] = right[rIdx];
                idx++;
                rIdx++;
            }
            arr[idx] = left[lIdx];
            idx++;
            lIdx++;
        }
        while (rIdx < right.length) {
            arr[idx] = right[rIdx];
            idx++;
            rIdx++;
        }
    }

    private static void printArr(int[] arr) {
        if (arr == null) return;

        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i != arr.length-1)
                System.out.print(", ");
        }
        System.out.println("]");
    }

    public static void main(String args[]) {
        int arr[] = {1, 5, 2, 3, 3};
        sort(arr);
        printArr(arr);

        int arr2[] = {1, 2, 1, 2, 1, 2, 1, 2};
        sort(arr2);
        printArr(arr2);

        int arr3[] = {1, 1, 1, 1, 1, 1};
        sort(arr3);
        printArr(arr3);

        int arr4[] = {2, 2, 2, 1, 1, 1};
        sort(arr4);
        printArr(arr4);

        int arr5[] = {2};
        sort(arr5);
        printArr(arr5);

        int arr6[] = {};
        sort(arr6);
        printArr(arr6);
    }
}
