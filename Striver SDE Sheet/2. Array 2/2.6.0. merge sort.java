import java.util.ArrayList;

class Solution {
    static void mergeSortUtil(int[] arr, int low, int high) {
        if (low == high)
            return;
        int mid = (low + high) / 2;
        mergeSortUtil(arr, low, mid);
        mergeSortUtil(arr, mid + 1, high);
        merge(arr, low, mid, high);
    }

    static void merge(int[] arr, int low, int mid, int high) {
        ArrayList<Integer> temp = new ArrayList<>();
        int left = low, right = mid + 1;
        while (left <= mid && right <= high) {
            if (arr[left] <= arr[right]) {
                temp.add(arr[left]);
                left++;
            } else {
                temp.add(arr[right]);
                right++;
            }
        }

        while (left <= mid) {
            temp.add(arr[left]);
            left++;
        }
        while (right <= high) {
            temp.add(arr[right]);
            right++;
        }

        for (int i = low; i <= high; i++) {
            arr[i] = temp.get(i - low);
        }
    }

    public static void mergeSort(int[] arr, int n) {
        mergeSortUtil(arr, 0, n - 1);
    }
}