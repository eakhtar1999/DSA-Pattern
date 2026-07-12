/* https://takeuforward.org/data-structure/printing-longest-increasing-subsequence-dp-42
Problem Description: Given an array of n integers arr, return the Longest Increasing Subsequence (LIS) 
that is index-wise lexicographically smallest.
The Longest Increasing Subsequence (LIS) is the longest subsequence where all elements are in strictly increasing order. 
A subsequence A1 is index-wise lexicographically smaller than another subsequence A2 if, 
at the first position where A1 and A2 differ, the element in A1 appears earlier in the array than corresponding element in A2.

Input: arr = [1, 3, 2, 4, 6, 5]
Output: [1, 3, 4, 6] 
Explanation: Possible LIS sequences are [1, 3, 4, 6] and [1, 2, 4, 6]. 
Since [1, 3, 4, 6] is index-wise lexicographically smaller, it is the result.
*/
import java.util.*;

class Solution {
    // Function to return the LIS as a list
    public List<Integer> longestIncreasingSubsequence(int[] nums) {
        int n = nums.length;

        // DP array to store length of LIS ending at each index
        int[] dp = new int[n];

        // Array to store previous index of LIS element for reconstruction
        int[] prev = new int[n];

        Arrays.fill(dp, 1);
        Arrays.fill(prev, -1);

        // Compute LIS length for each index
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i] && dp[j] + 1 > dp[i]) {
                    // Update dp[i] and store previous index
                    dp[i] = dp[j] + 1;
                    prev[i] = j;
                }
            }
        }

        // Find the index of maximum LIS length
        int maxLen = 0, maxIndex = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                maxIndex = i;
            }
        }

        // Reconstruct LIS sequence
        List<Integer> lisSeq = new ArrayList<>();
        int curr = maxIndex;
        while (curr != -1) {
            lisSeq.add(nums[curr]);
            curr = prev[curr];
        }

        // Reverse sequence as it was built backwards
        Collections.reverse(lisSeq);

        return lisSeq;
    }
}

