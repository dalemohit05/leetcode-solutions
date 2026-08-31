class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int first = -1;
        int prevCritical = -1;
        int min = Integer.MAX_VALUE;
        int index = 1;

        ListNode prev = head;
        ListNode curr = head.next;

        while (curr != null && curr.next != null) {
            if ((curr.val > prev.val && curr.val > curr.next.val) ||
                (curr.val < prev.val && curr.val < curr.next.val)) {

                if (first == -1) {
                    first = index;
                } else {
                    min = Math.min(min, index - prevCritical);
                }

                prevCritical = index;
            }

            prev = curr;
            curr = curr.next;
            index++;
        }

        if (first == -1 || first == prevCritical) {
            return new int[]{-1, -1};
        }

        int max = prevCritical - first;

        return new int[]{min, max};
    }
}