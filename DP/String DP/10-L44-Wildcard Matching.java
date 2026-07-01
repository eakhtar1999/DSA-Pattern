// Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:
// '?' Matches any single character.
// '*' Matches any sequence of characters (including the empty sequence).
// The matching should cover the entire input string (not partial).
// Example 1:
// Input: s = "aa", p = "a"
// Output: false
// Explanation: "a" does not match the entire string "aa".

class Solution {

    // Function to check if pattern p matches string s
    public boolean isMatch(String s, String p) {

        int n = s.length();
        int m = p.length();

        // prev[j] -> result for previous pattern row
        // curr[j] -> result for current pattern row
        boolean[] prev = new boolean[n + 1];
        boolean[] curr = new boolean[n + 1];

        // Base case: empty pattern matches empty string
        prev[0] = true;

        // Iterate through pattern characters
        for (int i = 1; i <= m; i++) {

            // Create a fresh row for current pattern character
            curr = new boolean[n + 1];

            // Check if pattern[0...i-1] contains only '*'
            boolean allStars = true;
            for (int k = 1; k <= i; k++) {
                if (p.charAt(k - 1) != '*') {
                    allStars = false;
                    break;
                }
            }

            // Pattern can match empty string only if all characters are '*'
            curr[0] = allStars;

            // Process all string characters
            for (int j = 1; j <= n; j++) {

                // Case 1: Exact match or '?' wildcard
                if (p.charAt(i - 1) == s.charAt(j - 1) || 
                    p.charAt(i - 1) == '?') {
                    curr[j] = prev[j - 1];
                }
                // Case 2: '*' wildcard
                else if (p.charAt(i - 1) == '*') {
                    // '*' matches:
                    // 0 characters -> prev[j]
                    // 1 or more characters -> curr[j-1]
                    curr[j] = prev[j] || curr[j - 1];
                }
                // Case 3: Characters do not match
                else {
                    curr[j] = false;
                }
            }
            // Move current row to previous row for next iteration
            prev = curr;
        }
        // Final answer: full pattern vs full string
        return prev[n];
    }
}




---  SLOW

class Solution {
    // Helper function to check if the first i characters of the pattern are all '*'
    private boolean isAllStars( String p, int i){
        // Loop through the first i characters
        for(int k =i; k>=1; k--){
            if(p.charAt(k-1) != '*')return false; // If any character is not '*', return false
        }
        return true; // All were '*', so return true
    }
    // Function to check if the pattern S1 matches the string S2
    public boolean isMatch(String s, String p) {
        //setup
        int n = s.length();
        int m = p.length();
         // Create a DP table where dp[i][j] tells if pattern[0...i-1] matches text[0...j-1]
        boolean[][] dp = new boolean[m+1][n+1];

         // Base case: empty pattern matches empty string
        dp[0][0]=true;

        // Base case: empty pattern cannot match non-empty string
        for(int j=1;j<=n;j++){
            dp[0][j] = false;
        }

        // Base case: pattern can match empty string only if it contains all '*'
        for(int i=1;i<=m; i++){
            dp[i][0] = isAllStars(p, i);
        }

        for( int i=1; i<=m; i++){
            for(int j=1; j<=n; j++){
                // Case 1: Characters match exactly or pattern has '?'
                if(p.charAt(i-1)==s.charAt(j-1) || p.charAt(i-1) == '?'){
                    dp[i][j] = dp[i-1][j-1];
                }
                // Case 2: Pattern has '*'
                else if(p.charAt(i-1) == '*'){
                    // '*' matches zero characters (dp[i-1][j]) OR
                    // '*' matches one/more characters (dp[i][j-1])
                    dp[i][j] = dp[i-1][j] || dp[i][j-1];
                }
                // Case 3: No match
                else{
                  dp[i][j] = false;
                }
            }
        }
        return dp[m][n];
    }
}
