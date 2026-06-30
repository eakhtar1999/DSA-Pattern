// Given two strings text1 and text2, return the length of their longest common subsequence. 
//   If there is no common subsequence, return 0.
// A subsequence of a string is a new string generated from the original string with some characters (can be none) 
//   deleted without changing the relative order of the remaining characters.
// For example, "ace" is a subsequence of "abcde".
// A common subsequence of two strings is a subsequence that is common to both strings.
// Example 1:
// Input: text1 = "abcde", text2 = "ace" 
// Output: 3  
// Explanation: The longest common subsequence is "ace" and its length is 3.

// HINT
// Match      -> Take both chars -> Diagonal ↖ + 1 
// No Match   -> Drop one char   -> Max(Up ↑, Left←)
                                          

class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();

        int[][] dp = new int[n + 1][m + 1];
        // Build answer from smaller prefixes to larger prefixes
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // Characters match → include it in LCS
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } 
                // Characters don't match → skip one char from either string
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[n][m];
    }
}


// Time  : O(n * m)
// Space : O(n * m)
