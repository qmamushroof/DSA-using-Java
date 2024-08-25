// https://leetcode.com/problems/two-sum/

import java.util.HashMap;
import java.util.Arrays;

class indexVariant {
    public int[] twoSum(int[] arr, int target) { // T=O(nlogn) S=O(n)
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int remaining = target - arr[i];
            if (map.containsKey(remaining)) {
                return new int[] { map.get(remaining), i };
            }
            map.put(arr[i], i);
        }
        return null;
    }
}

class checkVariant {
    public boolean sumExists(int[] nums, int target) {// T=O(nlogn+n) S=O(logn)
        int left = 0, right = nums.length - 1;
        Arrays.sort(nums);
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum < target) {
                left++;
            } else if (sum > target)
                right--;
            else {
                return true;
            }
        }
        return false;
    }
}