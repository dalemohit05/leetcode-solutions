class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] res = new long[k];
        long[] dp = new long[k];
        
        for (int num : nums) {
            int v = num % k;
            long[] nextDp = new long[k];
            
            // Subarray starting at the current element
            nextDp[v]++;
            
            // Extend subarrays ending at the previous index
            for (int r = 0; r < k; r++) {
                if (dp[r] > 0) {
                    nextDp[(r * v) % k] += dp[r];
                }
            }
            
            // Accumulate counts into the final result
            for (int r = 0; r < k; r++) {
                res[r] += nextDp[r];
            }
            
            dp = nextDp;
        }
        
        return res;
    }
}