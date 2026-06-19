// Find all valid combinations of k numbers that sum up to n such that the following conditions are true:
// Only numbers 1 through 9 are used. Each number is used at most once.
// Return a list of all possible valid combinations. The list must not contain the same combination twice, and the combinations may be returned in any order.

// Example 1:
// Input: k = 3, n = 7
// Output: [[1,2,4]]
// Explanation:
// 1 + 2 + 4 = 7
// There are no other valid combinations.


class Solution {
    // Notes
    // Backtracking = Explore all possible combinations step by step, and undo (backtrack) when needed.
    // Backtracking with pruning = exploring possibilities + stopping early when a path is guaranteed to fail
    public List<List<Integer>> combinationSum3(int k, int n) {
        //💡 Use backtracking with pruning.
        List<List<Integer>> res = new ArrayList<>();
        backtrack(1, res, new ArrayList<>(), n, k);
        return res;
    }

    private void backtrack(int start, List<List<Integer>> res, List<Integer> cur, int remaining, int k){
        //valid solution → keep it
        if(cur.size() == k && remaining == 0){
            res.add(new ArrayList<>(cur));
            return ;
        }
        // pruning : 
        //     If size already reached/exceeded k
        //     OR remaining sum is already negative
        if(cur.size() >= k && remaining < 0) return ;

        for(int i=start; i<=9; i++){
            cur.add(i);
            backtrack(i+1, res, cur, remaining-i, k);
            cur.remove(cur.size()-1);
        }
    }
    //⬇️ for loop
    //      1  2  3  4       ➡️ recursive call
    // 1
    // 2
    // 3
    // 4

}
