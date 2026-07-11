/**
You are given an array prices where prices[i] is the price of a given stock on the ith day.
Find the maximum profit you can achieve. You may complete as many transactions as you like 
(i.e., buy one and sell one share of the stock multiple times) with the following restrictions:
After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
Input: prices = [1,2,3,0,2]
Output: 3
Explanation: transactions = [buy, sell, cooldown, buy, sell]
*/


class Solution {
    public int maxProfit(int[] prices) {

        // hold = max profit while holding a stock
        // sold = max profit if sold today
        // rest = max profit while resting/cooldown

        int hold = -prices[0], sold = 0, rest = 0;

        for (int i = 1; i < prices.length; i++) {
            int prevHold = hold;

            // keep holding OR buy today after rest
            hold = Math.max(hold, rest - prices[i]);

            // keep resting OR enter cooldown after sell
            rest = Math.max(rest, sold);

            // must have held stock before selling
            sold = prevHold + prices[i];
        }

        // cannot end with a stock in hand
        return Math.max(sold, rest);
    }
}
