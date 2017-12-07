package com.khaled.string;

import java.lang.instrument.Instrumentation;

/**
 *
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 *
 * For example,
 * S = "ADOBECODEBANC"
 * T = "ABC"
 * Minimum window is "BANC".
 *
 *  https://leetcode.com/problems/minimum-window-substring/description/
 */
public class MinimumWindowSubstring2 {

        public static String minWindow(String S, String P) {
            if (P.length() > S.length() || P.length() == 0)
                return "";

            int[] seen = new int[256];
            int[] patt = new int[256];

            // Run through pattern
            for (int i = 0; i < P.length(); i++)
                patt[P.charAt(i)]++;

            int matches = 0;
            int startIndex = -1;
            int i = 0;
            int minLen = Integer.MAX_VALUE;
            for (int cur = 0; cur < S.length(); cur++) {
                char c = S.charAt(cur);
                seen[c]++;

                if (seen[c] <= patt[c]) {
                    matches++;
                }

                if (matches == P.length()) {
                    char ci = S.charAt(i);
                    while (patt[ci] == 0 || seen[ci] > patt[ci]) {
                        seen[ci]--;
                        i++;
                        ci = S.charAt(i);
                    }
                    int len = cur - i + 1;
                    if (minLen > len) {
                        startIndex = i;
                        minLen = len;
                    }
                }
            }
            if (minLen > -1 && matches == P.length())
                return S.substring(startIndex, startIndex + minLen);
            return "";
        }

    public static void main (String[] args) {

//        String s = "acbbaca";
//        String t = "aba";
//        String s = "cabwefgewcwaefgcf";
//        String t = "cae";;
        String s = "bba";
        String t = "ab";
//        String s = "ADOBECODEBANC";
//        String t = "ABC";
        String minWindow = minWindow(s, t);
        System.out.println(minWindow);
    }
}
