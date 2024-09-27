// https://practice.geeksforgeeks.org/problems/job-sequencing-problem-1587115620/1#

import java.util.Arrays;

class Job {
    int id, profit, deadline;

    Job(int id, int dl, int p) {
        this.id = id;
        this.deadline = dl;
        this.profit = p;
    }
}

class Solution { // T=O(nlogn+n*MaxDeadline+n) S=O(MaxDeadline+n)
    int[] JobScheduling(Job arr[]) {
        int maxDeadline = 0;
        for (Job job : arr)
            maxDeadline = Math.max(maxDeadline, job.deadline);

        int hashArr[] = new int[maxDeadline + 1];

        Arrays.sort(arr, (a, b) -> Integer.compare(b.profit, a.profit));

        int count = 0, profit = 0;
        for (Job job : arr)
            for (int dl = job.deadline; dl > 0; dl--)
                if (hashArr[dl] == 0) {
                    count++;
                    profit += job.profit;
                    hashArr[dl] = job.id;
                    break;
                }
        
        return new int[]{count, profit};
    }
}