class Solution {
    // Notes - Loop DFS: iterate choices + recurse → backtrack (used for combinations)
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
        if(cur.size() >= k || remaining < 0) return ;

        for(int i=start; i<=9; i++){
            cur.add(i);
            backtrack(i+1, res, cur, remaining-i, k);
            cur.remove(cur.size()-1);
        }
    }
    //⬇️ for loop(starting number to chose from)
    //      1  2  3  4       ➡️ recursive call
    // 1
    // 2
    // 3
    // 4

    //    1
    //     ├─ 2
    //     │   ├─ 3 ❌
    //     │   ├─ 4 ✅
    //     │
    //     ├─ 3 ❌(it skip 2 here)
    //     ├─ 4 ❌(it skip 2 and 3 here)
    //    2
    //     ├─ 3 ❌
    //     ├─ 4 ❌

}

// 👉

// Loop present → Loop DFS ✅
// Two recursive calls → Binary DFS ✅
