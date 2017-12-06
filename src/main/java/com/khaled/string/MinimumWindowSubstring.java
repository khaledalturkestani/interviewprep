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

    private static void removeElement(Map<Character, Integer> map, char c) {
        Integer count = map.get(c);
        if (count != null) {
            count -= 1;
            if (count != 0) {
                map.put(c, count);
            } else {
                map.remove(c);
            }
        }
    }

    private static void addElement(Map<Character, Integer> map, char c) {
        Integer count = map.get(c);
        if (count == null) {
            count = 0;
        }
        map.put(c, count+1);
    }

    public static String minWindow(String S, String T) {
        List<CharIndex> orderedSeen = new LinkedList<>();
        Map<Character, Integer> remaining = new HashMap<>();
        Map<Character, Integer> seen = new HashMap<>();
        for (int i = 0; i < T.length(); i++)
            addElement(remaining, T.charAt(i));
        int curLen = 0;
        int start;
        for (start = 0; start < S.length(); start++){
            if (remaining.containsKey(S.charAt(start))) {
                removeElement(remaining, S.charAt(start));
                Character c = new Character(S.charAt(start));
                addElement(seen, c);
                orderedSeen.add(new CharIndex(c, start));
                break;
            }
        }
        int end;
        for (end = start+1; end < S.length(); end++) {
            char c = S.charAt(end);
            if (remaining.containsKey(c)) {
                addElement(seen, c);
                orderedSeen.add(new CharIndex(c, end));
                removeElement(remaining, c);
                curLen = end - start;
            } else if (orderedSeen.size() >= 2 && seen.containsKey(c) && c == S.charAt(start)) {
                int thisLen = end - orderedSeen.get(1).index;
                if (thisLen < curLen) {
                    start = orderedSeen.get(1).index;
                    curLen = thisLen;
                    orderedSeen.remove(0);
                    orderedSeen.add(new CharIndex(c, end));
                }
            } else if (orderedSeen.size() == 1 && orderedSeen.get(0).c == c) {
                start = end;
            }
        }

        if (orderedSeen.size() != T.length())
            return "";
        return S.substring(start, start+curLen+1);
    }

    public static void main(String[] args) {
        String s = "acbbaca";
        String t = "aba";
        String minWindow = minWindow(s, t);
        System.out.println(minWindow);
    }
}
