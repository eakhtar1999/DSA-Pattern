/**
You are given an integer array coins representing coins of different denominations and an integer amount representing 
  a total amount of money.
Return the fewest number of coins that you need to make up that amount. If that amount of money cannot 
  be made up by any combination of the coins, return -1.
You may assume that you have an infinite number of each kind of coin.
Example 1:
Input: coins = [1,2,5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1
*/

class Solution {
    public int coinChange(int[] coins, int amount) {
        // 💡 Use dynamic programming.
        // 💡 Build up solutions from smaller amounts.
        // n <- 1+{n-1, n-2, n-5} where coins =[1,2,5]
        // n<- a-c where c belongs to coins
        // minimum number of coins no matter which order we get answer. 
        // So coin loop is inner loop
        int[] dp = new int[amount+1];
        Arrays.fill(dp, amount+1);
        dp[0]=0;

        for(int a =1; a<= amount; a++){
            for(int c : coins){
                if(a-c >= 0){
                    dp[a] = Math.min(dp[a], 1+ dp[a-c]);
                }
            }
        } 
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
