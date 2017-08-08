package com.khaled.array;

/**
 * Created by khaledalturkestani on 8/8/17.
 */
public class SubarrayWithGivenSumV2 {

    public static int[] subarray(int[] arr, int sum) {
        int index = 0;
        int start = 0;
        int len = 0;
        int sumSoFar = 0;
        while (index < arr.length) {
            sumSoFar += arr[index];
            index++;
            len++;
            if (sumSoFar == sum) {
                break;
            } else if (sumSoFar > sum) {
                while (sumSoFar > sum && start <= index) {
                    sumSoFar -= arr[start];
                    start++;
                    len--;
                }
                if (sumSoFar == sum) {
                    break;
                }
            }
        }
        if (sumSoFar == sum) {
            int[] resultArr = new int[len];
            for (int i = 0; i < len; i++) {
                resultArr[i] = arr[i + start];
            }
            return resultArr;
        }
        return null;
    }

    public static void main(String args[]) {
        int[] arr1 = {1, 2, 3, 7, 5};
        int sum1 = 12;
        int[] result = subarray(arr1, sum1);
        for (int i = 0; i < result.length; i++)
            System.out.print(result[i] + " ");
        System.out.println("");

        int[] arr2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int sum2 = 15;
        result = subarray(arr2, sum2);
        for (int i = 0; i < result.length; i++)
            System.out.print(result[i] + " ");
        System.out.println("");
    }
}
