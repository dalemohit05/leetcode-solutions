class Solution {
    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public boolean containsCycle(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    if (dfs(grid, visited, i, j, -1, -1, m, n)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] grid, boolean[][] visited, int r, int c, int parentR, int parentC, int m, int n) {
        visited[r][c] = true;

        for (int[] dir : DIRS) {
            int nr = r + dir[0];
            int nc = c + dir[1];

            // Check boundaries and character match
            if (nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] == grid[r][c]) {
                // Skip the parent cell we just came from
                if (nr == parentR && nc == parentC) {
                    continue;
                }

                // If already visited and not the parent, a cycle exists
                if (visited[nr][nc]) {
                    return true;
                }

                if (dfs(grid, visited, nr, nc, r, c, m, n)) {
                    return true;
                }
            }
        }

        return false;
    }
}