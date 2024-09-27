// https://www.geeksforgeeks.org/find-minimum-number-of-coins-that-make-a-change/
// https://leetcode.com/problems/coin-change/description/

// THIS CODE WILL NOT WORK IN ALL CASES

import java.util.ArrayList;

// T=O(target) S=O(coin)=O(target)
class Solution {
    ArrayList<Integer> minCoins(int arr[], int target) {
        ArrayList<Integer> coinList = new ArrayList<>();

        int count = 0, remainingTarget = target;
        for (int i = arr.length - 1; i >= 0; i--) {
            while (remainingTarget >= arr[i]) {
                coinList.add(arr[i]);
                count++;
                remainingTarget -= arr[i];
            }
        }
        // if (remainingAmount > 0) count=-1;
        return coinList;
    }
}
