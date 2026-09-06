class Solution {
    public int minimumDistance(int[] nums) {
        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    for (int k = j + 1; k < nums.length; k++) {
                        if (nums[i] == nums[k]) {
                            int distance = 2 * (k - i);
                            ans = Math.min(ans, distance);
                        }
                    }
                }
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}