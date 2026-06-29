// You are climbing a staircase. It takes n steps to reach the top.
// Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
// Input: n = 3
// Output: 3
// Explanation: There are three ways to climb to the top.
// 1. 1 step + 1 step + 1 step
// 2. 1 step + 2 steps
// 3. 2 steps + 1 step

class Solution {
    public int climbStairs(int n) {
    // Fibonacci variant — dp[i] = dp[i-1] + dp[i-2]
    // n = (no. of ways to come to n-1) + (no. of way to come to n-2)

    //tabulization
    if (n <= 2) {
        return n;
    }
    int[] dp = new int[n + 1];
    dp[1] = 1;
    dp[2] = 2;
    for (int i = 3; i <= n; i++) {
        dp[i] = dp[i - 1] + dp[i - 2];
    }
    return dp[n];

    // memoization
    // Map<Integer, Integer> memo = new HashMap<>();
    // return climbStairs(n,memo);

    //recursion
    // if (n == 1 || n == 2) {
    //         return n;
    //     }
    // return climbStairs(n-1) + climbStairs(n-2);
    }

    
    private int climbStairs(int n, Map<Integer, Integer> memo){
        if(n==1 || n==2){
            return n;
        }
        if(!memo.containsKey(n)){
            memo.put(n,climbStairs(n-1, memo) + climbStairs(n-2, memo));
        }
        return memo.get(n);
    }
}
