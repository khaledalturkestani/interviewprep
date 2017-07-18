package com.khaled;

/**
 * Problem:
 *
 * Given two strings str1 and str2 and below operations that can performed on str1. Find minimum number of edits
 * (operations) required to convert ‘str1’ into ‘str2’.
 *
 *      Insert
 *      Remove
 *      Replace
 *
 * All of the above operations are of equal cost.
 *
 * http://www.geeksforgeeks.org/dynamic-programming-set-5-edit-distance/
 *
 */
public class EditDistance {

    public static int minEdits(String str1, String str2, int str1len, int str2len) {

        if (str1len == 0) return str2len;

        if (str2len == 0) return str1len;

        if (str1.charAt(str1len) == str2.charAt(str2len))
            return minEdits(str1, str2, str1len-1, str2len-1);

        return 1 + Math.min(Math.min(minEdits(str1, str2, str1len-1, str2len),
                minEdits(str1, str2, str1len, str2len-1)),
                minEdits(str1, str2, str1len-1, str2len-1));
    }

    /**
     * Test cases:
     *
     * @param args
     */
    public static void main(String args[]) {
        String str1 = "sunday";
        String str2 = "saturday";

        // Expected output: 3
        System.out.println( minEdits( str1 , str2 , str1.length()-1, str2.length()-1) );
    }
}
