// https://leetcode.com/problems/3sum/

import java.util.*;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {// T=O(n²+nlogn) S=O(triplets+logn)
        List<List<Integer>> ans = new ArrayList<>();
        int[] arr = Arrays.copyOf(nums, nums.length);
        Arrays.sort(arr);
        for (int i = 0; i < arr.length - 2; i++) {
            if (i > 0 && arr[i] == arr[i - 1])
                continue;
            int j = i + 1, k = arr.length - 1;
            while (j < k) {
                int sum = arr[i] + arr[j] + arr[k];
                if (sum < 0)
                    j++;
                else if (sum > 0)
                    k--;
                else {
                    List<Integer> triplet = Arrays.asList(arr[i], arr[j], arr[k]);
                    ans.add(triplet);
                    j++;
                    k--;
                    while (j < k && arr[j] == arr[j - 1])
                        j++;
                    while (j < k && arr[k] == arr[k + 1])
                        k--;
                }
            }
        }
        return ans;
    }
}