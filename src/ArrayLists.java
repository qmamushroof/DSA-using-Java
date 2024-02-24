import java.util.ArrayList;

public class ArrayLists {

    // brute force
    public static int max_water_unoptimized(ArrayList<Integer> line) {
        int maxWater = 0;
        for (int i = 0; i < line.size(); i++) {
            for (int j = i + 1; j < line.size(); j++) {
                int height = Math.min(line.get(i), line.get(j));
                int width = j - i;
                maxWater = Math.max(maxWater, height * width);
            }
        }
        return maxWater;
    }

    // O(n): 2 pointer appraoch
    public static int max_water_optimized(ArrayList<Integer> line) {
        int maxWater = 0;
        int left = 0, right = line.size() - 1;
        while (left < right) {
            int height = Math.min(line.get(left), line.get(right));
            int width = right - left;
            maxWater = Math.max(maxWater, height * width);

            if (line.get(left) < line.get(right)) {
                left++;
            } else {
                right--;
            }
        }
        return maxWater;
    }

    // brute force
    public static boolean pair_sum_unoptimized(ArrayList<Integer> numbers, int target) {
        for (int i = 0; i < numbers.size(); i++) {
            for (int j = i + 1; j < numbers.size(); j++) {
                if (numbers.get(i) + numbers.get(j) == target) {
                    return true;
                }
            }
        }
        return false;
    }

    // O(n): 2 pointer appraoch
    public static boolean pair_sum_1_optimized(ArrayList<Integer> sorted_numbers, int target) {
        int left = 0, right = sorted_numbers.size() - 1;
        while (left < right) {
            if (sorted_numbers.get(left) + sorted_numbers.get(right) == target) {
                return true;
            } else if (sorted_numbers.get(left) < sorted_numbers.get(right)) {
                left++;
            } else {
                right--;
            }
        }
        return false;
    }

    // O(n): 2 pointer appraoch
    public static boolean pair_sum_2_optimized(ArrayList<Integer> sortedAndRotatedNumbers, int target) {
        int pivot = -1;
        for (int i = 0; i < sortedAndRotatedNumbers.size(); i++) {
            if (sortedAndRotatedNumbers.get(i) > sortedAndRotatedNumbers.get(i + 1)) {
                pivot = i;
                break;
            }
        }
        int right = pivot;
        int left = pivot + 1;
        while (left != right) {
            if (sortedAndRotatedNumbers.get(left) + sortedAndRotatedNumbers.get(right) == target) {
                return true;
            } else if (sortedAndRotatedNumbers.get(left) + sortedAndRotatedNumbers.get(right) < target) {
                left = (left + 1) % sortedAndRotatedNumbers.size();
            } else {
                right = (sortedAndRotatedNumbers.size() + right - 1) % sortedAndRotatedNumbers.size();
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // int arr[] = { 1, 8, 6, 2, 5, 4, 8, 3, 7 };
        // int arr[] = { 1, 2, 3, 4 };
        int arr[] = { 3, 4, 1, 2 };
        ArrayList<Integer> line = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            line.add(arr[i]);
        }
        // System.out.println(max_water_optimized(line));
        System.out.println(pair_sum_2_optimized(line, 6));
    }
}
