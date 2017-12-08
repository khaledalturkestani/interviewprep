package com.khaled.array;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by khaledalturkestani on 12/7/17.
 */
public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];
            if (!map.containsKey(n)) {
                int left = (map.containsKey(n-1) ? map.get(n-1) : 0);
                int right = (map.containsKey(n+1) ? map.get(n+1) : 0);

                int len = left + right + 1;
                map.put(n, len);

                if (max < len)
                    max = len;

                map.put(n-left, len);
                map.put(n-right, len);
            } else {
                continue;
            }
        } // End of for loop

       return max;
    }


    public static void main(String[] args) {
        LongestConsecutiveSequence inst = new LongestConsecutiveSequence();
//        int[] arr = {100, 4, 200, 1, 3, 2};
//        int length = inst.longestConsecutive(arr);
//        System.out.println("Exp: " + 4);
//        System.out.println("Got: " + length);
        int[] arr1 = new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        int length1 = inst.longestConsecutive(arr1);
        System.out.println("Exp: " + 4);
        System.out.println("Got: " + length1);
    }
}
