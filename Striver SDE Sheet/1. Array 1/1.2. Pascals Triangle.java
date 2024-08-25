import java.util.List;
import java.util.ArrayList;

// https://leetcode.com/problems/pascals-triangle/
// T=O(n^2) S=O(n^2)
class PersonalSubOptimalSpaceSolution {
    public List<List<Integer>> generate(int numRows) {
        int dp[][] = new int[numRows][numRows];

        // initializing the beginning and ending of every row with 0
        for (int i = 0; i < numRows; i++) {
            dp[i][0] = 1;
            dp[i][i] = 1;
        }

        // inner box's value = upper value + upper left value
        for (int i = 2; i < numRows; i++) { // travel all rows
            for (int j = 1; j < i; j++) { // travel just until before the edge of every row
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
            }
        }

        // Converting dp array to list
        List<List<Integer>> outerList = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> innerList = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                innerList.add(dp[i][j]);
            }
            outerList.add(innerList);
        }
        return outerList;
    }
}

// T=O(n^2) S=O(1)
class Solution {
    public List<Integer> generateRow(int row) {
        List<Integer> innerList = new ArrayList<>();
        double value = 1;
        innerList.add((int) value);
        for (int col = 1; col < row; col++) {
            value = value * (row - col);
            value = value / col;
            innerList.add((int) value);
        }
        return innerList;
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> outerList = new ArrayList<>();

        for (int row = 1; row <= numRows; row++) {
            outerList.add(generateRow(row));
        }
        return outerList;
    }
}