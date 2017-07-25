package com.khaled.array;

import java.awt.peer.SystemTrayPeer;

/**
 * Given an array, reverse every sub-array formed by consecutive k elements.
 * Examples:
 *  Input:
 *  arr = [1, 2, 3, 4, 5, 6, 7, 8, 9]
 *  k = 3
 *  Output:
 *  [3, 2, 1, 6, 5, 4, 9, 8, 7]
 */
public class ReverseArrayInGroups {

    public static void reverse(int[] arr, int k) {
        int start = 0;
        int finish = (k > arr.length) ? arr.length : k;
        while (finish <= arr.length) {
            int middle = start + ((finish - start) / 2);
            for (int i = start; i < middle; i++) {
                int temp = arr[i];
                arr[i] = arr[finish-(i-start)-1];
                arr[finish-(i-start)-1] = temp;
            }
            if (finish == arr.length)
                break;
            start += (finish - start);
            finish = (finish + k > arr.length ? arr.length : finish + k);
        }
    }

    public static void printArr(int[] arr) {
        if (arr == null) return;
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            if (i != arr.length-1)
                System.out.print(arr[i] + ", ");
            else
                System.out.println(arr[i] + "]");
        }
    }

    public static void main(String args[]) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int k = 3;
        System.out.println("Expected: [3, 2, 1, 6, 5, 4, 9, 8, 7]");
        System.out.print(  "Got     : ");
        reverse(arr, k);
        printArr(arr);

        int[] arr2 = {1, 2, 3, 4, 5, 6, 7, 8};
        k = 5;
        System.out.println("Expected: [5, 4, 3, 2, 1, 8, 7, 6]");
        System.out.print(  "Got     : ");
        reverse(arr2, k);
        printArr(arr2);

        int[] arr3 = {1, 2, 3, 4, 5, 6};
        k = 1;
        System.out.println("Expected: [1, 2, 3, 4, 5, 6]");
        System.out.print(  "Got     : ");
        reverse(arr3, k);
        printArr(arr3);

        int[] arr4 = {1, 2, 3, 4, 5, 6, 7, 8};
        k = 10;
        System.out.println("Expected: [8, 7, 6, 5, 4, 3, 2, 1]");
        System.out.print(  "Got     : ");
        reverse(arr4, k);
        printArr(arr4);
    }
}
