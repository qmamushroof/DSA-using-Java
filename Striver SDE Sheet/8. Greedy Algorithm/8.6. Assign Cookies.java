// https://leetcode.com/problems/assign-cookies/

import java.util.Arrays;

// T=O(nlogn+mlogm+n) S=O(n+m)
class Solution {
    int assignedCookies(int greed[], int size[]) {
        Arrays.sort(greed);
        Arrays.sort(size);
        int i = 0, j = 0;
        while (i < greed.length && j < size.length) {
            if (greed[i] <= size[j]) i++;
            j++;
        }
        return i;
    }
}
