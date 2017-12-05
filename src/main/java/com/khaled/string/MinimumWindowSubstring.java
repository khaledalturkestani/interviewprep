package com.khaled.string;

import com.google.common.collect.HashMultiset;

import java.util.*;

/**
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 *
 * For example,
 * S = "ADOBECODEBANC"
 * T = "ABC"
 * Minimum window is "BANC".
 *
 *  https://leetcode.com/problems/minimum-window-substring/description/
 */
public class MinimumWindowSubstring {

    public static class CharIndex {
        Character c;
        int index;
        public CharIndex(Character c, int index) {
            this.c = c;
            this.index= index;
        }
    }

    public static String minWindow(String S, String T) {
        List<CharIndex> orderedSeen = new LinkedList<>();
        Collection<Character> remaining = HashMultiset.create();
        Collection<Character> seen = HashMultiset.create();
        for (int i = 0; i < T.length(); i++)
            remaining.add(T.charAt(i));
        int curLen = 0;
        int start;
        for (start = 0; start < S.length(); start++){
            if (remaining.contains(S.charAt(start))) {
                remaining.remove(S.charAt(start));
                Character c = new Character(S.charAt(start));
                seen.add(c);
                orderedSeen.add(new CharIndex(c, start));
                break;
            }
        }
        int end;
        for (end = start+1; end < S.length(); end++) {
            char c = S.charAt(end);
            if (remaining.contains(c)) {
                seen.add(c);
                orderedSeen.add(new CharIndex(c, end));
                remaining.remove(c);
                curLen = end - start;
            } else if (seen.size() >= 2 && seen.contains(c) && c == S.charAt(start)) {
                int thisLen = end - orderedSeen.get(1).index;
                if (thisLen < curLen) {
                    start = orderedSeen.get(1).index;
                    curLen = thisLen;
                    orderedSeen.remove(0);
                    orderedSeen.add(new CharIndex(c, end));
                }
            }
        }

        if (curLen == 0)
            return "";
        return S.substring(start, start+curLen+1);
    }

    public static void main(String[] args) {
        String s = "acdvb";
        String t = "z";
        String minWindow = minWindow(s, t);
        System.out.println(minWindow);
    }
}
