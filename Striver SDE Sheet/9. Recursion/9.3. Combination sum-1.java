/* Quazi Mushroof Abdullah - github.com/qmamushroof */
// https://leetcode.com/problems/combination-sum/

// Individual arr[i] CAN be repeated

import java.util.ArrayList;
import java.util.List;

// T=O((2^target)*AvgComboLength)
// S=O((2^n)*AvgComboLength  + target)
class Solution {
    List<List<Integer>> getCombinations(int[] arr, int target) {
        List<List<Integer>> allCombos = new ArrayList<>();
        getCombinationsUtil(0, arr, target, new ArrayList<>(), allCombos);
        return allCombos;
    }

    void getCombinationsUtil(int idx, int[] arr, int target, List<Integer> combo, List<List<Integer>> allCombos) {
        if (idx == arr.length) {
            if (target == 0)
                allCombos.add(new ArrayList<>(combo));
            return;
        }
        if (arr[idx] <= target) {
            combo.add(arr[idx]);
            getCombinationsUtil(idx, arr, target - arr[idx], combo, allCombos);
            combo.remove(combo.size() - 1);
        }
        getCombinationsUtil(idx + 1, arr, target, combo, allCombos);
    }

    // SLightly less optimized
    void getCombinationsUtilPersonal(int idx, int[] arr, int target, List<Integer> combo, List<List<Integer>> allCombos) {
        if (target < 0) return;
        if (idx == arr.length) {
            if (target == 0) allCombos.add(new ArrayList<>(combo));
            return;
        }

        // Include the current element and explore further.
        combo.add(arr[idx]);
        getCombinationsUtil(idx, arr, target - arr[idx], combo, allCombos);

        // Backtrack and exclude the current element.
        combo.remove(combo.size() - 1);
        getCombinationsUtil(idx + 1, arr, target, combo, allCombos);
    }
}