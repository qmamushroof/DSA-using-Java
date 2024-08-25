// https://www.naukri.com/code360/problems/longest-subarray-with-sum-k_6682399

class NonNegativeOnly {
    int subarraySum(int[] arr, int target) {// T=O(2n)
        int left = 0, right = 0, sum = arr[0], maxLength = 0;
        while (right < arr.length) {
            while (left <= right && sum > target)
                sum -= arr[left++];
            if (sum == target)
                maxLength = Math.max(maxLength, right - left + 1);
            right++;
            if (right < arr.length)
                sum += arr[right];
        }
        return maxLength;
    }
}