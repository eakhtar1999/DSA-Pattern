
import java.util.HashMap;
import java.util.Map;

/*
Pattern:
    Prefix Sum Modulo + HashMap

Need:
    Existence

Store:
    Remainder -> First Index

Why First Index?
    Need length >= 2
*/

class Solution {

    public boolean checkSubarraySum(int[] nums, int k) {

        Map<Integer, Integer> remainderIndex = new HashMap<>();

        // Imaginary index before array starts.
        remainderIndex.put(0, -1);

        int curSum = 0;

        for (int i = 0; i < nums.length; i++) {

            curSum += nums[i];

            int rem = curSum % k;

            if (remainderIndex.containsKey(rem)) {

                if (i - remainderIndex.get(rem) >= 2) {
                    return true;
                }

            } else {

                // Keep first occurrence only.
                remainderIndex.put(rem, i);
            }
        }

        return false;
    }
}
