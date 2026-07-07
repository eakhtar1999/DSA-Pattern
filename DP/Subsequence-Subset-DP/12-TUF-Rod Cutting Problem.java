/* https://takeuforward.org/data-structure/rod-cutting-problem-dp-24
Problem Statement: Given a rod of length N inches and an array price[] where price[i] denotes the value of a piece 
of rod of length i inches (1-based indexing). Determine the maximum value obtainable by cutting up the rod and selling the pieces. 
Make any number of cuts, or none at all, and sell the resulting pieces.

Input : price = [1, 6, 8, 9, 10, 19, 7, 20], N = 8
Output :25
Explanation :Cut the rod into lengths of 2 and 6 for a total price of 6 + 19= 25.
*/


import java.util.*;

class Solution {
    // Function to solve the rod cutting problem
    public int rodCutting(int[] price, int n) {
        // Initialize DP table with dimensions [n][n + 1]
        int[][] dp = new int[n][n + 1];
        
        for (int length = 0; length <= n; length++) {
            dp[0][length] = price[0] * length;
        }
        
        // Fill the DP table
        for (int ind = 1; ind < n; ++ind) { 
            for (int length = 1; length <= n; ++length) { 
                
                // Case when the piece is not taken
                int notTaken = dp[ind - 1][length];
                
                // Case when the piece is taken
                int taken = Integer.MIN_VALUE;
                
                /* Length of the rod piece 
                corresponding to the current index*/
                int rodLength = ind + 1;
                
                // Check if the piece can be taken
                if (rodLength <= length) {
                    taken = price[ind] + dp[ind][length - rodLength];
                }
                
                /* Update dp[ind][length] with the maximum of
                including or not including the current piece*/
                dp[ind][length] = Math.max(notTaken, taken);
            }
        }
        
        // Return the result
        return dp[n - 1][n];
    }
