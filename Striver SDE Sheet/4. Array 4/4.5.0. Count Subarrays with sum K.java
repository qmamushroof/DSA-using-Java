// https://leetcode.com/problems/subarray-sum-equals-k/

import java.util.Map;
import java.util.HashMap;

class Solution {// T=O(n) or O(n²) S=O(n)
    int subarraySum(int[] arr, int target) {
        Map<Integer, Integer> map = new HashMap<>();// <sum,count>
        int prefixSum = 0, count = 0;

        map.put(0, 1);
        for (int i = 0; i < arr.length; i++) {
            prefixSum += arr[i];
            int remaining = prefixSum - target;
            count += map.getOrDefault(remaining, 0);
            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        }
        return count;
    }
}