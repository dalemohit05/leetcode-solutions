class Solution {
    static final long LIMIT = 1_000_001L;

    public String smallestPalindrome(String s, int k) {
        int[] freq = new int[26];

        for (char c : s.toCharArray())
            freq[c - 'a']++;

        int[] half = new int[26];
        String mid = "";

        int halfLen = 0;

        for (int i = 0; i < 26; i++) {
            half[i] = freq[i] / 2;
            halfLen += half[i];

            if ((freq[i] & 1) == 1)
                mid = String.valueOf((char) ('a' + i));
        }

        long total = countWays(half, halfLen);

        if (total < k)
            return "";

        StringBuilder left = new StringBuilder();

        for (int pos = 0; pos < halfLen; pos++) {

            for (int c = 0; c < 26; c++) {

                if (half[c] == 0)
                    continue;

                half[c]--;

                long ways = countWays(half, halfLen - pos - 1);

                if (ways >= k) {
                    left.append((char) ('a' + c));
                    break;
                } else {
                    k -= ways;
                    half[c]++;
                }
            }
        }

        String right = left.reverse().toString();
        left.reverse();

        return left.toString() + mid + right;
    }

    private long countWays(int[] cnt, int total) {
        long res = 1;

        int remaining = total;

        for (int i = 0; i < 26; i++) {
            int c = cnt[i];
            if (c == 0)
                continue;

            res *= combLimited(remaining, c);
            if (res > LIMIT)
                return LIMIT;

            remaining -= c;
        }

        return Math.min(res, LIMIT);
    }

    private long combLimited(int n, int r) {
        if (r > n) return 0;
        r = Math.min(r, n - r);

        long ans = 1;

        for (int i = 1; i <= r; i++) {
            ans = ans * (n - r + i) / i;
            if (ans > LIMIT)
                return LIMIT;
        }

        return ans;
    }
}
