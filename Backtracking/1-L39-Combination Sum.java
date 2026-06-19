class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // 💡 Use backtracking.
        // 💡 For each candidate, include it multiple times or skip.
        // 💡 To avoid duplicates, only consider from current index onwards. ( i or i+1 not i-1)
        List<List<Integer>> res =  new ArrayList<>();
        List<Integer> cur =  new ArrayList<>();
        dfs(0, res, cur, 0, candidates, target);
        return res;
    }
    private void dfs(int i, List<List<Integer>> res,  List<Integer> cur, int total, int[] candidates, int target){
        if(total == target){
            // store a snapshot (copy) of the current combination.
            // future change in cur wont effect res
            res.add(new ArrayList<>(cur));
            return ;
        }
        if(total > target || i>= candidates.length){
            return ;
        }
        
        cur.add(candidates[i]);
        // consider element, and unbound knapsack so we are on same element i
        dfs(i, res, cur, total+candidates[i], candidates, target);

        cur.remove(cur.size()-1);//backracking
        // not considering element, so we move to i+1
        dfs(i+1, res, cur, total, candidates, target);
    }
    // notes
    // res.add(new ArrayList<>(cur));
    // It creates a new independent list(snapshot (copy) of the current combination):
    //     It copies the current contents of cur
    //     Future changes to cur do NOT affect saved results

    // res.add(cur);
    // cur keeps getting modified during recursion (add/remove)
    // res stores multiple references to the same object
    // At the end, all entries in res will look identical (the final state of cur)
}
