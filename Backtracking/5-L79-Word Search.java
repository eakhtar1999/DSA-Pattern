// Given an m x n grid of characters board and a string word, return true if word exists in the grid.
// The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.
// Example 1:
// Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
// Output: true

// Pattern - DFS + Backtracking on Grid
// ✅ Word Search
// ✅ Number of Islands
// ✅ Maze problems

class Solution {
    public boolean exist(char[][] board, String word) {
        // Try to start DFS from every cell → brute-force all start points
        for(int i=0; i < board.length; i++){
            for(int j=0; j < board[0].length; j++){
                // If any starting point works, return true immediately
                if(dfs(board, word, i, j, 0)) return true;
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, int i, int j, int k){
        // ✅ Base case: entire word matched
        if(k == word.length()) return true;

        // ❌ Boundary + mismatch check (fail fast)
        if(i < 0 || j < 0 || i >= board.length || j >= board[0].length 
           || board[i][j] != word.charAt(k)) return false;

        // ✅ Mark current cell as visited (avoid reuse in same path)
        char tmp = board[i][j];
        board[i][j] = '/';

        // ✅ Explore all 4 directions (DFS branching)
        boolean res = dfs(board, word, i + 1, j, k + 1) ||
                      dfs(board, word, i - 1, j, k + 1) ||
                      dfs(board, word, i, j + 1, k + 1) ||
                      dfs(board, word, i, j - 1, k + 1);

        // 🔄 Backtrack → restore original value (critical step)
        board[i][j] = tmp;

        return res;
    }
}
