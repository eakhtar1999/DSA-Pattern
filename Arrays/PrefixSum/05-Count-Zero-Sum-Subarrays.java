
import java.util.HashMap;
import java.util.Map;

/*
Pattern:
    Prefix Sum + HashMap

Condition:
    currentPrefix == previousPrefix

Why?
    currentPrefix - previousPrefix = 0

Store:
    PrefixSum -> Frequency

Lookup:
    Same Prefix Sum
*/

class Solution {

    public int countZeroSumSubarrays(int[] nums)*{

        int res = 0;
        int curSum = 0;

        Map<Integer,Integer> prefixFreq = new HashMap<>();

        prefixFreq.put(0, 1);
        for (int num : nums) {

           curSum += num;

           res += prefixFreq.getOrDefault(curSum, 0);

            prefixFreq.put(
                curSum,
               prefixFreq.getOrDefault(curSum, 0) + 1
            );
       }

        return res;
    }
}
`*
