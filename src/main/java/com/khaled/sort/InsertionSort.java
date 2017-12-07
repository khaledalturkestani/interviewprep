package com.khaled.sort;

/**
 * Created by khaledalturkestani on 7/12/17.
 */
public class InsertionSort {

    public static void sort(int[] arr) {
        if (arr == null) {
            return;
        }

        for (int i = 1; i < arr.length; i++) {
            int j = i;
            for (j = i; j >= 0; j--) {
                if (arr[j] < arr[i])
                    break;
            }
            int temp = arr[i];
            // Shift arr
            for (int k = i-1; k > j; k--) {
                arr[k+1] = arr[k];
            }
            arr[j+1] = temp;
        }
    }

    public static void main(String args[]) {
        int[] arr = {3, 2, 1, 15, 5, 4, 45};
        sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
