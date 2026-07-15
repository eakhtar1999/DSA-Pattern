/*
Problem Statement: Given an array arr of n integers, the task is to find the length of the longest bitonic sequence. 
A sequence is considered bitonic if it first increases, then decreases. The sequence does not have to be contiguous.
Example 1:
Input:  arr = [5, 1, 4, 2, 3, 6, 8, 7]  
Output:  6  
Explanation: The longest bitonic sequence is [1, 2, 3, 6, 8, 7] with a length of 6.  
The sequence increases from 1 to 8 and then decreases at 7.
  */

import java.util.*;

class Solution {
    public int LongestBitonicSequence(int[] arr) {
        int n = arr.length; // Size of the array 
        
        // LIS_dp[i] stores the length of LIS ending at index i
        int[] LIS_dp = new int[n];
        Arrays.fill(LIS_dp, 1);
        
        // To store the length of longest bitonic sequence
        int maxLen = 0;
        
        // Computing the LIS DP array 
        for (int i = 0; i < n; i++) {
            // For each previous index
            for (int prev = 0; prev < i; prev++) {
                /* If the element at index i can be
                 included in the LIS ending at prev index */
                if (arr[prev] < arr[i] && LIS_dp[i] < LIS_dp[prev] + 1) {
                    LIS_dp[i] = LIS_dp[prev] + 1; // Update the DP value
                }
            }
        }
        
        // LDS_dp[i] stores the length of LDS starting from index i
        int[] LDS_dp = new int[n];
        Arrays.fill(LDS_dp, 1);
        
        // Computing the LDS DP array 
        for (int i = n - 1; i >= 0; i--) {
            // For each previous index
            for (int prev = n - 1; prev > i; prev--) {
                /* If the element at index i can be
                 included in the LIS ending at prev index */
                if (arr[prev] < arr[i] && LDS_dp[i] < LDS_dp[prev] + 1) {
                    LDS_dp[i] = LDS_dp[prev] + 1; // Update the DP value
                }
            }
            
            // Update the maximum possible length of Longest Bitonic Sequence
            maxLen = Math.max(maxLen, LIS_dp[i] + LDS_dp[i] - 1);
        }
        
        return maxLen;
    }
}

class Main {
    public static void main(String[] args) {
        int[] arr = {5, 1, 4, 2, 3, 6, 8, 7};
        
        Solution sol = new Solution();
        int lengthOfLongestBitonicSequence = sol.LongestBitonicSequence(arr);
        
        System.out.println("The length of the Longest Bitonic Sequence is: " + lengthOfLongestBitonicSequence);
    }
}
