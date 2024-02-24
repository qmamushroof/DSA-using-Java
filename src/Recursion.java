import java.util.*;

public class Recursion {
    public static void printInDecreasingOrder(int n) {
        if (n == 0) {
            System.out.println(n);
            return;
        }
        System.out.print(n + " ");
        printInDecreasingOrder(n - 1);
    }

    public static void printInIncreasingOrder(int n) {
        if (n == 0) {
            System.out.print(n + " ");
            return;
        }
        printInIncreasingOrder(n - 1);
        System.out.print(n + " ");
    }

    public static int factorial(int n) {
        if (n == 0) {
            return 1;
        }
        int fn = n * factorial(n - 1);
        return fn;
    }

    public static int sum_of_1st_n_natural_numbers(int n) {
        if (n == 1) {
            return 1;
        }
        int fn = n + sum_of_1st_n_natural_numbers(n - 1);
        return fn;
    }

    public static int fibonacci(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        int f_n_minus_1 = fibonacci(n - 1);
        int f_n_minus_2 = fibonacci(n - 2);
        return f_n_minus_1 + f_n_minus_2;
    }

    public static boolean check_if_array_is_sorted_ascendingly(int array[], int i) {
        if (i == array.length - 1)
            return true;
        if (array[i] > array[i + 1])
            return false;
        return check_if_array_is_sorted_ascendingly(array, i + 1);
    }

    public static int first_index_of_number_in_array(int array[], int key, int i) {
        if (i == array.length) {
            return -1;
        }
        if (array[i] == key) {
            return i;
        }
        return first_index_of_number_in_array(array, key, i + 1);
    }

    public static int last_index_of_number_in_array(int array[], int key, int i) {
        if (i == array.length) {
            return -1;
        }
        int is_found = last_index_of_number_in_array(array, key, i + 1);
        if (is_found == -1 && array[i] == key) {
            return i;
        }
        return is_found;
    }

    public static int optimized_power(int x, int n) {
        if (n == 0) {
            return 1;
        }
        int half_power = optimized_power(x, n / 2);
        int half_power_square = half_power * half_power;

        if (n % 2 != 0) {
            return x * half_power_square;
        }
        return half_power_square;
    }

    public static int tiling_problem(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        int horizontal_ways = tiling_problem(n - 1);
        int vertical_ways = tiling_problem(n - 2);
        return horizontal_ways + vertical_ways;
    }

    public static void remove_duplicate_from_string(String given_string, StringBuilder answer, int i, boolean map[]) {
        if (i == given_string.length()) {
            System.out.println(answer);
            return;
        }
        char current_char = given_string.charAt(i);
        if (map[current_char - 'a'] == true) {
            remove_duplicate_from_string(given_string, answer, i + 1, map);
        } else {
            map[current_char - 'a'] = true;
            remove_duplicate_from_string(given_string, answer.append(current_char), i + 1, map);
        }
    }

    public static int pairing_friends(int n) {
        if (n == 1 || n == 2)
            return n;
        return pairing_friends(n - 1) + (n - 1) * pairing_friends(n - 2);
    }

    public static void print_all_binary_strings_of_length_n_without_consecutive_1s(int n, int last_number, String s) {
        if (n == 0) {
            System.out.println(s);
            return;
        }
        print_all_binary_strings_of_length_n_without_consecutive_1s(n - 1, 0, s + "0");
        if (last_number == 0) {
            print_all_binary_strings_of_length_n_without_consecutive_1s(n - 1, 1, s + "1");
        }
    }

    public static void main(String[] args) {
        // Scanner scn = new Scanner(System.in);
        // int n = scn.nextInt();
        // scn.close();

        // printInDecreasingOrder(n);
        // printInIncreasingOrder(n);
        // System.out.println(factorial(n));
        // System.out.println(sum_of_1st_n_natural_numbers(n));
        // System.out.println(fibonacci(n));

        // int array[] = {0,1,3,6,3,2,1,0,9};
        // System.out.println(check_if_array_is_sorted_ascendingly(array,0));
        // int key = 3;
        // System.out.println(first_index_of_number_in_array(array, key, 0));
        // System.out.println(last_index_of_number_in_array(array, key, 0));

        // System.out.println(optimized_power(2, 10));
        // System.out.println(tiling_problem(20));

        // String str = "aaabbbcccddeeff";
        // remove_duplicate_from_string(str, new StringBuilder(""), 0, new boolean[26]);
        // System.out.println(pairing_friends(3));
        print_all_binary_strings_of_length_n_without_consecutive_1s(3, 0, "");
    }
}