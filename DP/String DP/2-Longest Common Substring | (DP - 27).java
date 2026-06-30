// https://takeuforward.org/data-structure/longest-common-substring-dp-27
Given two strings str1 and str2, find the length of their longest common substring.
A substring is a contiguous sequence of characters within a string.
Example 1:
Input:  str1 = "abcde", str2 = "abfce"  
Output:  2  
Explanation: The longest common substring is "ab", which has a length of 2.

// HINT - same as LC subsequence , just - Reset to 0 on mismatch (not max of neighbors)
  
import java.util.*;

// Class containing the LCS logic
class Solution {
    /* Function to find the length of 
       the Longest Common Substring */
    public int longestCommonSubstr(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
    
        // DP table: dp[i][j] stores length of common substring ending at str1[i-1] and str2[j-1]
        int[][] dp = new int[n + 1][m + 1];

        // Variable to store the maximum substring length found so far
        int ans = 0;

        // Fill the DP table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // If characters match, extend the substring
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    ans = Math.max(ans, dp[i][j]); // Update maximum length
                } else {
                    dp[i][j] = 0; // Reset length when characters don't match
                }
            }
        }
        return ans; // Return the longest length found
    }
}

// Separate main class for testing
class Main {
    public static void main(String[] args) {
        String s1 = "abcjklp";
        String s2 = "acjkp";

        Solution sol = new Solution();
        System.out.println("The Length of Longest Common Substring is " + sol.longestCommonSubstr(s1, s2));
    }
}
