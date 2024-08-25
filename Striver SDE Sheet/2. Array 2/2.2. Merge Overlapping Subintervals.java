// https://leetcode.com/problems/merge-intervals/

import java.util.Arrays;
import java.util.ArrayList;

class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        ArrayList<int[]> list = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            int start = intervals[i][0];
            int end = intervals[i][1];
            int lastIndex = list.size() - 1;

            if (list.isEmpty() || start > list.get(lastIndex)[1]) {
                list.add(intervals[i]);
            } else {
                list.get(lastIndex)[1] = Math.max(list.get(lastIndex)[1], end);
            }
        }
        return list.toArray(new int[list.size()][]);
    }
}