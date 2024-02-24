public class Backtracking {
    public static void print_array(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
    public static void print_array(int board[][]) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }

    public static void change_array(int[] arr, int i, int value) {
        if (i == arr.length) {
            print_array(arr);
            return;
        }
        arr[i] += value;
        change_array(arr, i + 1, value + 1);
        arr[i] -= 2;
    }

    public static void print_subsets(String str, int i, String ans) {
        if (i == str.length()) {
            if (ans == "") {
                System.out.println("NULL");
            } else {
                System.out.println(ans);
            }
            return;
        }
        print_subsets(str, i + 1, ans + str.charAt(i));
        print_subsets(str, i + 1, ans);
    }

    public static void print_permutations(String str, String ans) {
        if (str.length() == 0) {
            System.out.println(ans);
            return;
        }
        for (int i = 0; i < str.length(); i++) {
            char current = str.charAt(i);
            String newStr = str.substring(0, i) + str.substring(i + 1);
            print_permutations(newStr, ans + current);
        }
    }

    public static void nQueen(char board[][], int row) {
        if (row == board.length) {
            print_board(board);
            nQueenCount++;
            return;
            // return true;
        }

        for (int col = 0; col < board.length; col++) {
            if (is_safe(board, row, col)) {
                board[row][col] = 'Q';
                nQueen(board, row + 1);
                // if (nQueen(board, row + 1))
                // return true;
                board[row][col] = 'X';
            }
        }
        // return false;
    }

    public static boolean is_safe(char board[][], int row, int col) {
        for (int i = row - 1; i >= 0; i--) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }

    public static void print_board(char board[][]) {
        System.out.println("Chess Board:");
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }

    public static void initialize_board(char board[][]) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
                board[row][col] = 'x';
            }
        }
    }

    // unoptimized: T(n)=O(2^(row+col))
    public static int grid_ways(int x, int y, int row, int col) {
        if (x == row - 1 && y == col - 1) {
            return 1;
        } else if (x == row || y == col) {
            return 0;
        }
        return grid_ways(x + 1, y, row, col) + grid_ways(x, y + 1, row, col);
    }

    public static int factorial(int n) {
        if (n == 1 || n == 0) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    // T(n)=O(n) using permutation
    public static int grid_ways(int row, int col) {
        return factorial(row - 1 + col - 1) / (factorial(row - 1) * factorial(col - 1));
    }

    static int nQueenCount = 0;

    public static boolean is_safe(int sudoku[][], int row, int col, int digit) {
        for (int i = 0; i < sudoku.length; i++) {
            // row
            if (sudoku[row][i] == digit) {
                return false;
            }
            // col
            if (sudoku[i][col] == digit) {
                return false;
            }
        }
        // grid
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (sudoku[i][j] == digit) {
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean solve_sudoku(int sudoku[][], int row, int col) {
        if (row == sudoku.length && col == 0) {
            return true;
        }
        int nextRow = row, nextCol = col + 1;
        if (col == sudoku.length - 1) {
            nextRow++;
            nextCol = 0;
        }
        if (sudoku[row][col] != 0) {
            return solve_sudoku(sudoku, nextRow, nextCol);
        }

        for (int digit = 1; digit <= 9; digit++) {
            if (is_safe(sudoku, row, col, digit)) {
                sudoku[row][col] = digit;
                if (solve_sudoku(sudoku, nextRow, nextCol))
                    return true;
                sudoku[row][col] = 0;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // int arr[] = new int[5];
        // change_array(arr, 0, 1);
        // print_array(arr);

        // print_subsets("abc", 0, "");
        // print_permutations("abc", "");

        // int n = 4;
        // char board[][] = new char[n][n];
        // initialize_board(board);
        // nQueen(board, 0);

        // System.out.println(grid_ways(0, 0, 3, 3));
        // System.out.println(grid_ways(3, 3));

        int sudoku[][] = { { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
        solve_sudoku(sudoku, 0,0);
        print_array(sudoku);
    }
}
