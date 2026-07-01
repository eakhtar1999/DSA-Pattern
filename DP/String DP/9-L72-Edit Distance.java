// Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.
// You have the following three operations permitted on a word:
// Insert a character
// Delete a character
// Replace a character
// Example 1: Input: word1 = "horse", word2 = "ros"
// Output: 3
// Explanation: 
// horse -> rorse (replace 'h' with 'r')
// rorse -> rose (remove 'r')
// rose -> ros (remove 'e')

import java.util.*;

class TUF {

    // Function to calculate the minimum edit distance between two strings
    static int editDistance(String S1, String S2) {

        int m = S1.length(); // length of first string
        int n = S2.length(); // length of second string

        // Create a 2D array to store the minimum edit distances
        int[][] dp = new int[m + 1][n + 1];

        // Initialize the first column:
        // Converting first i characters of S1 to an empty string requires i deletions.
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }

        // Initialize the first row:
        // Converting an empty string to first j characters of S2 requires j insertions.
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        // Fill the dp array using a bottom-up approach
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {

                if (S1.charAt(i - 1) == S2.charAt(j - 1)) {
                    // If the characters match, no edit is needed,
                    // so take the value from the diagonal.
                    dp[i][j] = dp[i - 1][j - 1];

                } else {
                    // If the characters don't match, take the minimum of:
                    // 1. Replace the character in S1 with the character in S2 (diagonal).
                    // 2. Delete the character from S1 (up).
                    // 3. Insert the character of S2 into S1 (left).
                    dp[i][j] = 1 + Math.min(
                            dp[i - 1][j - 1], // replace
                            Math.min(
                                    dp[i - 1][j], // delete
                                    dp[i][j - 1]  // insert
                            )
                    );
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String args[]) {

        String s1 = "horse";
        String s2 = "ros";

        System.out.println(
                "The minimum number of operations required is: "
                        + editDistance(s1, s2));
    }
}
