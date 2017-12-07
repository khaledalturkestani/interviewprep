package com.khaled.array;

/**
 * Given a set of strings, find the longest common prefix.
 *
 * Binary Search solution: http://www.geeksforgeeks.org/longest-common-prefix-set-4-binary-search/
 */
public class LongestCommonPrefix {

    /**
     * Runtime: O(NMlogM), N: number of strings. M: length of shortest string
     * Space: O(1)
     * @param arr
     * @return
     */
    public static String lcpBinarySearch(String[] arr) {
        if (arr == null || arr.length == 0)
            return null;

        // First, find shortest string
        String shortest = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].length() < shortest.length())
                shortest = arr[i];
        }

        String prefix = "";
        int lo = 0;
        int hi = shortest.length()-1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
             prefix = shortest.substring(0, mid+1);
            boolean allContainPrefix = true;
            for (int i = 0; i < arr.length; i++) {
                if (!arr[i].startsWith(prefix)) {
                    allContainPrefix = false;
                    break;
                }
            }
            if (allContainPrefix) {
                // Go right
                lo = mid+1;
            } else {
                // Go left
                hi = mid;
            }
        }

        return prefix;
    }

    public static String lcpDivideAndConquer(String[] arr) {
        return lcpDivideAndConquer(arr, 0, arr.length);
    }

    public static String lcpDivideAndConquer(String[] arr, int start, int end) {
        if (end - start == 1)
            return arr[start];

        int mid = start + (end - start) / 2;
        String leftPrefix = lcpDivideAndConquer(arr, start, mid);
        String ritePrefix = lcpDivideAndConquer(arr, mid, end);

        StringBuilder prefix = new StringBuilder();
        int len = (leftPrefix.length() < ritePrefix.length()) ? leftPrefix.length() : ritePrefix.length();
        for (int i = 0; i < len; i++) {
            if (leftPrefix.charAt(i) != ritePrefix.charAt(i))
                break;
            prefix = prefix.append(leftPrefix.charAt(i));
        }
        return prefix.toString();
    }

    public static void main(String[] args) {
        System.out.println("Testing Binary Search Algorithm");
        // Expected output: gee
        String[] arr1 = {"geeksforgeeks", "geeks", "geek", "geezer"};
        System.out.println(lcpBinarySearch(arr1));
        // Expected output: ap
        String[] arr2 = {"apple", "ape", "april"};
        System.out.println(lcpBinarySearch(arr2));

        // Divide and Conquer
        System.out.println("Testing Divide and Conquer Algorithm");
        System.out.println(lcpDivideAndConquer(arr1));
        System.out.println(lcpDivideAndConquer(arr2));
    }
}
