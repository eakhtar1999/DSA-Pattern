You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, 
  the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected 
  and it will automatically contact the police if two adjacent houses were broken into on the same night.
Given an integer array nums representing the amount of money of each house, return the maximum amount of money 
  you can rob tonight without alerting the police.
Example 1:
Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.

🧠 max = max(take + i-2, skip)
    
```java
class Solution {
    // Stage 4 — space-optimized (0-as-valid-empty-state trick, no n==1 guard needed)
    public int rob(int[] nums) {
        int rob1 = 0; // i-2
        int rob2 = 0; // i-1
        
        for (int n : nums) {
            // take (n + rob1) OR skip (rob2)
            int temp = Math.max(n + rob1, rob2);
            
            rob1 = rob2; // shift
            rob2 = temp;
        }
        
        return rob2; // max till last
    }

    // Stage 1 — brute-force recursion
    private int solve(int i, int[] nums) {
        if (i < 0) return 0;                              // valid empty state, not an error
        if (i == 0) return nums[0];
        return Math.max(solve(i - 1, nums), solve(i - 2, nums) + nums[i]);
    }

    // Stage 2 — memoized top-down
    private int solveMemo(int i, int[] nums, Map<Integer, Integer> memo) {
        if (i < 0) return 0;
        if (i == 0) return nums[0];
        if (memo.containsKey(i)) return memo.get(i);
        int res = Math.max(solveMemo(i - 1, nums, memo), solveMemo(i - 2, nums, memo) + nums[i]);
        memo.put(i, res);
        return res;
    }

    // Stage 3 — tabulation
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];                       // guard: dp[1] needs nums[1]
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[n - 1];
    }
}

```
