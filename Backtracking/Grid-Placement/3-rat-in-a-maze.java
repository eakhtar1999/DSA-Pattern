// https://takeuforward.org/data-structure/rat-in-a-maze
// Problem Statement: Given a grid of dimensions n x n. A rat is placed at coordinates (0, 0) and wants to reach at coordinates (n-1, n-1). Find all possible paths that rat can take to travel from (0, 0) to (n-1, n-1). The directions in which rat can move are 'U' (up) , 'D' (down) , 'L' (left) , 'R' (right).
// The value 0 in grid denotes that the cell is blocked and rat cannot use that cell for travelling, whereas value 1 represents that rat can travel through the cell. If the cell (0, 0) has 0 value, then mouse cannot move to any other cell.

// Examples
// Input: n = 4 , grid = [ [1, 0, 0, 0] , [1, 1, 0, 1], [1, 1, 0, 0], [0, 1, 1, 1] ]
// Output: ["DDRDRR" , "DRDDRR"]
// Explanation: The rat has two different path to reach (3, 3).
// The first path is (0, 0) => (1, 0) => (2, 0) => (2, 1) => (3, 1) => (3, 2) => (3, 3).
// The second path is (0,0) => (1,0) => (1,1) => (2,1) => (3,1) => (3,2) => (3,3).


class Solution {
    public java.util.List<String> findPath(int[][] maze, int n) {
        java.util.List<String> result = new java.util.ArrayList<>();
        boolean[][] visited = new boolean[n][n]; // track visited cells

        if (maze[0][0] == 1) // only start if open
            backtrack(maze, 0, 0, "", visited, result);

        return result;
    }

    private void backtrack(int[][] maze, int row, int col, String path,
                           boolean[][] visited, java.util.List<String> result) {

        if (row == maze.length - 1 && col == maze.length - 1) { // reached end
            result.add(path);
            return;
        }

        visited[row][col] = true; // mark

        // D L R U (fixed order for lexicographic paths)
        int[] dr = {1, 0, 0, -1};
        int[] dc = {0, -1, 1, 0};
        char[] move = {'D', 'L', 'R', 'U'};

        for (int i = 0; i < 4; i++) {
            int r = row + dr[i];
            int c = col + dc[i];

            if (isSafe(maze, r, c, visited)) {
                backtrack(maze, r, c, path + move[i], visited, result);
            }
        }

        visited[row][col] = false; // undo
    }

    private boolean isSafe(int[][] maze, int r, int c, boolean[][] visited) {
        return r >= 0 && c >= 0 && r < maze.length && c < maze.length
                && maze[r][c] == 1 && !visited[r][c]; // inside + open + not visited
    }
}

// ⚡ Quick recall (maps to N-Queens)

// Same backtracking template
// Instead of placing queens → move in 4 directions
// Instead of column/diagonal check → bounds + visited + cell=1
// Add path string instead of board

// 🔁 Key Twist vs N-Queens

// ✅ Needs visited[][] (to avoid cycles)
// ✅ Multiple directions (not just next row)
// ✅ Build path string dynamically
// ✅ Order (DLRU) matters for sorted output

// 🧠 One-line memory

// “DFS in 4 directions + visited + path string + backtrack same as queens”
