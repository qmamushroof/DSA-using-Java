public class DivideAndConquer {

    public static void print_array(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void quick_sort(int arr[], int start, int end) {
        if (start >= end) {
            return;
        }
        int pivotIndex = partition(arr, start, end);
        quick_sort(arr, start, pivotIndex - 1);
        quick_sort(arr, pivotIndex + 1, end);
    }

    public static int partition(int arr[], int start, int end) {
        int pivot = arr[end];
        int i = start - 1;
        for (int j = start; j <= end; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
            }
        }
        return i;
    }

    public static void merge_sort(int arr[], int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = start + (end - start) / 2;
        merge_sort(arr, start, mid);
        merge_sort(arr, mid + 1, end);
        merge(arr, start, mid, end);
    }

    public static void merge(int arr[], int start, int mid, int end) {
        int temp[] = new int[end - start + 1];
        int i = start;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= end) {
            if (arr[i] <= arr[j]) {
                temp[k] = arr[i++];
            } else {
                temp[k] = arr[j++];
            }
            k++;
        }
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        while (j <= end) {
            temp[k++] = arr[j++];
        }
        for (i = start, k = 0; k < temp.length; i++, k++) {
            arr[i] = temp[k];
        }
    }

    public static int search_sorted_rotated_array(int[] arr, int target, int start, int end) {
        if (start > end) {
            return -1;
        }
        int mid = start + (end - start) / 2;
        if (arr[mid] == target) {
            return mid;
        }
        // if mid on left line
        if (arr[start] <= arr[mid]) {
            // left part of left line
            if (arr[start] <= target && target <= arr[mid]) {
                return search_sorted_rotated_array(arr, target, start, mid - 1);
            }
            // right part of left line
            else {
                return search_sorted_rotated_array(arr, target, mid + 1, end);
            }
        }
        // if mid on right line
        else {
            // right part of left line
            if (arr[mid] <= target && target <= arr[end]) {
                return search_sorted_rotated_array(arr, target, mid + 1, end);
            }
            // left part of right line
            else {
                return search_sorted_rotated_array(arr, target, start, mid - 1);
            }
        }
    }

    public static void main(String[] args) {
        // int arr[] = { 9, 8, 6, 4, 1, 2, 0, -1 };
        // merge_sort(arr, 0, arr.length - 1);
        // quick_sort(arr, 0, arr.length-1);
        // print_array(arr);

        int arr[] = { 4, 5, 6, 7, 0, 1, 2 };
        System.out.println(search_sorted_rotated_array(arr, 0, 0, arr.length));
    }
}
