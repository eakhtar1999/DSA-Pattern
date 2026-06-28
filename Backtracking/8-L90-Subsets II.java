/**
Given an integer array nums that may contain duplicates, return all possible subsets (the power set).
The solution set must not contain duplicate subsets. Return the solution in any order.
Example 1:
Input: nums = [1,2,2]
Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
*/

class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> subset = new ArrayList<>();
        dfs(0, nums, subset, res);
        return res;
    }
    
    private void dfs(int i, int[] nums, List<Integer> subset, List<List<Integer>> res) {
        //Stop when index goes past the last element, not when it reaches the last element.
        if (i >= nums.length) {
            res.add(new ArrayList<>(subset));
            return;
        }
        
        subset.add(nums[i]);
        // we don't add while loop here because, it will completely 
        //skip all duplicates from include or exclude branch
        // we do in Loop-based approach
        dfs(i + 1, nums, subset, res);
        
        subset.remove(subset.size() - 1);//bactracking
        //👉 Duplicate skipping is only needed in the "exclude" branch, NOT the "include" branch.
        // since excluding branch with next duplicate number nums[i+1] at level i+1 
        // will reproduce what has been produced in the including branch at level i
        while (i + 1 < nums.length && nums[i] == nums[i + 1]) {
            i++;
        }
        dfs(i + 1, nums, subset, res);
    }
}
/**
*✅ Loop-based DFS approach:
* You iterate and choose elements inside a loop
* Duplicate decision must be taken before adding
* 👉 Skip happens before add()
*/


// Loop DFS → skip duplicates BEFORE add
// Include/Exclude → skip duplicates AFTER remove


