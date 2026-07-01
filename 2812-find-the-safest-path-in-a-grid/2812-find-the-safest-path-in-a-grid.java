import java.util.*;

class Solution {
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();

        int[][] dist = new int[n][n];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        Queue<int[]> queue = new LinkedList<>();

        // Multi-source BFS: Start from all thieves
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    dist[i][j] = 0;
                    queue.offer(new int[]{i, j});
                }
            }
        }

        int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0], y = curr[1];

            for (int[] d : directions) {
                int nx = x + d[0];
                int ny = y + d[1];

                if (nx >= 0 && ny >= 0 && nx < n && ny < n &&
                    dist[nx][ny] == Integer.MAX_VALUE) {

                    dist[nx][ny] = dist[x][y] + 1;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }

        int low = 0;
        int high = 2 * n;
        int ans = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (canReach(mid, dist, n, directions)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }

    private boolean canReach(int safe, int[][] dist, int n, int[][] directions) {

        if (dist[0][0] < safe) return false;

        boolean[][] visited = new boolean[n][n];
        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[]{0, 0});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0], y = curr[1];

            if (x == n - 1 && y == n - 1) {
                return true;
            }

            for (int[] d : directions) {
                int nx = x + d[0];
                int ny = y + d[1];

                if (nx >= 0 && ny >= 0 && nx < n && ny < n &&
                    !visited[nx][ny] &&
                    dist[nx][ny] >= safe) {

                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }

        return false;
    }
}