package com.khaled.array;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Sort elements in an array according to their frequency.
 * If frequency of two elements matches, then print them in increasing order.
 */
public class SortByRequency {

    public static void sortByFrequency(int[] arr) {
        if (arr == null)
            return;

        final Map<Integer, Integer> freqMap = new HashMap<Integer, Integer>();

        for (int i = 0; i < arr.length; i++) {
            if (freqMap.containsKey(arr[i])) {
                Integer count = freqMap.get(arr[i]);
                count++;
                freqMap.put(arr[i], count);
            } else {
                freqMap.put(arr[i], 1);
            }
        }

        List<int[]> list = freqMap.keySet().stream().sorted(new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                if (freqMap.get(o1) > freqMap.get(o2)) return -1;
                if (freqMap.get(o1) < freqMap.get(o2)) return 1;
                if (o1 < o2) return -1;
                if (o2 < o1) return 1;
                return 0;
            }
        }).map(k -> {
            int[] auxArr = new int[2];
            auxArr[0] = k;
            auxArr[1] = freqMap.get(k);
            return auxArr;
        }).collect(Collectors.toList());

        int i = 0;
        for (int[] a : list) {
            for (int j = 0; j < a[1]; j++) {
                arr[i] = a[0];
                i++;
            }
        }
    }

    public static void printArray(int[] arr){
        System.out.print("{");
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length-1)
                System.out.println(arr[i] + "}");
            else
                System.out.print(arr[i] + ", ");
        }
    }
    public static void main(String[] args) {
        int[] arr = {2, 3, 2, 4, 5, 12, 2, 3, 3, 3, 12};
        sortByFrequency(arr);
        printArray(arr);
    }
}
