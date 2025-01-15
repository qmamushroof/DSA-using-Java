/* Quazi Mushroof Abdullah - github.com/qmamushroof */
// https://www.geeksforgeeks.org/problems/subset-sums2234/1

import java.util.ArrayList;

// T=O(2^n) S=O(2^n)
// T=O(2^n + (2^n)log(2^n)) S=O(2*2^n)
class Solution {
    ArrayList<Integer> subsetSums(int arr[]) {
        ArrayList<Integer> sumList = new ArrayList<>();
        subsetSumsUtil(0, arr, 0, sumList);
        // sumList.sort((a, b) -> Integer.compare(a, b));
        return sumList;
    }

    void subsetSumsUtil(int idx, int arr[], int sum, ArrayList<Integer> sumList) {
        if (idx == arr.length) {
            sumList.add(sum);
            return;
        }
        subsetSumsUtil(idx + 1, arr, sum + arr[idx], sumList);
        subsetSumsUtil(idx + 1, arr, sum, sumList);
    }
}

// class Solution {
//     ArrayList<Integer> subsetSums(ArrayList<Integer> arr, int n) {
//         ArrayList<Integer> sumList = new ArrayList<>();
//         getSubsetsUtil(0, 0, arr, sumList);
//         sumList.sort((a, b) -> Integer.compare(a, b));
//         return sumList;
//     }
//     void getSubsetsUtil(int idx, int sum, ArrayList<Integer> arr, ArrayList<Integer> sumList) {
//         if (idx >= arr.size()) {
//             sumList.add(sum);
//             return;
//         }
//         getSubsetsUtil(idx + 1, sum + arr.get(idx), arr, sumList);
//         getSubsetsUtil(idx + 1, sum, arr, sumList);
//     }
// }
