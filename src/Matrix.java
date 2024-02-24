import java.util.*;

public class Matrix {

    public static void print_matrix(int args[][]) {
        int i, j;
        for (i = 0; i < args.length; i++) {
            for (j = 0; j < args[0].length; j++) {
                System.out.print(args[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean search_matrix(int args[][], int key) {
        int i, j;
        for (i = 0; i < args.length; i++) {
            for (j = 0; j < args[0].length; j++) {
                if (args[i][j] == key) {
                    System.out.println("array[" + i + "]" + "[" + j + "]");
                    return true;
                }
            }
        }
        return false;
    }

    public static void print_spirally(int args[][]) {
        int startRow = 0;
        int endRow = args.length - 1;
        int startCol = 0;
        int endCol = args[0].length - 1;

        while (startRow <= endRow && startCol <= endCol) {
            // top
            for (int i = startCol; i <= endCol; i++) {
                System.out.print(args[startRow][i] + " ");
            }
            System.out.println();
            // right
            for (int i = startRow + 1; i <= endRow; i++) {
                System.out.print(args[i][endCol] + " ");
            }
            System.out.println();
            // bottom
            for (int i = endCol - 1; i >= startCol; i--) {
                if (startRow == endRow)
                    break;
                System.out.print(args[endRow][i] + " ");
            }
            System.out.println();
            // left
            for (int i = endRow - 1; i >= startRow + 1; i--) {
                if (startCol == endCol)
                    break;
                System.out.print(args[i][startCol] + " ");
            }
            System.out.println();

            startRow++;
            endRow--;
            startCol++;
            endCol--;
        }
    }

    public static int sum_diagonal(int args[][]) {
        int sum = 0;
        for (int i = 0; i < args.length; i++) {
            sum += args[i][i];
            if (i != args.length - 1 - i)
                sum += args[i][args.length - 1 - i];
        }
        return sum;
    }

    // only works for matrix whose each row and each column is sorted
    public static void staircase_search(int args[][], int key) {
        int row = 0, col = args.length - 1;
        while (row < args.length && col >= 0) {
            if (args[row][col] == key) {
                System.out.println("matrix[" + row + "][" + col + "]");
                return;
            } else if (key > args[row][col]) {
                row++;
            } else {
                col--;
            }
        }
        System.out.println("Not found");
    }

    public static void main(String[] args) {
        // Scanner sc = new Scanner(System.in);

        // int array[][] = new int[3][3];

        // int i, j;
        // for (i = 0; i < array.length; i++) {
        // for (j = 0; j < array[0].length; j++) {
        // array[i][j] = sc.nextInt();
        // }
        // }
        int array[][] = { { 1, 2, 3, 4 },
                { 5, 6, 7, 8 },
                { 9, 10, 11, 12 },
                { 13, 14, 15, 16 } };

        // print_matrix(array);
        // System.out.println(search_matrix(array, 7));

        // print_spirally(array);
        // System.out.println(sum_diagonal(array));

        staircase_search(array, 15);
    }
}
