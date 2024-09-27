// https://leetcode.com/problems/4sum/

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {// T=O(n³+nlogn) S=O(quads+logn)
        List<List<Integer>> ans = new ArrayList<>();
        int[] arr = Arrays.copyOf(nums, nums.length);
        Arrays.sort(arr);
        for (int i = 0; i < arr.length - 3; i++) {
            if (i > 0 && arr[i] == arr[i - 1])
                continue;
            for (int j = i + 1; j < arr.length - 2; j++) {
                if (j > i + 1 && arr[j] == arr[j - 1])
                    continue;
                int k = j + 1, l = arr.length - 1;
                while (k < l) {
                    long sum = arr[i] + arr[j];
                    sum += arr[k];
                    sum += arr[l];
                    if (sum < target)
                        k++;
                    else if (sum > target)
                        l--;
                    else {
                        List<Integer> quad = Arrays.asList(arr[i], arr[j], arr[k], arr[l]);
                        ans.add(quad);
                        k++;
                        l--;
                        while (k < l && arr[k] == arr[k - 1])
                            k++;
                        while (k < l && arr[l] == arr[l + 1])
                            l--;
                    }
                }
            }
        }
        return ans;
    }
}
