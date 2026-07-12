/*
Given an integer array nums, return the number of longest increasing subsequences.
Notice that the sequence has to be strictly increasing.
Example 1:
Input: nums = [1,3,5,4,7]
Output: 2
Explanation: The two longest increasing subsequences are [1, 3, 4, 7] and [1, 3, 5, 7].
*/

/*
dp[i] stores the LIS length ending at index i, and ct[i] stores how many LIS of that maximum length end at i. 
When a longer subsequence is found we replace the count; when another subsequence with the same length is found we add the count. 
Finally, sum all counts whose LIS length equals the global maximum length.
*/

import java.util.*;

class Solution {

    // Finds the total number of Longest Increasing Subsequences (LIS)
    public int findNumberOfLIS(int[] arr) {

        // Length of the array
        int n = arr.length;

        /* dp[i] = Length of LIS ending at index i.
         * Example:
         * arr = [1,3,5]
         * dp   = [1,2,3]
         * Every element alone can form a subsequence of length 1,
         * so initialize all values with 1.
         */
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        /*
         * ct[i] = Number of LIS ending at index i.
         *
         * Initially each element itself forms one subsequence,
         * so count = 1.
         */
        int[] ct = new int[n];
        Arrays.fill(ct, 1);

        /*
         * Stores the maximum LIS length found in the whole array.
         *
         * Initially 1 because any single element is an LIS
         * of length 1.
         */
        int maxi = 1;

        // Consider every element as the ending point of a subsequence
        for (int i = 0; i < n; i++) {

            // Check all previous elements
            for (int j = 0; j < i; j++) {
                /* arr[j] < arr[i]
                 * We can extend the subsequence ending at j by including arr[i].
                 */
                if (arr[j] < arr[i] && dp[j] + 1 > dp[i]) {
                    /* Found a better (longer) subsequence.
                     * Why dp[j] + 1 ?
                     * Length at j plus current element.
                     */
                    dp[i] = dp[j] + 1;

                    /* Since this new LIS is longer than anything
                     * previously found for i, the count should be replaced.
                     * We inherit all ways from index j.
                     */
                    ct[i] = ct[j];
                }
                // Another LIS of the SAME length found.
                else if (arr[j] < arr[i] && dp[j] + 1 == dp[i]) {
                    /* Existing LIS length remains unchanged.
                     * But now we discovered additional ways
                     * to obtain the same LIS length. Therefore add the counts.*/
                    ct[i] = ct[i] + ct[j];
                }
            }

            /* Update global LIS length.
             * Keeps track of the longest subsequence
             * seen anywhere in the array.
             */
            maxi = Math.max(maxi, dp[i]);
        }

        // Will store total number of LIS in the array.
        int countLIS = 0;

        // Any position having dp[i] == maxi contributes to the final answer.
        for (int i = 0; i < n; i++) {

            if (dp[i] == maxi) {

                /*
                 * Add all LIS counts ending at i.
                 */
                countLIS += ct[i];
            }
        }

        return countLIS;
    }
}

