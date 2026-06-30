// Given a string s, find the longest palindromic subsequence's length in s.
// A subsequence is a sequence that can be derived from another sequence by deleting some or no elements 
//   without changing the order of the remaining elements.
// Example 1:
// Input: s = "bbbab"
// Output: 4
// Explanation: One possible longest palindromic subsequence is "bbbb".

// HINT : LCS of s and reverse(s)

import java.util.*;

class Solution {

    /* Helper function to calculate the length of the
       Longest Common Subsequence (LCS) between s1 and s2 */
    private int func(String s1, String s2) {
        // Length of first string
        int n = s1.length();
        // Length of second string
        int m = s2.length();

        // 2D DP array: dp[i][j] stores LCS length of s1[0..i-1] and s2[0..j-1]
        int[][] dp = new int[n + 1][m + 1];

        // Initialize first row to 0 (when one string is empty)
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }
        // Initialize first column to 0 (when the other string is empty)
        for (int i = 0; i <= m; i++) {
            dp[0][i] = 0;
        }

        // Fill DP table
        for (int ind1 = 1; ind1 <= n; ind1++) {
            for (int ind2 = 1; ind2 <= m; ind2++) {
                // If current characters match, add 1 to the LCS of previous characters
                if (s1.charAt(ind1 - 1) == s2.charAt(ind2 - 1)) {
                    dp[ind1][ind2] = 1 + dp[ind1 - 1][ind2 - 1];
                } 
                // If they don't match, take the max by ignoring one character from either string
                else {
                    dp[ind1][ind2] = Math.max(dp[ind1 - 1][ind2], dp[ind1][ind2 - 1]);
                }
            }
        }

        // LCS length is stored in the bottom-right cell
        return dp[n][m];
    }

    /* Function to calculate the length of the Longest Palindromic Subsequence */
    public int longestPalinSubseq(String s) {
        // Reverse the original string
        String t = new StringBuilder(s).reverse().toString();

        // LPS is just the LCS between the string and its reverse
        return func(s, t);
    }

    public static void main(String[] args) {
        // Example input string
        String s = "bbabcbcab";
        
        // Create an instance of Solution
        Solution sol = new Solution();
        
        // Print the result
        System.out.println("The Length of Longest Palindromic Subsequence is " + sol.longestPalinSubseq(s));
    }
}
