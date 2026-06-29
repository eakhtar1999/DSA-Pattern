// You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. 
// All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. 
// Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police 
// if two adjacent houses were broken into on the same night.
// Given an integer array nums representing the amount of money of each house, return the maximum amount of money 
// you can rob tonight without alerting the police.
// Example 1:
// Input: nums = [2,3,2]
// Output: 3
// Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.

class Solution {
    public int rob(int[] nums) {
        if (nums.length == 1) return nums[0]; // single house case

        // case1: skip first
        int case1 = robHelper(nums, 1, nums.length);

        // case2: skip last
        int case2 = robHelper(nums, 0, nums.length - 1);

        return Math.max(case1, case2);
    }

    // same logic as your House Robber code, just on a subarray
    private int robHelper(int[] nums, int start, int end) {
        int rob1 = 0; // i-2
        int rob2 = 0; // i-1

        for (int i = start; i < end; i++) {
            int temp = Math.max(nums[i] + rob1, rob2); // take or skip
            rob1 = rob2;
            rob2 = temp;
        }

        return rob2;
    }
}
