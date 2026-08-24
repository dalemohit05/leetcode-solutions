class Solution {
    public boolean canBeEqual(String s1, String s2) {
        char[] a = {s1.charAt(0), s1.charAt(2)};
        char[] b = {s2.charAt(0), s2.charAt(2)};

        char[] c = {s1.charAt(1), s1.charAt(3)};
        char[] d = {s2.charAt(1), s2.charAt(3)};

        return ((a[0] == b[0] && a[1] == b[1]) ||
                (a[0] == b[1] && a[1] == b[0]))
            &&
               ((c[0] == d[0] && c[1] == d[1]) ||
                (c[0] == d[1] && c[1] == d[0]));
    }
}