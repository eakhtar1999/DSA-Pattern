// https://takeuforward.org/plus/dsa/problems/subset-sum-equals-to-target
// Given an array arr of n integers and an integer target, determine if there is a subset of the given array with a sum equal to the given target.
// Example 1
// Input: arr = [1, 2, 7, 3], target = 6
// Output: True
// Explanation: There is a subset (1, 2, 3) with sum 6.

class Solution {
    public boolean isSubsetSum(int[] arr, int target) {

        // dp[i][j] = true if a subset from index 0..i can form sum j
        boolean[][] dp = new boolean[arr.length][target + 1];

        // Base case:
        // Sum 0 can always be formed by choosing no elements
        for (int i = 0; i < arr.length; i++) {
            dp[i][0] = true;
        }

        // Base case for first element:
        // Using only arr[0], we can form the sum arr[0]
        // Mark it only if arr[0] lies within the DP table range
        if (arr[0] <= target) {
            dp[0][arr[0]] = true;
        }

        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= target; j++) {

                // Option 1: Exclude the current element
                boolean notTaken = dp[i - 1][j];

                // Option 2: Include the current element
                // Possible only if arr[i] <= current target sum j
                boolean taken = false;
                if (arr[i] <= j) {
                    taken = dp[i - 1][j - arr[i]];
                }

                // Sum j is achievable if either choice works
                dp[i][j] = notTaken || taken;
            }
        }

        // Check whether the required target sum is achievable
        return dp[arr.length - 1][target];
    }
}
