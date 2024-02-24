import java.util.*;

public class Sort {

    public static void bubble_sort(int args[]) {
        for (int turn = 0; turn < args.length - 1; turn++) {
            int swap_count = 0;
            for (int j = 0; j < args.length - 1 - turn; j++) {
                if (args[j] > args[j + 1]) {
                    int temp = args[j];
                    args[j] = args[j + 1];
                    args[j + 1] = temp;

                    swap_count++;
                }
                if (swap_count == 0) {
                    break;
                }
            }
        }
    }

    public static void selection_sort(int args[]) {
        for (int i = 0; i < args.length - 1; i++) {
            int min_position = i;
            for (int j = i + 1; j < args.length; j++) {
                if (args[min_position] > args[j]) {
                    min_position = j;
                }
            }
            int temp = args[min_position];
            args[min_position] = args[i];
            args[i] = temp;
        }
    }

    public static void insertion_sort(int args[]) {
        for (int i = 1; i < args.length; i++) {
            int current_element = args[i];
            int previous_index = i - 1;

            // finding correct position to insert
            while (previous_index >= 0 && args[previous_index] > current_element) {
                args[previous_index + 1] = args[previous_index];
                previous_index--;
            }
            // insertion
            args[previous_index + 1] = current_element;
        }
    }

    // counting sort should be used when the largest element is not very large
    public static void counting_sort(int args[]) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < args.length; i++) {
            max = Math.max(max, args[i]);
        }
        int count[] = new int[max + 1];
        for (int i = 0; i < args.length; i++) {
            count[args[i]]++;
        }
        int j = 0;
        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) {
                args[j] = i;
                j++;
                count[i]--;
            }
        }

    }

    public static void main(String[] args) {
        int arr[] = { 0, 5, 4, 3, 2, 1 ,0 };

        counting_sort(arr);

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
