class Solution {
    public int longestBalanced(String s) {
        int n = s.length();
        int ans = 1;

        for (int i = 0; i < n; i++) {
            int[] freq = new int[26];

            for (int j = i; j < n; j++) {
                freq[s.charAt(j) - 'a']++;

                int target = 0;
                boolean balanced = true;

                for (int f : freq) {
                    if (f > 0) {
                        if (target == 0) {
                            target = f;
                        } else if (target != f) {
                            balanced = false;
                            break;
                        }
                    }
                }

                if (balanced) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }

        return ans;
    }
}