// https://www.naukri.com/code360/problems/count-inversions_615

import java.util.Arrays;

class PersonalBruteSolution {// O(n²)
    long getInversions(long arr[], int n) {
        long count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] > arr[j]) {
                    count++;
                }
            }
        }
        return count;
    }
}

class Solution {// O(nlogn) O(n)
    long getInversions(long arr[], int n) {
        long arr2[] = Arrays.copyOf(arr, arr.length);
        int low = 0, high = n - 1;
        return mergeSort(arr2, low, high);
    }

    long mergeSort(long arr[], int low, int high) {
        long count = 0;
        if (low == high)
            return count;
        int mid = (low + high) / 2;
        count += mergeSort(arr, low, mid);
        count += mergeSort(arr, mid + 1, high);
        count += merge(arr, low, mid, high);
        return count;
    }

    long merge(long arr[], int low, int mid, int high) {
        long temp[] = new long[high - low + 1];
        long count = 0;
        int left = low, right = mid + 1, k = 0;
        while (left <= mid && right <= high) {
            if (arr[left] <= arr[right]) {
                temp[k++] = arr[left++];
            } else {
                temp[k++] = arr[right++];
                count += mid - left + 1;
            }
        }
        while (left <= mid) {
            temp[k++] = arr[left++];
        }
        while (right <= high) {
            temp[k++] = arr[right++];
        }
        for (int i = low; i <= high; i++) {
            arr[i] = temp[i - low];
        }
        return count;
    }
}