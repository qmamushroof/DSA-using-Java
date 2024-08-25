// https://leetcode.com/problems/longest-substring-without-repeating-characters/

import java.util.Map;
import java.util.HashMap;

class ArrOptimal {
    public int lengthOfLongestSubstring(String str) { // T=O(n) S=O(1)
        if (str.length() <= 1)
            return str.length();

        int[] idxArr = new int[256]; // 256 chars in extended ASCII
        int longest = 0, left = 0, right = 0;

        while (right < str.length()) {
            char ch = str.charAt(right);
            if (idxArr[ch] > 0) // Check if it appeared previously
                left = Math.max(idxArr[ch], left); // idxArr stores i+1. Move left to last seen + 1 if greater than left
            idxArr[ch] = right + 1; // To differentiate betn i=0 and unseen
            longest = Math.max(right - left + 1, longest);
            right++;
        }
        return longest;
    }
}

class UsingMapInstead {
    public int lengthOfLongestSubstring(String str) { // T=O(n or n²) S=O(n)
        if (str.length() <= 1)
            return str.length();

        Map<Character, Integer> idxMap = new HashMap<>();
        int longest = 0, left = 0, right = 0;

        while (right < str.length()) {
            char ch = str.charAt(right);
            if (idxMap.containsKey(ch))
                left = Math.max(idxMap.get(ch) + 1, left);
            idxMap.put(ch, right);
            longest = Math.max(right - left + 1, longest);
            right++;
        }

        return longest;
    }
}