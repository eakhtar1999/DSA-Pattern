class Solution {
    public int totalNQueens(int n) {
        int[] result={0};
        char[][] board = new char[n][n]; // board state
        for (int i = 0; i < n; i++) java.util.Arrays.fill(board[i], '.'); // init empty

        backtrack(board, 0, result); // start row 0
        return result[0];
    }

    private void backtrack(char[][] board, int row, int[] result) {
        if (row == board.length) { // all rows filled → valid solution
            result[0]++;
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
