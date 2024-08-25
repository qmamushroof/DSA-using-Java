// https://leetcode.com/problems/reverse-pairs/

import java.util.Arrays;

class Solution { // O(2nlogn) // O(n)
    public int reversePairs(int[] nums) {
        int arr[] = Arrays.copyOf(nums, nums.length);
        return mergeSort(arr, 0, arr.length - 1);
    }

    int mergeSort(int[] arr, int low, int high) {
        int count = 0;
        if (low >= high)
            return count;
        int mid = low + (high - low) / 2;
        count += mergeSort(arr, low, mid);
        count += mergeSort(arr, mid + 1, high);
        count += countPairs(arr, low, mid, high);
        merge(arr, low, mid, high);
        return count;
    }

    int countPairs(int[] arr, int low, int mid, int high) {
        int count = 0, right = mid + 1;
        for (int left = low; left <= mid; left++) {
            while (right <= high && arr[left] > 2 * (long) arr[right]) {
                right++;
            }
            count += right - (mid + 1);
        }
        return count;
    }

    void merge(int[] arr, int low, int mid, int high) {
        int temp[] = new int[high - low + 1];
        int left = low, right = mid + 1, k = 0;
        while (left <= mid && right <= high) {
            if (arr[left] <= arr[right])
                temp[k++] = arr[left++];
            else
                temp[k++] = arr[right++];
        }
        while (left <= mid)
            temp[k++] = arr[left++];
        while (right <= high)
            temp[k++] = arr[right++];
        for (int i = low; i <= high; i++)
            arr[i] = temp[i - low];
    }
}