/* Quazi Mushroof Abdullah - github.com/qmamushroof */
// https://leetcode.com/problems/subsets-ii/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// T = O(AvgSubsetLength*2^n + nlogn) 
// S = O(AvgSubsetLength*2^n + n)
class Solution {
    List<List<Integer>> subsetsWithDup(int[] nums) {
        int[] arr = Arrays.copyOf(nums, nums.length);
        Arrays.sort(arr);
        List<List<Integer>> allSubsets = new ArrayList<>();
        getSubsetsUtil(0, arr, new ArrayList<>(), allSubsets);
        return allSubsets;
    }
    void getSubsetsUtil(int idx, int[] arr, List<Integer> subset, List<List<Integer>> allSubsets) {
        allSubsets.add(new ArrayList<>(subset));
        for (int i = idx; i < arr.length; i++) {
            if (i != idx && arr[i] == arr[i - 1]) continue;
            subset.add(arr[i]);
            getSubsetsUtil(i + 1, arr, subset, allSubsets);
            subset.remove(subset.size() - 1);
        }
    }
}