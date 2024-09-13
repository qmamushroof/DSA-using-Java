// https://leetcode.com/problems/max-consecutive-ones/

class Solution {
    int findMaxConsecutiveOnes(int[] arr) { // T=O(n)
        int length = 0, maxLength = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                length++;
                maxLength = Math.max(length, maxLength);
            } else
                length = 0;
        }
        return maxLength;
    }
}