## 209. Minimum Size Subarray Sum
Given an array of positive integers nums and a positive integer target,
return the minimal length of a subarray whose sum is greater than or equal to target.
If there is no such subarray, return 0 instead.
Example 1:
Input: target = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: The subarray [4,3] has the minimal length under the problem constraint.

```java
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int currSum = 0;
        int i=0,minCount=Integer.MAX_VALUE;
        for(int j=0; j < nums.length; j++){
            currSum+=nums[j];
            while(currSum>=target){
                minCount = Math.min(minCount, j-i+1);
                currSum-=nums[i];
                i++;
            }     
        }
        return minCount==Integer.MAX_VALUE? 0 : minCount;
    }
}
```
