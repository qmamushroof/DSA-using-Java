// Tabulation is slightly better than memoization which rarely causes Stack Overflow

import java.util.Arrays;
import java.util.HashSet;

public class DynamicProgramming {
    // Memoization
    public static int fibonacci(int n, int dp[]) { // O(n)
        if (n == 0 || n == 1) {
            return n;
        }
        if (dp[n] != 0) {
            return dp[n];
        }
        dp[n] = fibonacci(n - 1, dp) + fibonacci(n - 2, dp);
        return dp[n];
    }

    // Tabulation
    public static int fibonacci(int n) { // O(n)
        int dp[] = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    // Memoization
    public static int count_ways_for_climbing_stairs(int n, int ways[]) { // 1 or 2 step // O(n)
        if (n == 0) {
            return 1;
        }
        if (n < 0) {
            return 0;
        }
        ways[n] = count_ways_for_climbing_stairs(n - 1, ways) + count_ways_for_climbing_stairs(n - 2, ways);
        // for 1,2,3,... steps ways[n]=f(n-1)+f(n-2)+f(n-3)+...
        return ways[n];
    }

    // Tabulation
    public static int count_ways_for_climbing_stairs(int n) { // 1 or 2 step // O(n)
        int dp[] = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            if (i == 1) {
                dp[i] = dp[i - 1];
                continue;
            }
            dp[i] = dp[i - 1] + dp[i - 2]; // for 1,2,3,... steps ways[n]=f(n-1)+f(n-2)+f(n-3)+...
        }
        return dp[n];
    }

    // Recursion without DP
    public static int knapsack_0_1(int price[], int weight[], int capacity, int item) { // O(2^n)
        if (capacity == 0 || item == 0) {
            return 0;
        }
        if (weight[item - 1] > capacity) { // invalid
            return knapsack_0_1(price, weight, capacity, item - 1);
        } else { // valid
            int ans_including = price[item - 1] + knapsack_0_1(price, weight, capacity - weight[item - 1], item - 1);// include
            int ans_excluding = knapsack_0_1(price, weight, capacity, item - 1);// exclude
            return Math.max(ans_including, ans_excluding);
        }
    }

    // Memoization
    public static int knapsack_0_1(int price[], int weight[], int capacity, int item, int dp[][]) { // O(n*capacity)
        if (capacity == 0 || item == 0) {
            return 0;
        }
        if (dp[item][capacity] != -1) {
            return dp[item][capacity];
        }

        if (weight[item - 1] > capacity) { // invalid
            dp[item][capacity] = knapsack_0_1(price, weight, capacity, item - 1, dp);
            return dp[item][capacity];
        } else { // valid
            int ansIncluding = price[item - 1]
                    + knapsack_0_1(price, weight, capacity - weight[item - 1], item - 1, dp);
            int ansExcluding = knapsack_0_1(price, weight, capacity, item - 1, dp);
            dp[item][capacity] = Math.max(ansIncluding, ansExcluding);
            return dp[item][capacity];
        }
    }

    // Tabulation
    public static int knapsack_0_1(int price[], int weight[], int capacity) {// O(n*capacity)
        int items = price.length;
        int dp[][] = new int[items + 1][capacity + 1];
        for (int i = 0; i < dp.length; i++) {// 0th col
            dp[i][0] = 0;
        }
        for (int j = 0; j < dp.length; j++) {// 0th row
            dp[0][j] = 0;
        }

        for (int i = 1; i <= items; i++) {
            int Price = price[i - 1];
            int Weight = weight[i - 1];
            for (int j = 1; j <= capacity; j++) {
                if (Weight <= j) {// valid
                    int includingProfit = Price + dp[i - 1][j - Weight];
                    int excludingProfit = dp[i - 1][j];
                    dp[i][j] = Math.max(includingProfit, excludingProfit);
                } else {// invalid
                    int excludingProfit = dp[i - 1][j];
                    dp[i][j] = excludingProfit;
                }
            }
        }
        return dp[items][capacity];
    }

