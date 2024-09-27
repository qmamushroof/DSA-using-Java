// https://practice.geeksforgeeks.org/problems/minimum-platforms-1587115620/1#

import java.util.ArrayList;

class Solution {
    int getMinPlatform(int arrival[], int departure[]) { // T=O(nlogn)=O(2nlog(2n)+4n) // S=O(2n)
        ArrayList<int[]> list = new ArrayList<>();

        for(int i=0; i<arrival.length; i++){ 
            list.add(new int[]{arrival[i],1});
        }

        for(int i=0; i<departure.length; i++){
            list.add(new int[]{departure[i],-1});
        }

        list.sort((a,b)->Integer.compare(a[0],b[0]));
        
        int count=0,maxCount=0;
        for(int[] arr: list){
            int arrivals = arr[1];
            count+=arrivals;
            maxCount=Math.max(count,maxCount);
        }
        
        return maxCount;
    }
}