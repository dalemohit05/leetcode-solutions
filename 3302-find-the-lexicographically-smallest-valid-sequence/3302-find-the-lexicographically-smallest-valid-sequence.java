class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        int[] suf = new int[n + 1];

        int j = m - 1;

        for (int i = n - 1; i >= 0; i--) {
            suf[i] = suf[i + 1];

            if (j >= 0 && word1.charAt(i) == word2.charAt(j)) {
                suf[i]++;
                j--;
            }
        }

        int[] ans = new int[m];

        int p = 0;
        int q = 0;
        boolean changed = false;

        while (p < n && q < m) {

            if (word1.charAt(p) == word2.charAt(q)) {
                ans[q] = p;
                p++;
                q++;
            } 
            else if (!changed && suf[p + 1] >= m - q - 1) {
                ans[q] = p;
                p++;
                q++;
                changed = true;
            } 
            else {
                p++;
            }
        }

        if (q != m) {
            return new int[0];
        }

        return ans;
    }
}