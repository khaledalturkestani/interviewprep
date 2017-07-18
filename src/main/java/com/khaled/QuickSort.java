package com.khaled;

/**
 * Created by Khaled.AlTurkestani on 7/7/2017.
 */
public class QuickSort {

    public static void sort(int[] arr) {
        if (arr == null)
            return;

        sortUtil(arr, 0, arr.length-1);
    }

    private static void sortUtil(int arr[], int left, int right) {
        if (right - left <= 1)
            return;

        int pivot = right;
        int low = left;
        int high = right-1;
        while (low <= high) {
            if (arr[low] <= arr[pivot]) {
                low++;
            } else if (arr[high] > arr[pivot]) {
                high--;
            } else {
                int temp = arr[low];
                arr[low] = arr[high];
                arr[high] = temp;
                low++;
                high--;
            }
        }
        // Switch with 'low'
        int temp = arr[low];
        arr[low] = arr[pivot];
        arr[pivot] = temp;
        pivot = low;

        sortUtil(arr, left, pivot-1);
        sortUtil(arr, pivot+1, right);
    }

    private static void printArr(int[] arr) {
        if (arr == null)
            return;

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
    }
}
