// https://leetcode.com/problems/rotate-image/
class Solution { // O(n²) O(1)
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // Transpose
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                swap(matrix, i, j);
            }
        }
        // Reverse rows
        for (int i = 0; i < n; i++) {
            reverseRow(matrix[i], i, n - 1);
        }
    }

    void swap(int matrix[][], int i, int j) {
        int temp = matrix[i][j];
        matrix[i][j] = matrix[j][i];
        matrix[j][i] = temp;
    }

    void reverseRow(int row[], int start, int end) {
        start = 0;
        end = row.length - 1;
        while (start < end) {
            int temp = row[start];
            row[start] = row[end];
            row[end] = temp;
            start++;
            end--;
        }
    }
}