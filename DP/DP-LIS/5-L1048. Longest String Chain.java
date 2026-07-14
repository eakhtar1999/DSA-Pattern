/*
You are given an array of words where each word consists of lowercase English letters.
wordA is a predecessor of wordB if and only if we can insert exactly one letter anywhere in wordA without changing the order of the other characters to make it equal to wordB.
For example, "abc" is a predecessor of "abac", while "cba" is not a predecessor of "bcad".
A word chain is a sequence of words [word1, word2, ..., wordk] with k >= 1, where word1 is a predecessor of word2, word2 is a predecessor of word3, and so on. A single word is trivially a word chain with k == 1.
Return the length of the longest possible word chain with words chosen from the given list of words.
 
Input: words = ["xbc","pcxbcf","xb","cxbc","pcxbc"]
Output: 5
Explanation: All the words can be put in a word chain ["xb", "xbc", "cxbc", "pcxbc", "pcxbcf"].

Time Complexity
Sorting: O(n log n)
DP transitions: O(n²)
Predecessor check: O(L) where L = max word length
*/

import java.util.*;

class Solution {
    public int longestStringChain(String[] words) {
        int n = words.length; // Size of the array 
        // Smaller words must come first to build chains
        Arrays.sort(words, Comparator.comparingInt(String::length));
        int[] dp = new int[n]; // DP array
        Arrays.fill(dp, 1);// Every word itself forms a chain of length 1

        // To store the length of longest string chain
        int maxLen = 0;

        // Computing the DP array 
        for (int i = 0; i < n; i++) {

            // For each previous index - Try extending chain from every previous word
            for (int j = 0; j < i; j++) {

                /* If the element at index i can be 
                included in the chain ending at index j */
                if (checkPossible(words[i], words[j]) && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1; // Update the DP value
                }
            }

            // If a longer chain is found, update the values
            if (dp[i] > maxLen) maxLen = dp[i];
        }

        return maxLen;
    }

    // Function to check if the string can be added to the chain
    // Returns true if t can become s by inserting exactly one character
    private boolean checkPossible(String s, String t) {
        // Base case
        if (s.length() != t.length() + 1) return false;

        int i = 0, j = 0; // Pointers

        // Traverse until the first string is exhausted
        while (i < s.length()) {

            // Move both pointers if characters matches
            if (j < t.length() && s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
            }
            // Otherwise - Since One extra character allowed in s
            else {
                i++;
            }
        }

        // Return true if both the string gets exhausted
        return (i == s.length() && j == t.length());
    }
}

