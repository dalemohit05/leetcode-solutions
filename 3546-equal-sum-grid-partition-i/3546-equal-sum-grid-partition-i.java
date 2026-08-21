class Solution {
    public boolean canPartitionGrid(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        long total = 0;

        // Calculate total sum
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                total += grid[i][j];
            }
        }

        // Horizontal cuts
        long current = 0;

        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n; j++) {
                current += grid[i][j];
            }

            if (current == total - current) {
                return true;
            }
        }

        // Vertical cuts
        current = 0;

        for (int j = 0; j < n - 1; j++) {
            for (int i = 0; i < m; i++) {
                current += grid[i][j];
            }

            if (current == total - current) {
                return true;
            }
        }

        return false;
    }
}
