package com.khaled.string;

import java.util.Arrays;

/**
 * Let the input string be “i like this program very much”.
 * The function should change the string to “much very program this like i”
 */
public class ReverseWordsInString {

    // Solution:
    //  - Not using java helper functions
    //  - Basically, reverse every word. Then reverse the whole string.
    //  - BUT, since Strings are immutable in java, you can't do it in place. So it's
    //    better to just split the string into its words and reverse the list then concatenate them back together.

    public static String reverse (String str) {
        String[] words = str.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = words.length-1; i >=0; i--) {
            sb.append(words[i]).append(" ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String str = "i like this program very much";
        String revers = reverse(str);
        System.out.println(revers);
    }
}
