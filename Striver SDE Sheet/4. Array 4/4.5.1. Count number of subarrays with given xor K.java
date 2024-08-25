// https://www.interviewbit.com/problems/subarray-with-given-xor/

import java.util.Map;
import java.util.HashMap;

class Solution {
    int countSubarrays(int[] arr, int target) { // T=O(n or n²) S=O(n)
        Map<Integer, Integer> map = new HashMap<>(); // <xor,count>
        int xor = 0, count = 0;
        map.put(0, 1);
        for (int i = 0; i < arr.length; i++) {
            xor ^= arr[i];
            int remaining = xor ^ target;
            count += map.getOrDefault(remaining, 0);
            map.put(xor, map.getOrDefault(xor, 0) + 1);
        }
        return count;
    }
}
