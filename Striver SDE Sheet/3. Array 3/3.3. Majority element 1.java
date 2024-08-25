// https://leetcode.com/problems/majority-element/

class Solution {// O(2n) //Moore's voting algo
    int majorityElement(int arr[]) {
        int count = 0, element = -1;
        for (int i = 0; i < arr.length; i++) { // finding the only possible majority element
            if (count == 0) {
                count = 1;
                element = arr[i];
                continue;
            }
            if (element == arr[i]) {
                count++;
            } else {
                count--;
            }
        }

        count = 0;
        for (int i = 0; i < arr.length; i++) {// checking if that possible one is the majority element
            if (element == arr[i]) {
                count++;
            }
            if (count > arr.length / 2) {
                return element;
            }
        }
        return -1;// if no majority element exists
    }
}
// I have a O(nlogn) solution where 1st sort the array
// then if (arr[0]==arr[n/2+1] || arr[n-1]==arr[n/2-1]) return arr[n/2] else -1

// A T=O(nlogn+n) & S=O(n) solution exists using hashmap