// The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
// Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.
// Each solution contains a distinct board configuration of the n-queens' placement, 
//   where 'Q' and '.' both indicate a queen and an empty space, respectively.

//--
// 💡 Use backtracking to try placing queens row by row.
// 💡 Track columns, diagonals, and anti-diagonals that are under attack.
// 💡 A queen attacks along rows, columns, and both diagonals.

class Solution {
    public java.util.List<java.util.List<String>> solveNQueens(int n) {
        java.util.List<java.util.List<String>> result = new java.util.ArrayList<>();
        char[][] board = new char[n][n]; // board state
        for (int i = 0; i < n; i++) java.util.Arrays.fill(board[i], '.'); // init empty

        backtrack(board, 0, result); // start row 0
        return result;
    }

    private void backtrack(char[][] board, int row, java.util.List<java.util.List<String>> result) {
        if (row == board.length) { // all rows filled → valid solution
            java.util.List<String> solution = new java.util.ArrayList<>();
            for (char[] r : board) solution.add(new String(r)); // convert board → list
            result.add(solution);
            return;
        }

        for (int col = 0; col < board.length; col++) { // try each column
            if (isSafe(board, row, col)) { // constraint check
                board[row][col] = 'Q'; // choose
                backtrack(board, row + 1, result); // explore next row
                board[row][col] = '.'; // undo (backtrack)
            }
        }
    }

    private boolean isSafe(char[][] board, int row, int col) {
        for (int i = 0; i < row; i++) // same column check
            if (board[i][col] == 'Q') return false;

        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) // left diagonal
            if (board[i][j] == 'Q') return false;

        for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++) // right diagonal
            if (board[i][j] == 'Q') return false;

        return true; // safe position
    }
}
