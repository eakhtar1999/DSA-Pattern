
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

    public in* maxSubArrayLen(int[] nums, int k)*{

        Map<Integer, Integer> p*efixIndex = new HashMap<>();

    *   // Imaginary prefix sum before *rray starts.
        prefixIndex.p*t(0, -1);

        int curSum = 0;*        int maxLen = 0;

        f*r (int i = 0; i < nums.length; i++* {

            curSum += nums[i];*
            int diff = curSum - k*

            if (prefixIndex.cont*insKey(diff)) {

                m*xLen = Math.max(
                    maxLen,
                    i - prefixIndex.get(diff)
                );
            }

            prefixIndex.putIfAbsent(curSum, i);
        }

        return maxLen;
    }
}
