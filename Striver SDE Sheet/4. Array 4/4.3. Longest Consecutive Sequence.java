// https://leetcode.com/problems/longest-consecutive-sequence/

import java.util.HashSet;

class Solution {
    public int longestConsecutive(int[] arr) {// T=O(3n) S=O(n)
        if (arr.length == 0)
            return 0;

        HashSet<Integer> set = new HashSet<>();
        for (int number : arr) {
            set.add(number);
        }

        int maxLength = 1;
        for (int current : set) {// O(2n)=O(n+sum of lengths of all sequences)
            if (!set.contains(current - 1)) {
                int length = 1;
                int number = current;
                while (set.contains(number + 1)) {
                    length++;
                    number++;
                }
                maxLength = Math.max(length, maxLength);
            }
        }

        return maxLength;
    }
}