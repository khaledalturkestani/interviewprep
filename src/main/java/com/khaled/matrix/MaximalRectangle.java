package com.khaled.matrix;

/**
 * Created by khaledalturkestani on 12/13/17.
 */
public class MaximalRectangle {

    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0)
            return 0;

        int m = matrix.length;
        int n = matrix[0].length;

        int[][][] dp = new int[m][n][2];

        for (int i = 0; i < m; i++) {
            dp[i][0][0] = (matrix[i][0] == '1') ? 1 : 0;
            dp[i][0][1] = (matrix[i][0] == '1') ? 1 : 0;
        }

        for (int j = 0; j < n; j++) {
            dp[0][j][0] = (matrix[0][j] == '1') ? 1 : 0;
            dp[0][j][1] = (matrix[0][j] == '1') ? 1 : 0;
        }

        int max = 0;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                    int i1 = dp[i-1][j][0];
                    int i2 = dp[i][j-1][0];
                    int j1 = dp[i-1][j][1];
                    int j2 = dp[i][j-1][1];
                    if (matrix[i][j] == '1') {
                        if (matrix[i-1][j] == '1' && matrix[i][j-1] == '1') {
                            dp[i][j][0] = 1 + Math.min(i1, i2);
                            dp[i][j][1] = 1 + Math.min(j1, j2);
                        } else if (matrix[i-1][j] == '1') {
                            dp[i][j][0] = 1 ;
                            dp[i][j][1] = 1 + dp[i-1][j][0];
                        } else if (matrix[i][j-1] == '1') {
                            dp[i][j][0] = 1 + dp[i][j-1][1];
                            dp[i][j][1] = 1;
                        } else {
                            dp[i][j][0] = 1;
                            dp[i][j][1] = 1;
                        }
                    }

                int prod = dp[i][j][0]*dp[i][j][1];
                if (prod > max)
                    max = prod;
            }
        }

        return max;
    }

    public static void main(String[] args) {
        MaximalRectangle mr = new MaximalRectangle();
        char[][] matrix = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        System.out.println(mr.maximalRectangle(matrix));
    }
}
