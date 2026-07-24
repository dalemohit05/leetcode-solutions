class Solution {
    public int uniqueXorTriplets(int[] nums) {
        final int MAX = 2048;

        boolean[][] dp = new boolean[4][MAX];
        dp[0][0] = true;

        for (int taken = 0; taken < 3; taken++) {
            for (int x = 0; x < MAX; x++) {
                if (!dp[taken][x]) continue;

                for (int num : nums) {
                    dp[taken + 1][x ^ num] = true;
                }
            }
        }

        int ans = 0;
        for (boolean possible : dp[3]) {
            if (possible) ans++;
        }

        return ans;
    }
}