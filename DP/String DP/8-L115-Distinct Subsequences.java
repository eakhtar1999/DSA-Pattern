// Given two strings s and t, return the number of distinct subsequences of s which equals t.
// The test cases are generated so that the answer fits on a 32-bit signed integer.
// Example 1:
// Input: s = "rabbbit", t = "rabbit"
// Output: 3
// Explanation: As shown below, there are 3 ways you can generate "rabbit" from s.
// ra[b][b]bit
// ra[b]b[b]it
// rab[b][b]it


import java.util.*;

class Solution {
    // Function to count distinct subsequences
    public int numDistinct(String s, String t) {

        // Get lengths of both strings
        int m = s.length();
        int n = t.length();

        // Create dp table of size (m+1) x (n+1)
        long[][] dp = new long[m + 1][n + 1];

        // Base case: empty t can be formed from any suffix of s
        for (int i = 0; i <= m; i++) {
            dp[i][n] = 1;
        }

        // Fill dp table from bottom to top
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {

                // If characters match, we have two options:
                // 1. Take this character -> dp[i+1][j+1]
                // 2. Skip this character -> dp[i+1][j]
                if (s.charAt(i) == t.charAt(j)) {
                    dp[i][j] = dp[i + 1][j + 1] + dp[i + 1][j];
                } else {

                    // If characters don't match, we can only skip
                    dp[i][j] = dp[i + 1][j];
                }
            }
        }

        // Final answer is stored at dp[0][0]
        return (int) dp[0][0];
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        String s = "babgbag";
        String t = "bag";

        System.out.println(sol.numDistinct(s, t));
    }
}
