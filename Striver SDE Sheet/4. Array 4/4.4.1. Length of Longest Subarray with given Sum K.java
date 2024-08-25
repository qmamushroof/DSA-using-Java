// https://www.codingninjas.com/codestudio/problems/longest-subarray-with-sum-k_5713505

import java.util.Map;
import java.util.HashMap;

class Solution {
	int getLongestSubarray(int[] arr, int target) {// T=O(n) and rarely T=O(n²) S=O(n)
		Map<Integer, Integer> prefixSums = new HashMap<>();
		int maxLength = 0, sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
			int remaining = sum - target;
			if (sum == target)
				maxLength = i + 1;
			if (prefixSums.containsKey(remaining)) {
				int length = i - prefixSums.get(remaining);
				maxLength = Math.max(maxLength, length);
			}
			if (!prefixSums.containsKey(sum))
				prefixSums.put(sum, i);
		}
		return maxLength;
	}
}