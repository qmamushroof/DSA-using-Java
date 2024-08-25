// https://leetcode.com/problems/majority-element-ii/

import java.util.ArrayList;

class Solution {
    public ArrayList<Integer> majorityElement(int arr[]) {// O(2n)
        int count1 = 0, count2 = 0, element1 = -1, element2 = -1, n = arr.length;
        for (int i = 0; i < n; i++) {
            if (count1 == 0 && arr[i] != element2) {
                count1 = 1;
                element1 = arr[i];
            } else if (count2 == 0 && arr[i] != element1) {
                count2 = 1;
                element2 = arr[i];
            } else if (element1 == arr[i])
                count1++;
            else if (element2 == arr[i])
                count2++;
            else {
                count1--;
                count2--;
            }
        }
        count1 = count2 = 0;
        int threshhold = n / 3;
        for (int i = 0; i < n; i++) {
            if (element1 == arr[i])
                count1++;
            else if (element2 == arr[i])
                count2++;
        }
        ArrayList<Integer> answer = new ArrayList<>();
        if (count1 > threshhold) {
            answer.add(element1);
        }
        if (count2 > threshhold) {
            answer.add(element2);
        }
        answer.sort((a, b) -> Integer.compare(a, b));
        return answer;
    }
}