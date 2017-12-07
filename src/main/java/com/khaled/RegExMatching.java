package com.khaled;

/**
 * '.' Matches any single character.
 '*' Matches zero or more of the preceding element.

 The matching should cover the entire input string (not partial).

 The function prototype should be:
 bool isMatch(const char *s, const char *p)

 Some examples:
 isMatch("aa","a") → false
 isMatch("aa","aa") → true
 isMatch("aaa","aa") → false
 isMatch("aa", "a*") → true
 isMatch("aa", ".*") → true
 isMatch("ab", ".*") → true
 isMatch("aab", "c*a*b") → true

 * https://leetcode.com/problems/regular-expression-matching/description/
 */
public class RegExMatching {

    public static boolean isMatch(String s, String p) {
        return isMatchHelper(s, p, 0, 0);
    }

    private static boolean isMatchHelper(String s, String p, int sIdx, int pIdx) {
//        System.out.println("s: " + sIdx + ". p: " + pIdx);
        if (sIdx >= s.length() && pIdx >= p.length())
            return true;
        if (sIdx < s.length() && pIdx >= p.length())
            return false;
//        if (p.charAt(pIdx) == '*' || (pIdx+1 < p.length() && p.charAt(pIdx+1) == '*'))
//            return isMatchHelper(s, p, sIdx, pIdx+2);
//        char sChar = s.charAt(sIdx);
        char pChar = p.charAt(pIdx);
        if (pIdx+1 < p.length() && p.charAt(pIdx+1) == '*') {
            boolean zeroMatch = isMatchHelper(s, p, sIdx, pIdx+2);
            if (sIdx < s.length() && (pChar == s.charAt(sIdx) || pChar == '.')) {
                if (sIdx == s.length()-1) {
                    boolean both = isMatchHelper(s, p, sIdx+1, pIdx+2);
//                    boolean incrPattern = isMatchHelper(s, p, sIdx, pIdx+2);
//                    boolean incrString = isMatchHelper(s, p, sIdx+1, pIdx);
//                    zeroMatch = zeroMatch || both || incrPattern || incrString;
                    zeroMatch = zeroMatch || both;
                } else {
                    boolean both = isMatchHelper(s, p, sIdx+1, pIdx+2);
                    boolean incrPattern = isMatchHelper(s, p, sIdx+1, pIdx);
                    zeroMatch = zeroMatch || both || incrPattern;
                }
//                zeroMatch = zeroMatch || isMatchHelper(s, p, sIdx+1, pIdx);
            }
            return zeroMatch;
        } else if (sIdx < s.length() && (pChar == '.' || pChar == s.charAt(sIdx))) {
            return isMatchHelper(s, p, sIdx+1, pIdx+1);
        }
        return false;
    }

    public static void main(String[] args) {

        String s = "aba";
        String p = "a*b*";
        System.out.println("Exp: " + false);
        System.out.println("Got: " + isMatch(s, p));
    }
}
