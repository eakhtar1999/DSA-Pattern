// Given an integer array nums, return true if you can partition the array into two subsets such that the sum of the elements in both subsets is equal or false otherwise.
// Example 1:
// Input: nums = [1,5,11,5]
// Output: true
// Explanation: The array can be partitioned as [1, 5, 5] and [11].

class Solution {
    // Determines if the given array can be split into two subsets
    // whose sums are equal using a tabulation DP approach
    public boolean canPartition(int[] arr) {
        int n = arr.length;

        // Step 1: Calculate total sum
        int totalSum = 0;
        for (int num : arr) totalSum += num;

        // Step 2: If total sum is odd, partition is impossible
        if (totalSum % 2 != 0) return false;

        // Step 3: Target sum for each subset
        int targetSum = totalSum / 2;

        // Step 4: Create DP table and initialize
        boolean[][] dp = new boolean[n][targetSum + 1];

        // Step 5: Base case: sum 0 is always possible
        for (int i = 0; i < n; i++) dp[i][0] = true;

        // Step 6: Initialize first row
        if (arr[0] <= targetSum) dp[0][arr[0]] = true;

        // Step 7: Fill DP table
        for (int index = 1; index < n; index++) {
            for (int target = 1; target <= targetSum; target++) {
                boolean notTaken = dp[index - 1][target];
                boolean taken = false;
                if (arr[index] <= target) {
                    taken = dp[index - 1][target - arr[index]];
                }
                dp[index][target] = notTaken || taken;
            }
        }

        // Step 8: Return result
        return dp[n - 1][targetSum];
    }
}

public class Main {
    public static void main(String[] args) {
        int[] arr = {2, 3, 3, 3, 4, 5};
        Solution solver = new Solution();

        if (solver.canPartition(arr))
            System.out.println("The array can be partitioned into two equal subsets");
        else
            System.out.println("The array cannot be partitioned into two equal subsets");
    }
}
