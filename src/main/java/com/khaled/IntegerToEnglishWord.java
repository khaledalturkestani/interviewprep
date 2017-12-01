package com.khaled;

/**
 * Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.
 * https://leetcode.com/problems/integer-to-english-words/description/
 */
public class IntegerToEnglishWord {

    private static int[] convertToArr(int n) {
        String str = Integer.toString(n);
        int[] arr = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            arr[i] = str.charAt(i)-'0';
        }
        return arr;
    }

    private static String constructNumberWord(int[] arr, int begin, int end) {
        String[] magnitudes = {"", "Thousand", "Million", "Billion", "Trillion"};
        String[] digits = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
        String[] teens = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
        String[] tens = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
        int len = end - begin;
        int i = begin;
        int mag = (arr.length-end) / 3;
        StringBuilder sb = new StringBuilder();
        if (len == 3) {
            if (arr[i] != 0)
                sb = sb.append(digits[arr[i]] + " Hundred ");
            i++;
        }
        if (len >= 2) {
            if (arr[i] == 0 && arr[i+1] != 0) {
                sb = sb.append(digits[arr[i+1]] + " ");
            } else if (arr[i] == 1) {
                sb = sb.append(teens[arr[i+1]] + " ");
            } else if (arr[i] > 1) {
                sb = sb.append(tens[arr[i++]] + " ");
                if (arr[i] > 0) {
                    sb = sb.append(digits[arr[i]] + " ");
                }
            }
        }
        if (len == 1) {
            sb = sb.append(digits[arr[i]] + " ");
        }
        if (mag > 0 && sb.length() > 0)
            sb = sb.append(magnitudes[mag] + " ");

        return sb.toString();
    }


    private static String intToEng(int num) {
        // TODO - throw exception if negative
        // Edge case:
        if (num == 0)
            return "Zero";
        // Convert to digits array
        int[] arr = convertToArr(num);
        int begin = 0;
        int end = (arr.length % 3 == 0) ? 3 : arr.length % 3;
        StringBuilder sb = new StringBuilder();
        while (begin < arr.length) {
            String chunk = constructNumberWord(arr, begin, end);
            sb = sb.append(chunk);
            begin = end;
            end += 3;
        }
        return sb.toString().trim();
    }

    public static void main(String[] args) {
//        String str = intToEng(1);
//        System.out.println("Exp: One");
//        System.out.println("Got: " + str);
//        String str1 = intToEng(123);
//        System.out.println("Exp: One Hundred Twenty Three");
//        System.out.println("Got: " + str1);
//        String str2 = intToEng(12345);
//        System.out.println("Exp: Twelve Thousand Three Hundred Forty Five");
//        System.out.println("Got: " + str2);
//        String str3 = intToEng(10001);
//        System.out.println("Exp: Ten Thousand One");
//        System.out.println("Got: " + str3);
//        String str4 = intToEng(1234567891);
//        System.out.println("Exp: One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One");
//        System.out.println("Got: " + str4);
        String str5 = intToEng(100000);
        System.out.println("Exp: One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One");
        System.out.println("Got: " + str5);
    }
}
