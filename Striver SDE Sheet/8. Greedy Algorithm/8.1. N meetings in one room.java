// https://www.geeksforgeeks.org/problems/n-meetings-in-one-room-1587115620/1

import java.util.ArrayList;

class Solution { // T=O(nlogn +2n)

    // ArrayList<Integer> maxMeetings(int n, int start[], int end[]) { // S=O(2n)

    public int maxMeetings(int n, int start[], int end[]) { // S=O(n)
        ArrayList<int[]> meeting = new ArrayList<>();
        for(int i=0; i<n; i++) meeting.add(new int[]{start[i],end[i],i}); // O(n)
            
        meeting.sort((a,b)->Integer.compare(a[1],b[1])); // O(nlogn)
        
        int count=1, lastEnd=meeting.get(0)[1];
        ArrayList<Integer> sequence = new ArrayList<>();
        sequence.add(meeting.get(0)[2]);
        for(int i=1; i<n; i++){ // O(n)
            if(meeting.get(i)[0]>lastEnd){
                count++;
                lastEnd = meeting.get(i)[1];
                sequence.add(meeting.get(i)[2]);
            }
        }
        
        return count;

        // return sequence;
    }
}