package com.khaled;

/**
 * Created by khaledalturkestani on 7/12/17.
 */
public class HeapSort {

    public enum Order {
        ASCENDING,
        DESCENDING
    }

    public static void sort(int[] arr, Order order) {
        if (order == Order.ASCENDING) {
            orderAsc(arr);
        } else {
            orderDesc(arr);
        }
    }

    private static void orderAsc(int[] arr) {
        for (int i = arr.length - 1; i >= 0; i--) {
            maxHeapify(arr, i, arr.length-1);
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            swap(arr, 0, i);
            maxHeapify(arr, 0, i-1);
        }
    }


    private static void orderDesc(int[] arr) {
        for (int i = arr.length - 1; i >= 0; i--) {
            minHeapify(arr, i, arr.length-1);
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            swap(arr, 0, i);
            minHeapify(arr, 0, i-1);
        }

    }

    private static void swap(int[] arr, int i1, int i2) {
        int temp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = temp;
    }

    private static int parent(int indx) {
        return (indx - 1) / 2;
    }

    private static int left(int indx) {
        return 2 * indx + 1;
    }

    private static int right(int indx) {
        return 2 * indx + 2;
    }

    private static void minHeapify(int[] arr, int indx, int lastIndx) {
        int left = left(indx);
        int right = right(indx);
        int smallest = indx;
        if (left <= lastIndx && arr[left] < arr[smallest]) {
            smallest = left;
        }
        if (right <= lastIndx && arr[right] < arr[smallest]) {
            smallest = right;
        }
        if (smallest != indx) {
            swap(arr, indx, smallest);
            minHeapify(arr, smallest, lastIndx);
        }
    }

    private static void maxHeapify(int[] arr, int indx, int lastIndx) {
        int left = left(indx);
        int right = right(indx);
        int largest = indx;
        if (left <= lastIndx && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right <= lastIndx && arr[right] > arr[largest]) {
            largest = right;
        }
        if (largest != indx) {
            swap(arr, indx, largest);
            maxHeapify(arr, largest, lastIndx);
        }
    }

    public static void main(String args[]) {
        System.out.println("Ascending Order:");
        int[] arr = {3, 2, 1, 15, 5, 4, 45};
        sort(arr, Order.ASCENDING);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
        System.out.println("Descending Order:");
        int[] arr1 = {3, 2, 1, 15, 5, 4, 45};
        sort(arr1, Order.DESCENDING);
        for (int i = 0; i < arr1.length; i++) {
            System.out.println(arr1[i]);
        }
    }
}
