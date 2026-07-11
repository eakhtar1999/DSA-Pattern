class Solution {
    public int maxProfit(int[] prices) {
        // 💡 State DP: track profit after 1st buy, 1st sell, 2nd buy, 2nd sell.
        int buy1 = Integer.MAX_VALUE, buy2 = Integer.MAX_VALUE;
        int sell1 = 0, sell2 =0;
        for(int price : prices){
            // One Transaction
            buy1 = Math.min(buy1, price);
            sell1 = Math.max(sell1, price - buy1);
            // Add a Second Transaction
            buy2 = Math.min(buy2, price- sell1);
            sell2 = Math.max(sell2, price - buy2);
        }
        return sell2;
    }
}

/*
Variable	State	        Meaning
buy1	    After 1st buy	Maximum profit while holding first stock
sell1	    After 1st sell	Maximum profit after completing first transaction
buy2	    After 2nd buy	Maximum profit while holding second stock
sell2	    After 2nd sell	Maximum profit after completing both transactions
*/
