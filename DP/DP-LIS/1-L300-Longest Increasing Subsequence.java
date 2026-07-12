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

class Solution {
    public int LIS(int[] nums) {

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
