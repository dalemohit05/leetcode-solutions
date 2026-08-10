class Solution {
    public int maxDistance(int[] nums1, int[] nums2) {

        int i = 0;
        int j = 0;
        int maxDistance = 0;

        while (i < nums1.length && j < nums2.length) {

            if (i <= j && nums1[i] <= nums2[j]) {

                maxDistance = Math.max(maxDistance, j - i);

                j++;

            } else {
                i++;

                // Make sure i <= j
                if (i > j) {
                    j = i;
                }
            }
        }

        return maxDistance;
    }
}


