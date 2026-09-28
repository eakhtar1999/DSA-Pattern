```textquote
Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.
Example 1:

Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
Output: 6
Explanation: [1,1,1,0,0,1,1,1,1,1,1]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
```

```java
class Solution {
    public int longestOnes(int[] nums, int k) {
        int l = 0,count = 0,res = 0;

        for (int r = 0; r < nums.length; r++) {
            if (nums[r] == 0)count++;
            while (count > k) {// for making window valid
                if (nums[l] == 0) {
                    count--;
                }
                l++;
            }
            res = Math.max(res, r - l + 1);// result calculation
        }
        return res;
    }
}
```
