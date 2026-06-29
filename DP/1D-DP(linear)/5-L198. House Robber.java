// You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, 
//   the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected 
//   and it will automatically contact the police if two adjacent houses were broken into on the same night.
// Given an integer array nums representing the amount of money of each house, return the maximum amount of money 
//   you can rob tonight without alerting the police.
// Example 1:
// Input: nums = [1,2,3,1]
// Output: 4
// Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
// Total amount you can rob = 1 + 3 = 4.

// 🧠 max = max(take + i-2, skip)

class Solution {
    public int rob(int[] nums) {
        int rob1 = 0; // i-2
        int rob2 = 0; // i-1
        
        for (int n : nums) {
            // take (n + rob1) OR skip (rob2)
            int temp = Math.max(n + rob1, rob2);
            
            rob1 = rob2; // shift
            rob2 = temp;
        }
        
        return rob2; // max till last
    }
}
