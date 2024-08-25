// https://leetcode.com/problems/maximum-subarray/

// A more obvious solution is a brute force appraoch using 2 loops
class Solution { // T=O(n) S=O(1)
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        // startIdx=endIdx=-1;
        for (int i = 0; i < nums.length; i++) {
            // if(sum==0) startIdx=i;
            sum += nums[i];
            if (sum > max) {
                max = sum;
            }
            if (sum < 0) {
                sum = 0;
                // endIdx=i;
            }
        }
        return max;
    }
}
