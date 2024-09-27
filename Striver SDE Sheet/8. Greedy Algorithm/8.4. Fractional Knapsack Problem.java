// https://practice.geeksforgeeks.org/problems/fractional-knapsack-1587115620/1

import java.util.Arrays;

class Item {
    int value, weight;

    Item(int value, int weight) {
        this.value = value;
        this.weight = weight;
    }
}

class Solution {
    double fractionalKnapsack(Item arr[], int capacity) { // T=O(nlogn+n) // S=O(n)
        Arrays.sort(arr, (a, b) -> Double.compare((double)b.value / b.weight, (double)a.value / a.weight));

        double value = 0, remainingCapacity = capacity;
        for (Item item : arr) {
            if (item.weight <= remainingCapacity) {
                value += item.value;
                remainingCapacity -= item.weight;
            } else {
                value += (double)item.value / item.weight * remainingCapacity;
                break;
            }
        }
        return value;
    }
}
