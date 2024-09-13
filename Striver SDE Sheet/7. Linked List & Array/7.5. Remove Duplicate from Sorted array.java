// https://leetcode.com/problems/remove-duplicates-from-sorted-array/

class Solution {

    int removeDuplicates(int[] arr) { // T=O(n)
        int left = 0;
        for (int right = 1; right < arr.length; right++) {
            if (arr[left] != arr[right]) {
                arr[++left] = arr[right];
            }
        }
        return left + 1;
    }
}
