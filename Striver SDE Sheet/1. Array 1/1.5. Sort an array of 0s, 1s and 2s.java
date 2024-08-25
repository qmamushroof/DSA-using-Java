//https://leetcode.com/problems/sort-colors/
class PersonalObviousSolution { // O(2n) O(1)
    public void sortColors(int[] nums) {
        int zeroes = 0, ones = 0, twos = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0)
                zeroes++;
            else if (nums[i] == 1)
                ones++;
            else
                twos++;
        }
        int i = 0;
        while (zeroes > 0) {
            nums[i] = 0;
            i++;
            zeroes--;
        }
        while (ones > 0) {
            nums[i] = 1;
            i++;
            ones--;
        }
        while (twos > 0) {
            nums[i] = 2;
            i++;
            twos--;
        }
    }
}

class Solution {// O(n) O(1)
    public void sortColors(int[] nums) {
        int low = 0, mid = 0, high = nums.length - 1;
        while (mid <= high) {
            if (nums[mid] == 0) {
                swap(nums, mid, low);
                mid++;
                low++;
            } else if (nums[mid] == 1) {
                mid++;
            } else {
                swap(nums, mid, high);
                high--;
            }
        }
    }

    void swap(int nums[], int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}