    // Tabulation
    public static boolean target_sum_subset(int number[], int sum) {// O(n*sum)
        boolean dp[][] = new boolean[number.length + 1][sum + 1];
        // i=numberIdx , j=targetSumIdx
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = true;
        }
        for (int i = 1; i <= number.length; i++) {
            for (int j = 1; j <= sum; j++) {
                int Number = number[i - 1];
                if (Number <= j && dp[i - 1][j - Number]) {// include
                    dp[i][j] = true;
                } else if (dp[i - 1][j]) {// exclude
                    dp[i][j] = true;
                }
            }
        }
        return dp[number.length][sum];
    }

    // Tabulation
    public static int unbounded_knapsack(int price[], int weight[], int capacity) {
        int items = price.length;
        int dp[][] = new int[items + 1][capacity + 1];
        for (int i = 0; i < dp.length; i++) {// redundant in java
            dp[i][0] = 0;
        }
        for (int j = 0; j < dp[0].length; j++) {// redundant in java
            dp[0][j] = 0;
        }

        for (int i = 1; i <= items; i++) {
            int Price = price[i - 1];
            int Weight = weight[i - 1];
            for (int j = 1; j <= capacity; j++) {
                if (Weight <= j) {// valid
                    int priceIncluding = Price + dp[i][j - Weight];
                    int priceExcluding = dp[i - 1][j];
                    dp[i][j] = Math.max(priceIncluding, priceExcluding);
                } else {// invalid
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[items][capacity];
    }
    // Rod cutting same as Unbounded knapsack
    // (price:piecesLength:totalRodLength = price:weight:capacity)

    // unbounded knapsack variation
    public static int coin_change_ways(int coins[], int sum) {// O(n*sum)
        int items = coins.length;
        int dp[][] = new int[items + 1][sum + 1];
        for (int i = 0; i <= items; i++) {
            dp[i][0] = 1;
        }
        for (int j = 1; j <= sum; j++) {
            dp[0][j] = 0;
        }
        for (int i = 1; i <= items; i++) {
            for (int j = 1; j <= sum; j++) {
                if (coins[i - 1] <= j) {// valid
                    int ans_including = dp[i][j - coins[i - 1]];
                    int ans_excluding = dp[i - 1][j];
                    dp[i][j] = ans_including + ans_excluding;
                } else {// invalid
                    int ans_excluding = dp[i - 1][j];
                    dp[i][j] = ans_excluding;
                }
            }
        }
        return dp[items][sum];
    }

    // Recursion without DP
    public static int LCS_length(String str1, String str2, int length1, int length2) {
        if (length1 == 0 || length2 == 0) {
            return 0;
        }
        if (str1.charAt(length1 - 1) == str2.charAt(length2 - 1)) {
            return LCS_length(str1, str2, length1 - 1, length2 - 1) + 1;
        } else {
            int ans1 = LCS_length(str1, str2, length1 - 1, length2);
            int ans2 = LCS_length(str1, str2, length1, length2 - 1);
            return Math.max(ans1, ans2);
        }
    }

    // Memoization
    public static int LCS_length(String str1, String str2, int length1, int length2, int dp[][]) { // O(n*m)
        if (length1 == 0 || length2 == 0) {
            return 0;
        }
        if (dp[length1][length2] != -1) {
            return dp[length1][length2];
        }

        if (str1.charAt(length1 - 1) == str2.charAt(length2 - 1)) {
            dp[length1][length2] = LCS_length(str1, str2, length1 - 1, length2 - 1) + 1;
            return dp[length1][length2];
        } else {
            int ans1 = LCS_length(str1, str2, length1 - 1, length2);
            int ans2 = LCS_length(str1, str2, length1, length2 - 1);
            dp[length1][length2] = Math.max(ans1, ans2);
            return dp[length1][length2];
        }
    }

    // Tabulation
    public static int LCS_length(String str1, String str2) { // O(n*m)
        int length1 = str1.length();
        int length2 = str2.length();

        // initialization
        int dp[][] = new int[length1 + 1][length2 + 1];
        for (int i = 0; i < dp.length; i++) {// redundant in java
            dp[i][0] = 0;
        }
        for (int j = 0; j < dp[0].length; j++) {// redundant in java
            dp[0][j] = 0;
        }

        for (int i = 1; i <= length1; i++) {
            for (int j = 1; j <= length2; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    int ans1 = dp[i - 1][j];
                    int ans2 = dp[i][j - 1];
                    dp[i][j] = Math.max(ans1, ans2);
                }
            }
        }

        return dp[length1][length2];
    }

    public static int longest_common_substring_length(String str1, String str2) {
        int length1 = str1.length();
        int length2 = str2.length();
        int dp[][] = new int[length1 + 1][length2 + 1];
        int ans = 0;

        // initialize
        for (int i = 0; i < dp.length; i++) {// redundant in java
            dp[i][0] = 0;
        }
        for (int j = 0; j < dp[0].length; j++) {// redundant in java
            dp[0][j] = 0;
        }

        for (int i = 1; i <= length1; i++) {
            for (int j = 1; j <= length2; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    ans = Math.max(ans, dp[i][j]);
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return ans;
    }

    public static int LCS_length(int arr1[], int arr2[]) {
        int length1 = arr1.length;
        int length2 = arr2.length;
        int dp[][] = new int[length1 + 1][length2 + 1];

        // initialize
        for (int i = 0; i < dp.length; i++) {// redundant in java
            dp[i][0] = 0;
        }
        for (int j = 0; j < dp[0].length; j++) {// redundant in java
            dp[0][j] = 0;
        }

        for (int i = 1; i <= length1; i++) {
            for (int j = 1; j <= length2; j++) {
                if (arr1[i - 1] == arr2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    int ans1 = dp[i - 1][j];
                    int ans2 = dp[i][j - 1];
                    dp[i][j] = Math.max(ans1, ans2);
                }
            }
        }

        return dp[length1][length2];
    }

    public static int longest_increasing_subsequence(int arr[]) {// O(n*unique(n))
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            set.add(arr[i]);
        }

        int arrSorted[] = new int[set.size()];
        int i = 0;
        for (int number : set) {
            arrSorted[i] = number;
            i++;
        }
        Arrays.sort(arrSorted);
        return LCS_length(arr, arrSorted);
    }

    public static int edit_distance(String word1, String word2) {// O(n*m)
        int length1 = word1.length();
        int length2 = word2.length();
        int dp[][] = new int[length1 + 1][length2 + 1];

        // intializing (redundant in java)
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j < dp[0].length; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= length1; i++) {
            for (int j = 1; j <= length2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int additionCost = dp[i][j - 1] + 1;
                    int deletionCost = dp[i - 1][j] + 1;
                    int substitutionCost = dp[i - 1][j - 1] + 1;
                    dp[i][j] = Math.min(Math.min(additionCost, deletionCost), substitutionCost);
                }
            }
        }
        return dp[length1][length2];
    }

    // string conversion
    public static int edit_distance_without_substitution(String word1, String word2) {
        int length1 = word1.length();
        int length2 = word2.length();

        int LCS_length = LCS_length(word1, word2);
        int additionCost = Math.abs(length1 - LCS_length);
        int deletionCost = Math.abs(length2 - LCS_length);

        return additionCost + deletionCost;
    }

    public static boolean wildcard_matching(String str, String pattern) {// O(n*m)
        int lengthStr = str.length();
        int lengthPattern = pattern.length();
        boolean dp[][] = new boolean[lengthStr + 1][lengthPattern + 1];

        // intialize
        dp[0][0] = true;
        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = false;
        }
        for (int j = 1; j < dp[0].length; j++) {
            if (pattern.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 1];
            }
        }

        for (int i = 1; i <= lengthStr; i++) {
            for (int j = 1; j <= lengthPattern; j++) {
                if (str.charAt(i - 1) == pattern.charAt(j - 1) || pattern.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pattern.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                } else {
                    dp[i][j] = false;
                }
            }
        }
        return dp[lengthStr][lengthPattern];
    }

    public static int catalan(int n, int dp[]) {
        if (n == 0 || n == 1) {
            return 1;
        }
        if (dp[n] != 0) {
            return dp[n];
        }

        for (int i = 0; i < n; i++) {
            dp[n] += catalan(i, dp) * catalan(n - i - 1, dp);
        }
        return dp[n];
    }

    // count_BSTs(n) = catalan(n)
    // count_mountain_ranges(n pairs of /\) = catalan(n)
    public static int catalan(int n) {// O(n^2)
        int dp[] = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp[n];
    }

    // recursion without DP
    public static int MCM_min_cost(int arr[], int i, int j) {
        if (i == j) {
            return 0;
        }

        int minCost = Integer.MAX_VALUE;

        for (int k = i; k < j; k++) {
            int cost1 = MCM_min_cost(arr, i, k);
            int cost2 = MCM_min_cost(arr, k + 1, j);
            int cost3 = arr[i - 1] * arr[k] * arr[j];
            int cost = cost1 + cost2 + cost3;
            minCost = Math.min(minCost, cost);
        }
        return minCost;
    }

    public static int MCM_min_cost(int arr[], int i, int j, int dp[][]) {
        if (i == j) {
            return 0;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int minCost = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            int cost1 = MCM_min_cost(arr, i, k, dp);
            int cost2 = MCM_min_cost(arr, k + 1, j, dp);
            int cost3 = arr[i - 1] * arr[k] * arr[j];
            int cost = cost1 + cost2 + cost3;
            minCost = Math.min(minCost, cost);
        }
        return dp[i][j] = minCost;
    }

    public static int MCM_min_cost(int arr[]) {
        int n = arr.length;
        int dp[][] = new int[n][n];

        // Intializing diagonals with 0
        for (int i = 0; i < n; i++) {
            dp[i][i] = 0;
        }

        for (int length = 2; length < n; length++) {
            for (int i = 1; i <= n - length; i++) {
                int j = i + length - 1;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int cost1 = dp[i][k];
                    int cost2 = dp[k + 1][j];
                    int cost3 = arr[i - 1] * arr[k] * arr[j];
                    int cost = cost1 + cost2 + cost3;
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }
        return dp[1][n - 1];
    }

    // mild modification of 0-1 knapsack
    public static int min_partition(int arr[]) {// AKA Minimum subset sum difference & Partitioning subsets
        int numbers = arr.length;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        int target = sum / 2;

        int dp[][] = new int[numbers + 1][target + 1];
        for (int i = 1; i <= numbers; i++) {
            for (int j = 1; j <= target; j++) {
                if (arr[i - 1] <= j) {// valid
                    int ansIncluding = arr[i - 1] + dp[i - 1][j - arr[i - 1]];
                    int ansExcluding = dp[i - 1][j];
                    dp[i][j] = Math.max(ansIncluding, ansExcluding);
                } else {// invalid
                    int ansExcluding = dp[i - 1][j];
                    dp[i][j] = ansExcluding;
                }
            }
        }
        int sum1 = dp[numbers][target];
        int sum2 = sum - sum1;
        return Math.abs(sum1 - sum2);
    }

    public static int min_array_jumps(int number[]) {
        int numbers = number.length;
        int dp[] = new int[numbers];
        Arrays.fill(dp, -1);

        dp[numbers - 1] = 0;
        for (int i = numbers - 2; i >= 0; i--) {
            int steps = number[i];
            int ans = Integer.MAX_VALUE;
            for (int j = i + 1; j <= i + steps && j < numbers; j++) {
                if (dp[j] != -1) {
                    ans = Math.min(ans, dp[j] + 1);
                }
            }
            if (ans != Integer.MAX_VALUE) {
                dp[i] = ans;
            }
        }

        return dp[0];
    }

    public static void main(String[] args) {
        // int dp[][] = new int[value.length + 1][W + 1];
        // for (int i = 0; i < dp.length; i++) {
        // for (int j = 0; j < dp[0].length; j++) {
        // dp[i][j] = -1;
        // }
        // }

        // int dp[][] = new int[arr.length][arr.length];
        // for (int i = 0; i < dp.length; i++) {
        // Arrays.fill(dp[i], -1);
        // }
    }
}