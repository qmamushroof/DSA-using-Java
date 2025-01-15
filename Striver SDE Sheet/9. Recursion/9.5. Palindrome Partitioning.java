/* Quazi Mushroof Abdullah - github.com/qmamushroof */
// https://leetcode.com/problems/palindrome-partitioning/

import java.util.ArrayList;
import java.util.List;

// T = O(AvgPartitionLength*2^n*(n/2)) 
// S = O(AvgPartitionLength*2^n)

class Solution {
    List<List<String>> partition(String str) {
        List<List<String>> allPartitions = new ArrayList<>();
        partitionUtil(0, str, new ArrayList<>(), allPartitions);
        return allPartitions;
    }
    void partitionUtil(int idx, String str, List<String> partition, List<List<String>> allPartitions){
        if(idx==str.length()){
            allPartitions.add(new ArrayList<>(partition));
            return;
        }
        for(int i=idx; i<str.length(); i++)
            if(isPalindrome(str,idx,i)){
                partition.add(str.substring(idx,i+1));
                partitionUtil(i+1,str,partition,allPartitions);
                partition.remove(partition.size()-1);
            }
    }
    boolean isPalindrome(String str, int start, int end){
        while(start<=end)
            if(str.charAt(start++)!=str.charAt(end--)) return false;
        return true;
    }
}