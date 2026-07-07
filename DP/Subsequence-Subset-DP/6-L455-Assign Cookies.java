/**
QUESTION: Assume you are an awesome parent and want to give your children some cookies.
Each child i has a greed factor g[i], which is the minimum size of a cookie needed to satisfy that child.
Each cookie j has a size s[j].
If s[j] >= g[i], the cookie can be assigned to the child. Each child can get at most one cookie.
Each cookie can be used at most once. Return the maximum number of content children.
EXAMPLE
Input: g = [1,2,3]  ,  s = [1,1]
Output: 1
Explanation: Only the child with greed factor 1 can be satisfied.
*/

// TABULATION

// Time Complexity: O(n*m), every pair of student and cookie is checked exactly once.
// Space Complexity: O(n*m), A 2D memoization table is used to store result of subproblems.
  
import java.util.Arrays;

class Solution {
    // Function to find the maximum number of content students using tabulation
    public int findContentChildren(int[] student, int[] cookie) {
        int n = student.length;
        int m = cookie.length;

        // Sort both arrays
        Arrays.sort(student);
        Arrays.sort(cookie);

        // Create a DP table
        int[][] dp = new int[n + 1][m + 1];

        // Fill DP table from bottom up
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                // Skip current cookie
                int skip = dp[i][j + 1];

                // Take current cookie if it satisfies student's greed
                int take = 0;
                if (cookie[j] >= student[i]) {
                    take = 1 + dp[i + 1][j + 1];
                }

                // Take the best of both choices
                dp[i][j] = Math.max(skip, take);
            }
        }

        return dp[0][0];
    }
}

// GREEDY OPTIMISED
// Time Complexity: O(n*logn + m*logm), Both the arrays are sorted in increasing order.
// Space Complexity: O(1), No extra space is used.

import java.util.*;

class Solution {
    // Function to find the maximum number of content students
    public int findContentChildren(int[] student, int[] cookie) {
        // Sort both arrays to apply the greedy strategy
        Arrays.sort(student);
        Arrays.sort(cookie);

        int studentIndex = 0;
        int cookieIndex = 0;

        // Try to assign cookies until any one list is fully processed
        while (studentIndex < student.length && cookieIndex < cookie.length) {
            // If the cookie satisfies the student's greed
            if (cookie[cookieIndex] >= student[studentIndex]) {
                studentIndex++;
            }
            // Move to next cookie in both cases
            cookieIndex++;
        }

        // Number of students satisfied is equal to studentIndex
        return studentIndex;
    }
}


