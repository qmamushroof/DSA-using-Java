// https://leetcode.com/problems/unique-paths/

import java.util.Arrays;

class Optimal {
    int countPaths(int rows, int cols) {// O(min(rows, cols))
        int n = rows + cols - 2;
        int r = Math.min(rows, cols) - 1;
        double ans = 1;
        for (int i = 0; i < r; i++) { // nCr
            ans *= (n - i);
            ans /= (i + 1);
        }
        return (int) ans;
    }
}

class GoodSuboptimalDP {// T=S=(O(m*n))
    int countPaths(int rows, int cols) {
        int dp[][] = new int[rows][cols];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        return countPathsUtil(0, 0, rows, cols, dp);
    }

    public int countPathsUtil(int i, int j, int rows, int cols, int dp[][]) {
        if (i > rows - 1 || j > cols - 1)
            return 0;
        if (i == rows - 1 && j == cols - 1)
            return 1;
        if (dp[i][j] != -1)
            return dp[i][j];
        return dp[i][j] = countPathsUtil(i + 1, j, rows, cols, dp) + countPathsUtil(i, j + 1, rows, cols, dp);
    }
}