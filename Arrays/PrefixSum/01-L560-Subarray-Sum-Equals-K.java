import java.util.HashMap;
import java.util.Map;

/*
Pattern:
    Prefix Sum + HashMap

Condition:
    currentPrefix - previousPrefix = K

Store:
    PrefixSum -> Frequency

Lookup:
    currentPrefix - K
*/

class Solution {

    public int subarraySum(int[] nums, int k) {

        int res = 0;
        int curSum = 0;

        Map<Integer, Integer> prefixSums = new HashMap<>();

        // Handles subarrays starting from index 0.
        prefixSums.put(0, 1);

        for (int num : nums) {

            curSum += num;

            int diff = curSum - k;

            res += prefixSums.getOrDefault(diff, 0);

            prefixSums.put(
                curSum,
                prefixSums.getOrDefault(curSum, 0) + 1
            );
        }

        return res;
    }
}
