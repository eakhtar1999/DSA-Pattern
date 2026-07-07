/*
Problem Statement: A thief wants to rob a store. He is carrying a bag of capacity W. The store has ‘n’ items of infinite supply. 
Its weight is given by the ‘wt’ array and its value by the ‘val’ array. He can either include an item in its 
knapsack or exclude it but can’t partially have it as a fraction. We need to find the maximum value of items 
that the thief can steal. He can take a single item any number of times he wants and put it in his knapsack .

Input: n = 3, W = 8, wt = [2, 4, 6], val = [5, 11, 13]
Output: 22
Explanation:We can take item with weight 2 (value 5) four times to fill capacity 8,total value = 5 × 4 = 20.
But a better choice: take item with weight 2 (value 5) twice and item with weight 4 (value 11) once → total weight = 2 + 2 + 4 = 8, total value = 5 + 5 + 11 = 21.
Even better: take two items with weight 4 (value 11 each), total value = 22, which is maximum.
*/

import java.util.*;

class Solution {
    // Function to solve unbounded knapsack using tabulation
    public int unboundedKnapsack(int n, int W, int[] val, int[] wt) {
        // Create DP table where dp[i][j] stores max value for i items and capacity j
        int[][] dp = new int[n][W + 1];

        // Base condition: fill first row using infinite supply of first item
        for (int i = wt[0]; i <= W; i++) {
            dp[0][i] = (i / wt[0]) * val[0];
        }

        // Loop through remaining items
        for (int ind = 1; ind < n; ind++) {
            // Loop through all capacities
            for (int cap = 0; cap <= W; cap++) {
                // Case 1: Not take current item
                int notTaken = dp[ind - 1][cap];

                // Case 2: Take current item
                int taken = Integer.MIN_VALUE;
                if (wt[ind] <= cap) {
                    taken = val[ind] + dp[ind][cap - wt[ind]];
                }

                // Store the best value
                dp[ind][cap] = Math.max(notTaken, taken);
            }
        }

        // Return result
        return dp[n - 1][W];
    }
}
