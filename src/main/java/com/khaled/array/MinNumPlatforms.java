package com.khaled.array;

import com.khaled.sort.QuickSort;

import java.util.Stack;

/**
 * Given arrival and departure times of all trains that reach a railway station, find the minimum number of platforms
 * required for the railway station so that no train waits.
 */
public class MinNumPlatforms {

    public static int minPlaforms(int[] arr, int[] dep) {
        // First sort both arrays
        QuickSort.sort(arr);
        QuickSort.sort(dep);

        int arrIndx = 0;
        int depIndx = 0;
        int maxPlatforms = 0;
        Stack<Integer> stack = new Stack<Integer>();
        while (arrIndx < arr.length) {
            if (arr[arrIndx] <= dep[depIndx]) {
                stack.push(arr[arrIndx]);
                if (stack.size() > maxPlatforms)
                    maxPlatforms = stack.size();
                arrIndx++;
            } else {
                stack.pop();
                depIndx++;
            }
        }

        return maxPlatforms;
    }

    public static void main(String args[]) {
        int arr[] = {900, 940, 950, 1100, 1500, 1800};
        int dep[] = {910, 1200, 1120, 1130, 1900, 2000};
        System.out.println("Min. Number of Platforms: " + minPlaforms(arr, dep));
    }
}
