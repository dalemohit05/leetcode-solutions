import java.util.*;

class Solution {
    public int minJumps(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0;

        int max = 1000000;

        boolean[] prime = new boolean[max + 1];
        Arrays.fill(prime, true);
        prime[0] = prime[1] = false;

        for (int i = 2; i * i <= max; i++) {
            if (prime[i]) {
                for (int j = i * i; j <= max; j += i) {
                    prime[j] = false;
                }
            }
        }

        List<Integer>[] map = new ArrayList[max + 1];

        for (int i = 0; i < n; i++) {
            int x = nums[i];

            for (int p = 2; p * p <= x; p++) {
                if (x % p == 0) {
                    if (map[p] == null) {
                        map[p] = new ArrayList<>();
                    }

                    map[p].add(i);

                    while (x % p == 0) {
                        x /= p;
                    }
                }
            }

            if (x > 1) {
                if (map[x] == null) {
                    map[x] = new ArrayList<>();
                }

                map[x].add(i);
            }
        }

        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n];

        queue.offer(0);
        visited[0] = true;

        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                int i = queue.poll();

                if (i == n - 1) {
                    return steps;
                }

                if (i > 0 && !visited[i - 1]) {
                    visited[i - 1] = true;
                    queue.offer(i - 1);
                }

                if (i + 1 < n && !visited[i + 1]) {
                    visited[i + 1] = true;
                    queue.offer(i + 1);
                }

                if (prime[nums[i]] && map[nums[i]] != null) {
                    for (int j : map[nums[i]]) {
                        if (!visited[j]) {
                            visited[j] = true;
                            queue.offer(j);
                        }
                    }

                    map[nums[i]] = null;
                }
            }

            steps++;
        }

        return -1;
    }
}