// You are climbing a staircase with n + 1 steps, numbered from 0 to n.
// You are also given a 1-indexed integer array costs of length n, where costs[i] is the cost of step i.
// From step i, you can jump only to step i + 1, i + 2, or i + 3. 
// The cost of jumping from step i to step j is defined as: costs[j] + (j - i)2
// You start from step 0 with cost = 0.Return the minimum total cost to reach step n.
// Example 1: Input: n = 4, costs = [1,2,3,4]
// Output: 13
// Explanation: One optimal path is 0 → 1 → 2 → 4
// Jump	Cost Calculation	Cost
// 0 → 1	costs[1] + (1 - 0)2 = 1 + 1	2
// 1 → 2	costs[2] + (2 - 1)2 = 2 + 1	3
// 2 → 4	costs[4] + (4 - 2)2 = 4 + 4	8
// Thus, the minimum total cost is 2 + 3 + 8 = 13

class Solution {
    public int climbStairsSpaceOptimised(int n, int[] costs) {
        int a = 0, b = Integer.MAX_VALUE, c = Integer.MAX_VALUE;

        for (int i = 1; i <= n; i++) {
            int one = a + 1; // 1^2
            int two = (i - 2 >= 0) ? b + 4 : Integer.MAX_VALUE; // 2^2
            int three = (i - 3 >= 0) ? c + 9 : Integer.MAX_VALUE; // 3^2
            // dp[i] = cost[i] + best previous
            int curr = costs[i - 1] + Math.min(one, Math.min(two, three));

            c = b;
            b = a;
            a = curr;
        }

        return a;
    }
}

Time: O(n) — single pass
Space: O(1) — only 3 variables used
  


class Solution {
    public int climbStairs(int n, int[] costs) {

        int[] dp = new int[n + 1];
        dp[0] = 0; // start

        for (int i = 1; i <= n; i++) {

            // from i-1, i-2, i-3
            int one = dp[i - 1] + 1;              // 1^2
            int two = (i >= 2) ? dp[i - 2] + 4 : Integer.MAX_VALUE; // 2^2
            int three = (i >= 3) ? dp[i - 3] + 9 : Integer.MAX_VALUE; // 3^2

            // dp[i] = cost[i] + best previous
            dp[i] = costs[i - 1] + Math.min(one, Math.min(two, three));
        }

        return dp[n];
    }
}


