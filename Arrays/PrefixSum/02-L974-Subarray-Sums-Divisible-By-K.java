
import java.util.HashMap;
import java.util.Map;

/*
Pattern:
    Prefix Sum Modulo + HashMap

Condition:
    (currentPrefix - previousPrefix) % K == 0

Equivalent:
    currentPrefix % K == previousPrefix % K

Store:
    Remainder -> Frequency

Lookup:
    Same Remainder
*/

class Solution {

    public int subarraysDivByK(int[] nums, int k) {

        int res = 0;
        int curSum = 0;

        Map<Integer, Integer> remainderFreq = new HashMap<>();

        // Handles subarrays starting from index 0.
        remainderFreq.put(0, 1);

        for (int num : nums) {

            curSum += num;

            // Important for negative numbers.
            int rem = ((curSum % k) + k) % k;

            res += remainderFreq.getOrDefault(rem, 0);

            remainderFreq.put(
                rem,
                remainderFreq.getOrDefault(rem, 0) + 1
            );
        }

        return res;
    }
}
