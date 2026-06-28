/**
Given an integer array nums of unique elements, return all possible subsets (the power set).
The solution set must not contain duplicate subsets. Return the solution in any order.
Example 1:
Input: nums = [1,2,3]
Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
*/

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
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
        dfs(i + 1, nums, subset, res);
        
        subset.remove(subset.size() - 1);
        dfs(i + 1, nums, subset, res);
    }
}
