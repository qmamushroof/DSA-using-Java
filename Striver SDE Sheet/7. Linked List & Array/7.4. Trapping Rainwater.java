// https://leetcode.com/problems/trapping-rain-water/

class Solution {
    public int trap(int[] height) { //T=O(n)
        int left=0, right=height.length-1, leftMax=0, rightMax=0, trapped=0;
        while(left < right){
            if(height[left]<=height[right]){
                if(height[left]>leftMax) leftMax=height[left];
                else trapped += leftMax-height[left];
                left++;
            } else{
                if(height[right]>rightMax) rightMax=height[right];
                else trapped += rightMax-height[right];
                right--;
            }
        }
        return trapped;
    }
}