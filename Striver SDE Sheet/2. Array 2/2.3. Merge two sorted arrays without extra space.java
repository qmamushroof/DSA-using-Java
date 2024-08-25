// https://leetcode.com/problems/merge-sorted-array/description/

// The optimal solution in my notebook to a slightly different problem:
// https://takeuforward.org/data-structure/merge-two-sorted-arrays-without-extra-space/

import java.util.Arrays;

class PersonalBruteSolution {// O(m+n)log(m+n) O(1)
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for (int i = m, j = 0; i < m + n; i++, j++) {
            nums1[i] = nums2[j];
        }
        Arrays.sort(nums1);
    }
}

class BetterSolution {// O(m+n) O(1)
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1, k = m + n - 1;
        while (j >= 0) {
            if (i >= 0 && nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }
    }
}