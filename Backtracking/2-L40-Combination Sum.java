class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // 💡 Sort the array first.
        // 💡 Skip duplicates at the same level.
        // 💡 Use backtracking with start index.
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(0, res, new ArrayList<>(), 0, candidates, target);
        return res;
    }

    private void dfs(int i, List<List<Integer>> res, List<Integer> cur, int total, int[] candidates, int target){
        if(total == target){
            res.add(new ArrayList<>(cur));
            return ;
        }
        if(total > target || i ==candidates.length) return ;

        cur.add(candidates[i]);
        dfs(i+1, res, cur, total + candidates[i], candidates, target);
        cur.remove(cur.size()-1);
        while(i+1< candidates.length && candidates[i] == candidates[i+1]){
            i++;
        }
        dfs(i+1, res, cur, total, candidates, target );
    }
}

// Notes
// # ✅ Final Mental Model

// | Situation             | Action  | Reason                           |
// | --------------------- | ------- | -------------------------------- |
// | Same level duplicates | ❌ Skip  | Avoid duplicate combinations     |
// | Next recursion level  | ✅ Allow | Represents valid different picks |

// ***
// 👉 *“Why skip duplicates after not picking?”*

// Say:

// > "Because picking the same value again at the same recursion depth would produce identical combinations. So we skip duplicates to ensure uniqueness while still allowing them in deeper recursive calls."

// > At the same level, duplicate values lead to duplicate combinations → so we skip them.  e.g. [[1,_,2],[_,1,2]]
// > At deeper levels, they represent different choices → so we allow them.
// // same indices in nested list marks as same level  [1,1,2]

// [
// [ L1,L2,L3,L4],		// level increase , it goes deep
// [ L1,L2,_,L4],
// ]
