package com.khaled.array;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Given an array of numbers, arrange them in a way that yields the largest value.
 * For example, if the given numbers are {54, 546, 548, 60}, the arrangement 6054854654 gives the largest value.
 * And if the given numbers are {1, 34, 3, 98, 9, 76, 45, 4}, then the arrangement 998764543431 gives the largest value.
 */
public class ArrangeArrayToGetLargestNumber {

    public static void rearrange(int[] arr) {

        Integer[] intArray = new Integer[arr.length];
        for (int i = 0 ; i < arr.length; i++) {
            intArray[i] = arr[i];
        }

        Arrays.sort(intArray, new Comparator<Integer>(){
            public int compare(Integer o1, Integer o2) {
                String str1 = o1.toString();
                String str2 = o2.toString();

                int len = (str1.length() > str2.length()) ? str1.length() : str2.length();
                for (int i = 0; i < len; i++) {
                    if (str1.charAt(i%str1.length()) > str2.charAt(i%str2.length())) return -1;
                    if (str1.charAt(i%str1.length()) < str2.charAt(i%str2.length())) return 1;
                }
                return 0;
            }
        });
        for (int i = 0 ; i < arr.length; i++) {
            arr[i] = intArray[i];
        }
    }
    public static void printConcatArray(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length-1)
                System.out.println(arr[i]);
            else
                System.out.print(arr[i]);
        }
    }

    public static void main (String[] args) {
        int[] arr1 = {54, 546, 548, 60};
        rearrange(arr1);
        System.out.println("Expected: 6054854654");
        System.out.print(  "Got     : ");
        printConcatArray(arr1);

        int[] arr2 = {1, 34, 3, 98, 9, 76, 45, 4};
        rearrange(arr2);
        System.out.println("Expected: 998764543431");
        System.out.print(  "Got     : ");
        printConcatArray(arr2);
    }
}
