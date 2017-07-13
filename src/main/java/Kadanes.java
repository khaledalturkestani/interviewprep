/**
 * Largest sum of contiguous subarray problem.
 */
public class Kadanes {

    public static int largestSum(int[] arr) {
        if (arr == null || arr.length == 0)
            return Integer.MIN_VALUE;

        int largestSoFar = arr[0];
        int largestSeen = arr[0];
        for (int i = 1; i < arr.length; i++) {
            largestSoFar += arr[i];
            if (largestSoFar < 0) {
                largestSoFar = 0;
            }
            if (largestSeen < largestSoFar) {
                largestSeen = largestSoFar;
            }
        }
        return largestSeen;
    }

    public static void main(String args[]) {
        // Expected output is sum of {4, -1, -2, 1, 5} = 7
        int[] arr = {-2, -3, 4, -1, -2, 1, 5, -3};
        int largestSum = largestSum(arr);
        System.out.println(largestSum);

        // Expected output is 5
        int[] arr1 = {-2, -3, -4, -1, -2, -1, -5, -5};
        largestSum = largestSum(arr1);
        System.out.println(largestSum);

        // Expected output is 0
        int[] arr2 = {-2, -3, 4, -1, -2, -1, -5, 5};
        largestSum = largestSum(arr2);
        System.out.println(largestSum);
    }

}
