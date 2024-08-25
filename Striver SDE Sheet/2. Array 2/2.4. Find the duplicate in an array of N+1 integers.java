// https://leetcode.com/problems/find-the-duplicate-number/description/

import java.util.Arrays;

class PersonalBruteSolution {
    public int findDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] == 0) {
                return nums[i];
            }
        }
        return -1;
    }
} // Hashing is more time optimal[O(n)] but S=O(n)

class OptimalSolution {// O(n) O(1)
    public int findDuplicate(int[] nums) {
        int slow = 0, fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        int slow2 = 0;
        while (slow != slow2) {
            slow = nums[slow];
            slow2 = nums[slow2];
        }
        return slow;
    }
}