/* Quazi Mushroof Abdullah - github.com/qmamushroof */
// https://leetcode.com/problems/permutation-sequence/

import java.util.ArrayList;

// T=O(n²) S=O(n)
class Solution {
    public String getPermutation(int n, int k) {
        int factorial=1, position=k-1; //(n-1)!, 0-indexing-based position
        ArrayList<Integer> numbers = new ArrayList<>();
        for(int i=1; i<n; i++){
            factorial*=i;
            numbers.add(i);
        }
        numbers.add(n);

        String str="";
        while(!numbers.isEmpty()){
            str+=numbers.get(position/factorial);
            numbers.remove(position/factorial);
            position%=factorial;
            if(!numbers.isEmpty()) factorial/=numbers.size();
        }
        return str;
    }
}