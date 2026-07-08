/*
Ninja is planing this ‘N’ days-long training schedule. Each day, he can perform any one of these three activities. 
(Running, Fighting Practice or Learning New Moves). Each activity has some merit points on each day. 
As Ninja has to improve all his skills, he can’t do the same activity in two consecutive days. 
Can you help Ninja find out the maximum merit points Ninja can earn?
You are given a 2D array of size N*3 ‘POINTS’ with the points corresponding to each day and activity. 
Your task is to calculate the maximum number of merit points that Ninja can earn.
For Example If the given ‘POINTS’ array is [[1,2,5], [3 ,1 ,1] , [3,3,3]],the answer will be 11 as 5 + 3 + 3.

Example 1:
Input: matrix = [[10, 40, 70], [20, 50, 80], [30, 60, 90]]
Output: 210
Explanation:
Day 1: fighting practice = 70
Day 2: stealth training = 50
Day 3: fighting practice = 90
Total = 70 + 50 + 90 = 210
This gives the optimal points.
*/
/**
f(2,3)
  ↓
f(1,0), f(1,1), f(1,2)
  ↓
f(0,0), f(0,1), f(0,2)

f(day, last)  => Maximum points achievable from day 0 to day 'day'
where on day+1 we have already chosen task 'last',
so day cannot choose the same task.
*/


import java.util.*;

class Solution {
    // Recursive function to calculate the maximum points for the ninja training
    public int f(int day, int last, int[][] points, int[][] dp) {
        // If the result for this day and last activity is already calculated, return it
        if (dp[day][last] != -1) return dp[day][last];

        // Base case: When we reach the first day (day == 0)
        if (day == 0) {
            int maxi = 0;
            // Calculate the maximum points for the first day by choosing an activity
            // different from the last one
            for (int i = 0; i <= 2; i++) {
                if (i != last)
                    maxi = Math.max(maxi, points[0][i]);
            }
            // Store the result in dp array and return it
            return dp[day][last] = maxi;
        }

        int maxi = 0;
        // Iterate through the activities for the current day
        for (int i = 0; i <= 2; i++) {
            if (i != last) {
                // Calculate the points for the current activity and add it to the
                // maximum points obtained so far (recursively calculated)
                int activity = points[day][i] + f(day - 1, i, points, dp);
                maxi = Math.max(maxi, activity);
            }
        }

        // Store the result in dp array and return it
        return dp[day][last] = maxi;
    }

    // Function to find the maximum points for ninja training
    public int ninjaTraining(int n, int[][] points) {
        // Create a memoization table (dp) to store intermediate results
        int[][] dp = new int[n][4];
        for (int[] row : dp) {
            Arrays.fill(row, -1); // Initialize the dp array with -1
        }
        // Start the recursive calculation from the last day with no previous activity
        return f(n - 1, 3, points, dp);
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        // Define the points matrix
        int[][] points = {{10, 40, 70},
                          {20, 50, 80},
                          {30, 60, 90}};
        int n = points.length;  // Get the number of days
        // Call the ninjaTraining function to find the maximum points and print the result
        System.out.println(sol.ninjaTraining(n, points));
    }
}
