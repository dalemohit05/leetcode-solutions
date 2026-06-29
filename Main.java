public class Main {

    public static void main(String[] args) {

        Solution solution = new Solution();

        String[] patterns = {"a", "abc", "bc", "d"};
        String word = "abc";

        int result = solution.numOfStrings(patterns, word);

        System.out.println("Output: " + result);
    }
}

class Solution {

    public int numOfStrings(String[] patterns, String word) {

        int count = 0;

        for (String pattern : patterns) {
            if (word.contains(pattern)) {
                count++;
            }
        }

        return count;
    }
}