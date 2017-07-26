package com.khaled.array;

/**
 * Given n non-negative integers in array representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
 * For example:
 * Input:
 * [2 0 2]
 * Output:
 * 2
 * Structure is like below
 * | |
 * |_|
 */
public class TrappingRainWater {
    public static int volume(int[] arr) {
        int volume = 0;
        int beginIndx = 0;
        // Find first non-zero element
        while (arr[beginIndx] == 0 && beginIndx < arr.length)
            beginIndx++;
        int beginHeight = arr[beginIndx];
        int endHeight = 0;
        int endIndx = beginIndx+1;
        while (endIndx < arr.length) {
            if (arr[endIndx] > endHeight)
                endHeight = arr[endIndx];
            if (endHeight >= beginHeight || endIndx == arr.length-1) {
                int height = Math.min(beginHeight, endHeight);
                for (int k = beginIndx+1; k < endIndx; k++) {
                    volume += (height - arr[k]);
                }
                beginIndx = endIndx;
            }
            endIndx++;
        }
        return volume;
    }

    public static void main(String args[]) {
        int[] arr1 = {7, 4, 0, 9};
        System.out.println("Expected volume: 10. Got: " + volume(arr1));

        int[] arr2 = {6, 9, 9};
        System.out.println("Expected volume: 0. Got: " + volume(arr2));
    }
}
