// https://www.interviewbit.com/problems/repeat-and-missing-number-array/

class WrongSolutionBasedOnPreviousApproach {
    // DO NOT MODIFY THE ARGUMENTS WITH "final" PREFIX. IT IS READ ONLY
    public int[] repeatedNumber(final int[] A) {
        int slow = 0, fast = 0, missing = 0;
        do {
            slow = A[slow];
            fast = A[A[fast]];
        } while (slow != fast);

        int slow2 = 0;
        while (slow != fast) {
            slow = A[slow];
            slow2 = A[slow2];
        }

        for (int i = 0; i < A.length; i++) {
            if (slow != A[i]) {
                missing = A[i];
                break;
            }
        }
        int[] arr = { slow, missing };
        return arr;
    }
}

class OptimalSolution {// O(n) O(1)
    // DO NOT MODIFY THE ARGUMENTS WITH "final" PREFIX. IT IS READ ONLY
    public int[] repeatedNumber(final int[] A) {
        int x = -1, y = -1;
        long n = A.length;// x->repeated y->missing
        long sumN = n * (n + 1) / 2;
        long sumNsq = n * (n + 1) * (2 * n + 1) / 6;

        long sum = 0, sumSq = 0;
        for (int i = 0; i < n; i++) {// O(n)
            sum += (long) A[i];
            sumSq += (long) A[i] * (long) A[i];
        }
        // sum-x+y=sumN
        long xMINUSy = sum - sumN;

        // sumSq-x2+y2=sumNsq
        long x2MINUSy2 = sumSq - sumNsq;

        long xPLUSy = x2MINUSy2 / xMINUSy;// x+y=(x2-y2)/(x-y)

        x = (int) ((xPLUSy + xMINUSy) / 2);
        y = (int) (xPLUSy - x);

        int[] arr = { x, y };
        return arr;
    }
}