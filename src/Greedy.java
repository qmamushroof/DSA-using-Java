import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class Greedy {
    public static ArrayList<Integer> max_acts(int[] start, int[] end) {
        int count;
        // sorting starts
        int activities[][] = new int[start.length][3];
        for (int i = 0; i < start.length; i++) {
            activities[i][0] = i;
            activities[i][1] = start[i];
            activities[i][2] = end[i];
        }
        Arrays.sort(activities, Comparator.comparingDouble(o -> o[2]));
        // sorting ends
        ArrayList<Integer> ans = new ArrayList<>();
        count = 1;
        ans.add(activities[0][0]);
        int lastEnd = activities[0][2];
        for (int i = 0; i < activities.length; i++) {
            if (activities[i][1] >= lastEnd) {
                ans.add(activities[i][0]);
                count++;
                lastEnd = activities[i][2];
            }
        }
        System.out.println("Max Activities:" + count);
        return ans;
    }

    public static int fractional_knapsack(int[] price, int[] weight, int limit) {
        double ratio[][] = new double[price.length][2];
        for (int i = 0; i < ratio.length; i++) {
            ratio[i][0] = i;
            ratio[i][1] = (double) price[i] / weight[i];
        }
        Arrays.sort(ratio, Comparator.comparingDouble(o -> o[1]));

        int remaining = limit;
        int answer = 0;
        for (int i = ratio.length - 1; i >= 0; i--) {
            int idx = (int) ratio[i][0];
            if (remaining >= weight[idx]) {
                answer += price[idx];
                remaining -= weight[idx];
            } else {
                answer += remaining * ratio[i][1];
                remaining = 0;
                break;
            }
        }
        return answer;

    }

    public static int sum_of_min_absolute_pairs(int[] arr1, int[] arr2) {
        int sum = 0;
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        for (int i = 0; i < arr1.length; i++) {
            sum += Math.abs(arr1[i] - arr2[i]);
        }
        return sum;
    }

    public static int max_length_chain_of_pairs(int[][] pairs) {
        Arrays.sort(pairs, Comparator.comparingDouble(o -> o[1]));
        int chainEnd = pairs[0][1], chain = 1;
        for (int i = 1; i < pairs.length; i++) {
            if (pairs[i][0] > chainEnd) {
                chain++;
                chainEnd = pairs[i][1];
            }
        }
        return chain;
    }

    public static ArrayList<Integer> min_banknotes(int amount) {
        Integer notes[] = { 1, 2, 5, 10, 20, 50, 100, 200, 500, 1000 };
        Arrays.sort(notes, Comparator.reverseOrder());
        ArrayList<Integer> ans = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < notes.length; i++) {
            if (notes[i] <= amount) {
                while (notes[i] <= amount) {
                    count++;
                    ans.add(notes[i]);
                    amount -= notes[i];
                    if (amount == 0) {
                        break;
                    }
                }
            }
        }
        System.out.println("Minimum " + count + " notes needed");
        return ans;
    }

    static class Job {
        int id;
        int deadline;
        int profit;

        Job(int id, int deadline, int profit) {
            this.id = id;
            this.deadline = deadline;
            this.profit = profit;
        }
    }

    public static ArrayList<Integer> job_sequence(int[][] jobsInfo) {
        ArrayList<Integer> seq = new ArrayList<>();
        ArrayList<Job> jobs = new ArrayList<>();
        for (int i = 0; i < jobsInfo.length; i++) {
            jobs.add(new Job(i, jobsInfo[i][0], jobsInfo[i][1]));
        }
        Collections.sort(jobs, (obj1, obj2) -> obj2.profit - obj1.profit);
        int currTime = 0, count = 0;
        for (int i = 0; i < jobs.size(); i++) {
            Job currJob = jobs.get(i);
            if (currJob.deadline > currTime) {
                count++;
                seq.add(currJob.id);
                currTime++;
            }
        }
        System.out.print(count + " jobs:");
        return seq;
    }

    public static int min_cutting_cost(Integer[] costHorizontal, Integer[] costVertical) {// chocola problem
        int cost = 0;
        Arrays.sort(costHorizontal, Comparator.reverseOrder());
        Arrays.sort(costVertical, Comparator.reverseOrder());
        int h = 0, v = 0, vertical_pieces = 1, horizontal_pieces = 1;
        while (h < costHorizontal.length && v < costVertical.length) {
            if (costHorizontal[h] > costVertical[v]) {
                cost += costHorizontal[h] * vertical_pieces;
                h++;
                horizontal_pieces++;
            } else {
                cost += costVertical[v] * horizontal_pieces;
                v++;
                vertical_pieces++;
            }
        }
        while (h < costHorizontal.length) {
            cost += costHorizontal[h] * vertical_pieces;
            h++;
            horizontal_pieces++;
        }
        while (v < costVertical.length) {
            cost += costVertical[v] * horizontal_pieces;
            v++;
            vertical_pieces++;
        }

        return cost;
    }

    public static void main(String[] args) {
        // int pairs[][] = { { 5, 24 }, { 39, 60 }, { 5, 28 }, { 27, 40 }, { 50, 90 } };
        // System.out.println(max_length_chain_of_pairs(pairs));
        // System.out.println(min_banknotes(2721));
        // int jobsInfo[][] = { { 4, 20 }, { 1, 10 }, { 1, 40 }, { 1, 30 } };
        // System.out.println(job_sequence(jobsInfo));
        Integer[] costV = { 2, 1, 3, 1, 4 };
        Integer[] costH = { 4, 1, 2 };
        System.out.println(min_cutting_cost(costH, costV));
    }
}
