// https://leetcode.com/problems/powx-n/

class Solution {
    public double power(double x, long n) {// O(logn) O(1)
        double ans = 1.0;
        boolean powerIsNegative = false;

        if (n < 0) {
            n *= -1; // removing negative sign if any
            powerIsNegative = true;
        }

        while (n > 0) {
            if (n % 2 == 0) {
                x *= x;
                n /= 2;
            } else {
                ans *= x;
                n--;
            }
        }

        if (powerIsNegative)
            ans = 1.0 / ans;

        return ans;
    }
}