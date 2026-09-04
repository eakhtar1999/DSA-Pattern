
import java.util.HashMap;
import java.util.Map;

/*
Pattern:
    Prefix Sum + HashMap

Condition:
    currentPrefix - previousPrefix = K

Store:
    PrefixSum -> First Index

Why First Index?
    Earliest index gives maximum length.
*/

class Solution {

    public in* maxSubArrayLen(int[] nums, int k){

        Map<Integer, Integer> prefixIndex = new HashMap<>();

      // Imaginary prefix sum before array starts.
        prefixIndex.put(0, -1);

        int curSum = 0;       
        int maxLen = 0;

        for(int i = 0; i < nums.length; i++){

            curSum += nums[i];
            int diff = curSum - k;

            if (prefixIndex.containsKey(diff)) {

                maxLen = Math.max(
                    maxLen,
                    i - prefixIndex.get(diff)
                );
            }

            prefixIndex.putIfAbsent(curSum, i);
        }

        return maxLen;
    }
}
