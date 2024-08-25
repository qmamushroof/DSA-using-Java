// https://leetcode.com/problems/search-a-2d-matrix/

class LeetcodeOptimal {
    public boolean searchMatrix(int matrix[][], int target) {// log(m*n) 1
        if (matrix.length == 0)
            return false;
        int rows = matrix.length;
        int cols = matrix[0].length;

        int low = 0, high = rows * cols - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (matrix[mid / cols][mid % cols] == target) {
                return true;
            }
            if (target > matrix[mid / cols][mid % cols]) {
                low = mid + 1;
            } else
                high = mid - 1;
        }
        return false;
    }
}

class GFG_optimal {
    public boolean searchMatrix(int matrix[][], int target) {// (m+n) 1
        if (matrix.length == 0)
            return false;
        int rows = matrix.length, cols = matrix[0].length;

        int i = 0, j = cols - 1;
        while (i < rows && j >= 0) {
            if (matrix[i][j] == target) {
                return true;
            }
            if (target > matrix[i][j]) {
                i++;
            } else
                j--;
        }
        return false;
    }
}