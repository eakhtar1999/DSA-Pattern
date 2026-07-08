/**
You are given an m x n integer array grid. There is a robot initially located at the top-left corner (i.e., grid[0][0]). 
The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). 
The robot can only move either down or right at any point in time.
An obstacle and space are marked as 1 or 0 respectively in grid. A path that the robot takes cannot 
include any square that is an obstacle.
Return the number of possible unique paths that the robot can take to reach the bottom-right corner.
*/

class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        if (obstacleGrid == null || obstacleGrid[0][0] == 1) {
            return 0;
        }

        int rows = obstacleGrid.length;
        int cols = obstacleGrid[0].length;

        // dp[c] represents paths to current cell in column c.
        // Initially acts like the first row (aboveRow).
        int[] dp = new int[cols];

        // Start cell has one path.
        dp[0] = 1;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                // Obstacle blocks all paths reaching this cell.
                if (obstacleGrid[r][c] == 1) {
                    dp[c] = 0;
                } else if (c > 0) {
                    /* Mapping with Unique Paths:
                     * currentRow[j] = currentRow[j-1] + aboveRow[j]
                     * Here:
                     * dp[c]   = aboveRow[j]
                     * dp[c-1] = currentRow[j-1]
                     * Therefore:
                     * dp[c] = dp[c] + dp[c-1]
                     *       = above + left
                     */
                    dp[c] += dp[c - 1];
                }
            }
        }

        return dp[cols - 1];
    }
}

/*
dp[c]   -> value from the previous row (aboveRow[j])
dp[c-1] -> value already computed for the current row (currentRow[j-1])

At the start of processing a row, dp stores the entire previous row.

As we iterate from left to right:
- dp[c] still represents the "above" value.
- dp[c-1] represents the "left" value of the current row.

After computing a cell, dp[c] is updated from the previous row's value
to the current row's value.

Thus, the same dp array gradually transforms from storing the previous row
into storing the current row while moving across the columns.
*/

/*
dp[c]   -> value from the previous row (aboveRow[j])
dp[c-1] -> value already computed for the current row (currentRow[j-1])

At the start of processing a row, dp stores the entire previous row.

As we iterate from left to right:
- dp[c] still represents the "above" value.
- dp[c-1] represents the "left" value of the current row.

After computing a cell, dp[c] is updated from the previous row's value
to the current row's value.

Thus, the same dp array gradually transforms from storing the previous row
into storing the current row while moving across the columns.
*/
