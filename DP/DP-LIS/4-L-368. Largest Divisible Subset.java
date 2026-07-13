/*
Given a set of distinct positive integers nums, return the largest subset answer such that every pair 
(answer[i], answer[j]) of elements in this subset satisfies:
answer[i] % answer[j] == 0, or
answer[j] % answer[i] == 0
If there are multiple solutions, return any of them.
Example 1:
Input: nums = [1,2,3]
Output: [1,2]
Explanation: [1,3] is also accepted.
*/
/*
This is an LIS (Longest Increasing Subsequence) pattern problem where after sorting, we build the largest chain of numbers such that each number is divisible by its previous number. dp[] stores the subset length and parent[] is used to reconstruct the actual subset.
✅ Sort the array first.
✅ Use LIS-style DP.
✅ dp[i] = largest divisible subset length ending at i.
✅ parent[i] = previous index used to form that subset.
✅ Track the index of the maximum length subset.
✅ Backtrack using parent[] to reconstruct the actual answer.
*/


import java.util.*;

class Solution {
    // Function to find the largest divisble subset
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length; // Size of the array 
        
        //Every possible divisor will appear before its multiple, allowing LIS-style DP.
        Arrays.sort(nums);
        
        List<Integer> ans = new ArrayList<>(); // To store the LDS
        
        // dp[i] = Length of the largest divisible subset ending at index i.
        int[] dp = new int[n]; // DP array 
        // Every element alone forms a valid subset, so initialize with 1.
        Arrays.fill(dp, 1);
        
        // parent[i] stores the previous index used to form the subset ending at i. This helps reconstruct the answer later.
        int[] parent = new int[n]; // Array to keep record of the parent
        
        // To store the index of last element in the LDS
        int lastIndex = 0; 
        
        // To store the length of LDS
        int maxLen = 0;
        
        // Computing the DP array 
        for(int i = 0; i < n; i++) {
            //Initially every number points to itself. This indicates the start of a chain.
            parent[i] = i; // Assign the parent to itself
            
            // For each previous index
            for(int prevInd = 0; prevInd < i; prevInd++) {
                
                // Means nums[i] can extend the divisible subset ending at prevInd.
                if(nums[i] % nums[prevInd] == 0 && dp[i] < dp[prevInd] + 1) {
                    dp[i] = dp[prevInd] + 1; // Update the DP value
                    parent[i] = prevInd; // Store the parent who helped form this larger subset. Needed later during backtracking.
                }
            }
            // If a longer LDS is found, update the values
            if(dp[i] > maxLen) {
                lastIndex = i;
                maxLen = dp[i];
            }
        }
        // Backtracking
        int i = lastIndex;
        
        // Move backwards using parent[] to reconstruct the subset.
        // Until we reach an index which is its own parent
        while(parent[i] != i) {
            ans.add(nums[i]); // Add the element at current index
            i = parent[i]; 
        }
        ans.add(nums[i]); // Adding the last element 
        
        // Return the computed result
        return ans;
    }  
}
