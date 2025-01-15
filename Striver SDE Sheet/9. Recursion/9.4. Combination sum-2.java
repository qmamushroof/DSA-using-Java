/* Quazi Mushroof Abdullah - github.com/qmamushroof */
// https://leetcode.com/problems/combination-sum-ii/

// Individual arr[i] CANNOT be repeated
// Answer cannot include duplicates

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// T = O(AvgComboLength*2^n + nlogn) 
// S = O(AvgComboLength*2^n + n)
class Solution {
    List<List<Integer>> getCombinations(int[] nums, int target) {
        int[] arr = Arrays.copyOf(nums,nums.length);
        Arrays.sort(arr);
        List<List<Integer>> allCombos = new ArrayList<>();
        getCombinationsUtil(0, arr, target, new ArrayList<>(), allCombos);
        return allCombos;
    }
    void getCombinationsUtil(int idx, int[] arr, int target, List<Integer> combo, List<List<Integer>> allCombos) {
        if(target==0){
            allCombos.add(new ArrayList<>(combo));
            return;
        }
        for(int i=idx; i<arr.length; i++){
            if(i>idx && arr[i]==arr[i-1]) continue;
            if(arr[i]>target) break;
            combo.add(arr[i]);
            getCombinationsUtil(i+1, arr, target-arr[i], combo, allCombos);
            combo.remove(combo.size()-1);
        }
    }
}