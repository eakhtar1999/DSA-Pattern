/*
You are given an integer array nums and an integer target.
You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer 
in nums and then concatenate all the integers.
For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate them to build the expression "+2-1".
Return the number of different expressions that you can build, which evaluates to target.
Example 1:
Input: nums = [1,1,1,1,1], target = 3
Output: 5
Explanation: There are 5 ways to assign symbols to make the sum of nums be target 3.
-1 + 1 + 1 + 1 + 1 = 3
+1 - 1 + 1 + 1 + 1 = 3
+1 + 1 - 1 + 1 + 1 = 3
+1 + 1 + 1 - 1 + 1 = 3
+1 + 1 + 1 + 1 - 1 = 3
*/

// HINT -  BACKWARD LOOP TO MAKE IT 0/1 KNAPSACK
/**
dp = [1,0,0,0,0]      // Base: 1 way to make sum 0
dp = [1,1,0,0,0]      // Choose 1 out of 1 one → C(1,1)=1
dp = [1,2,1,0,0]      // Sum1: Choose 1 out of 2 to make 1 C(2,1)=2, Choose 2 out of 2 to make 2 Sum2: C(2,2)=1
dp = [1,3,3,1,0]      // Sum1: C(3,1)=3, Sum2: C(3,2)=3, Sum3: C(3,3)=1
dp = [1,4,6,4,1]      // Sum1: C(4,1)=4, Sum2: C(4,2)=6, Sum3: C(4,3)=4, Sum4: C(4,4)=1
dp = [1,5,10,10,5]    // Sum4: C(5,4)=5 ⇒ Answer = 5
*/

class Solution {
    public int findTargetSumWays(int[] nums, int target) {

        // Let P = sum of positive elements, N = sum of negative elements.
        // P + N = total (sum of all elements)
        // P - N = target (desired result)
        // Solving: P = (total + target) / 2
        // Finding ways to make target = counting subsets that sum to P!
        //( so we will do + not || during updating the dp table)
        int sum = 0;
        for (int num : nums) sum += num;

        // (sum + target) must be even and non-negative since it is P
        if ((sum + target) % 2 != 0 || sum + target < 0) return 0;

        int subsetSum = (sum + target) / 2;
        int[] dp = new int[subsetSum + 1];
        dp[0] = 1;

        for (int num : nums) {
            for (int j = subsetSum; j >= num; j--) {
                // DON'T TAKE +  TAKE
                dp[j] = dp[j] + dp[j - num];
            }
        }
        return dp[subsetSum];
    }
}

/* unbounded knapsack - dp[j] uses already-updated dp[j-1]
dp = [1,0,0,0,0]      // Base: 1 way to make sum 0
dp = [1,1,1,1,1]      // Wrong: same 1 reused → sums 1,2,3,4 all become possible
dp = [1,2,3,4,5]      // Count inflates as updated values are reused immediately
dp = [1,3,6,10,15]    // Triangle numbers start appearing
dp = [1,4,10,20,35]   // Repeated reuse of current element increases counts
dp = [1,5,15,35,70]   // Sum4 = 70 (incorrect, actual answer should be 5)
*/
/*
Backward Loop  -> Use element once     -> 0/1 Knapsack ✅
Forward Loop   -> Reuse element many times -> Unbounded Knapsack ❌
*/
