/*
Given an integer array nums, return the length of the longest strictly increasing subsequence.
A subsequence is a sequence derived from an array by deleting some or no elements without 
changing the order of the remaining elements.
In increasing subsequence, every element is greater than the previous one
Example 1:
Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
*/

import java.util.*;

public class LongestIncreasingSubsequence {

    // ---------- 1. Pure Recursion (Brute Force) ----------
    // State: index i, and the index of the last picked element (prevIndex)
    // Time: O(2^n) — exponential, every element has a pick/skip branch
    public int lengthOfLIS_recursive(int[] nums) {
        return recurse(nums, 0, -1);
    }

    private int recurse(int[] nums, int i, int prevIndex) {
        if (i == nums.length) return 0; // base case: ran off the end, no more to add

        // Option 1: skip nums[i]
        int skip = recurse(nums, i + 1, prevIndex);

        // Option 2: take nums[i], only valid if it extends the increasing sequence
        int take = 0;
        if (prevIndex == -1 || nums[i] > nums[prevIndex]) {
            take = 1 + recurse(nums, i + 1, i);
        }

        return Math.max(skip, take);
    }

    // ---------- 2. Memoization (Top-Down) ----------
    // Same state (i, prevIndex), but cache results.
    // prevIndex ranges from -1 to n-1, so shift by +1 to index into the memo array cleanly.
    public int lengthOfLIS_memo(int[] nums) {
        int n = nums.length;
        Integer[][] memo = new Integer[n][n + 1]; // memo[i][prevIndex + 1]
        return memoRecurse(nums, 0, -1, memo);
    }

    private int memoRecurse(int[] nums, int i, int prevIndex, Integer[][] memo) {
        if (i == nums.length) return 0;

        if (memo[i][prevIndex + 1] != null) return memo[i][prevIndex + 1];

        int skip = memoRecurse(nums, i + 1, prevIndex, memo);

        int take = 0;
        if (prevIndex == -1 || nums[i] > nums[prevIndex]) {
            take = 1 + memoRecurse(nums, i + 1, i, memo);
        }

        int result = Math.max(skip, take);
        memo[i][prevIndex + 1] = result;
        return result;
    }

    // ---------- 3. Tabulation (Bottom-Up, O(n^2)) ----------
    // Different state definition here: dp[i] = length of the longest increasing
    // subsequence *ending exactly at* index i (not "pick/skip from i onward").
    public int lengthOfLIS_tabulation(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1); // every single element is an LIS of length 1 by itself

        int maxLen = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        return maxLen;
    }

    // ---------- 4. Binary Search / "Tails" technique (O(n log n)) ----------
    public int lengthOfLIS_binarySearch(int[] nums) {
        List<Integer> tails = new ArrayList<>();
        for (int num : nums) {
            int idx = Collections.binarySearch(tails, num);
            if (idx < 0) idx = -(idx + 1);
            if (idx == tails.size()) {
                tails.add(num);
            } else {
                tails.set(idx, num);
            }
        }
        return tails.size();
    }

    public static void main(String[] args) {
        LongestIncreasingSubsequence sol = new LongestIncreasingSubsequence();
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println("Recursive:     " + sol.lengthOfLIS_recursive(nums));
        System.out.println("Memoized:      " + sol.lengthOfLIS_memo(nums));
        System.out.println("Tabulation:    " + sol.lengthOfLIS_tabulation(nums));
        System.out.println("BinarySearch:  " + sol.lengthOfLIS_binarySearch(nums));
    }
}

// -----

import java.util.*;

class Solution {
    public int lengthOfLIS(int[] nums) {

        int n = nums.length;
        // temp does NOT necessarily store the actual LIS.
        // temp[i] stores the smallest possible tail element
        // of an increasing subsequence of length (i + 1).
        List<Integer> temp = new ArrayList<>();
        // First element always starts a subsequence of length 1.
        temp.add(nums[0]);

        // Process remaining elements
        for (int i = 1; i < n; i++) {
            // If current element is greater than the last element
            // in temp, we can extend the longest subsequence found so far.
            if (nums[i] > temp.get(temp.size() - 1)) {
                temp.add(nums[i]);
            } else {
                // Find the first element in temp that is greater than or equal to nums[i] (lower bound).
                int ind = Collections.binarySearch(temp, nums[i]);
                if (ind < 0) {
                    ind = -(ind + 1);
                }
                // Replace the element at 'ind'.
                temp.set(ind, nums[i]);
            }
        }

        // Important: temp.size() == length of LIS
        // temp itself is NOT guaranteed to be the actual LIS.
        return temp.size();
    }
}

// Collections.binarySearch():
// - returns index if found
// - otherwise returns -(insertionPoint + 1)


// Why replace?
// We want the smallest possible tail for a subsequence
// of this length, because a smaller tail provides
// more opportunities to extend the subsequence later.
//
// Example:
// [1, 5, 7] -> current element = 2
// Replace 5 with 2: [1, 2, 7]
//
// Note:
// [1, 2, 7] may NOT be a valid subsequence in the
// original array. temp is only a helper structure
// used to calculate the LIS length efficiently.
