
// Given an array of distinct integers nums and a target integer target, return the number of possible combinations that add up to target.
// The test cases are generated so that the answer can fit in a 32-bit integer.
// Example 1:
// Input: nums = [1,2,3], target = 4
// Output: 7
// Explanation: The possible combination ways are:
// (1, 1, 1, 1)
// (1, 1, 2)
// (1, 2, 1)
// (1, 3)
// (2, 1, 1)
// (2, 2)
// (3, 1)
// Note that different sequences are counted as different combinations.

// Pattern - Looks like unbounded knapsack (count ways) but with order-sensitive variation
// Similar to:
//     Climbing stairs (when nums = {1, 2})
//     Counting sequences to reach target

class Solution {
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;//→ one way to make 0 (pick nothing)

        //Order matters → this is permutation-based counting, not combination
        //That’s why outer loop is i and inner loop is nums
        for (int i = 1; i <= target; i++) {
            for (int n : nums) {
                if (n <= i) {
                    dp[i] += dp[i - n];
                }
            }
        }

        return dp[target];        
    }
}
