class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        char[] ans = new char[n];

        // Try to match target from left to right
        for (int i = 0; i < n; i++) {
            int t = target.charAt(i) - 'a';

            if (freq[t] > 0) {
                // Keep the same character
                ans[i] = target.charAt(i);
                freq[t]--;
            } else {
                // Cannot keep target[i].
                // Try the smallest character greater than target[i].
                for (int c = t + 1; c < 26; c++) {
                    if (freq[c] > 0) {

                        ans[i] = (char) ('a' + c);
                        freq[c]--;

                        fillRemaining(ans, i + 1, freq);

                        return new String(ans);
                    }
                }

                break;
            }
        }

        // Backtrack from right to left
        for (int i = n - 1; i >= 0; i--) {

            int[] temp = new int[26];

            for (char c : s.toCharArray()) {
                temp[c - 'a']++;
            }

            boolean possible = true;

            // Match target[0 ... i-1]
            for (int j = 0; j < i; j++) {
                int c = target.charAt(j) - 'a';

                if (temp[c] == 0) {
                    possible = false;
                    break;
                }

                temp[c]--;
            }

            if (!possible)
                continue;

            int t = target.charAt(i) - 'a';

            // Find smallest character greater than target[i]
            for (int c = t + 1; c < 26; c++) {

                if (temp[c] > 0) {

                    char[] result = target.toCharArray();

                    result[i] = (char) ('a' + c);
                    temp[c]--;

                    fillRemaining(result, i + 1, temp);

                    return new String(result);
                }
            }
        }

        return "";
    }

    private void fillRemaining(char[] ans, int start, int[] freq) {
        int index = start;

        for (int c = 0; c < 26; c++) {
            while (freq[c] > 0) {
                ans[index++] = (char) ('a' + c);
                freq[c]--;
            }
        }
    }
}