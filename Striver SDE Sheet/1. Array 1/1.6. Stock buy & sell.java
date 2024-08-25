// https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
class Solution {
    public int maxProfit(int[] prices) {// O(n) O(1)
        int profit = 0;
        int prevMin = prices[0];
        for (int i = 1; i < prices.length; i++) {
            int change = prices[i] - prevMin;
            profit = Math.max(change, profit);
            prevMin = Math.min(prevMin, prices[i]);
        }
        return profit;
    }
}