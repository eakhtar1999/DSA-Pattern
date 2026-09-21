The thief has found himself a new place for his thievery again. There is only one entrance to this area, called root.
Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that all houses in this place form a binary tree. 
It will automatically contact the police if two directly-linked houses were broken into on the same night.
Given the root of the binary tree, return the maximum amount of money the thief can rob without alerting the police.

Example 1:
Input: root = [3,2,3,null,3,null,1]
Output: 7
Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.

## One-line rule
> *"Adjacency constraint moves from array-neighbor to parent-child — so the DP state moves from an array index to a tree node, and the fix for the resulting exponential blowup isn't a memo table, it's making one post-order visit answer both 'rob me' and 'skip me' at once, so no node is ever asked twice."*

```java
class Solution {

    // DFS + PostOrderTraversal( L-R-Ro)
    public int optimized(TreeNode root) {
        int[] res = dfs(root);
        return Math.max(res[0], res[1]);
    }
    private int[] dfs(TreeNode node) {
        if (node == null) return new int[]{0, 0};                     // null check FIRST — base case for BOTH slots
        int[] left = dfs(node.left);
        int[] right = dfs(node.right);
        int robThis    = node.val + left[0] + right[0];                // rob me -> children must be skipped
        int notRobThis = Math.max(left[0], left[1]) + Math.max(right[0], right[1]); // skip me -> children free to choose either
        return new int[]{notRobThis, robThis};
    }

    //Stage 1 — the naive recursion
    public int naive(TreeNode node) {
      if (node == null) return 0;                                   // null check FIRST, always
      int robThis = node.val;
      if (node.left != null) robThis += naive(node.left.left) + naive(node.left.right);
      if (node.right != null) robThis += naive(node.right.left) + naive(node.right.right);
      int skipThis = naive(node.left) + naive(node.right);
      return Math.max(robThis, skipThis);
    }

    //Stage 2 — memoized (cache by node reference)
    public int memoized(TreeNode root) {
        Map<TreeNode, Integer> memo = new HashMap<>();
        return solveMemo(root, memo);
    }
    private int solveMemo(TreeNode node, Map<TreeNode, Integer> memo) {
        if (node == null) return 0;                                    // null check FIRST
        if (memo.containsKey(node)) return memo.get(node);
        int robThis = node.val;
        if (node.left != null) robThis += solveMemo(node.left.left, memo) + solveMemo(node.left.right, memo);
        if (node.right != null) robThis += solveMemo(node.right.left, memo) + solveMemo(node.right.right, memo);
        int skipThis = solveMemo(node.left, memo) + solveMemo(node.right, memo);
        int res = Math.max(robThis, skipThis);
        memo.put(node, res);
        return res;
    }
}

```
