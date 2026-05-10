// Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
// The overall run time complexity should be O(log (m+n)).

// Example 1:
// Input: nums1 = [1,3], nums2 = [2]
// Output: 2.00000
// Explanation: merged array = [1,2,3] and median is 2.

// Example 2:
// Input: nums1 = [1,2], nums2 = [3,4]
// Output: 2.50000
// Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.

// Similar problems you should practice:

// ✅ Median of two sorted arrays (this one)
// ✅ Kth smallest in two sorted arrays
// ✅ Find k-th element in merged arrays
// ✅ Split array problems (some variations)

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int len1 = nums1.length, len2 = nums2.length;
        int left = 0, right = len1;

        while (left <= right) {
            // part1 + part2 == (len1 + len2 + 1)/2
            int part1 = (left + right) / 2;
            int part2 = (len1 + len2 + 1) / 2 - part1;

            int maxLeft1 = (part1 == 0) ? Integer.MIN_VALUE : nums1[part1 - 1];
            int minRight1 = (part1 == len1) ? Integer.MAX_VALUE : nums1[part1];
            int maxLeft2 = (part2 == 0) ? Integer.MIN_VALUE : nums2[part2 - 1];
            int minRight2 = (part2 == len2) ? Integer.MAX_VALUE : nums2[part2];

            if (maxLeft1 <= minRight2 && maxLeft2 <= minRight1) {
                if ((len1 + len2) % 2 == 0) {
                    return (Math.max(maxLeft1, maxLeft2) + Math.min(minRight1, minRight2)) / 2.0;
                } else {
                    return (double) Math.max(maxLeft1, maxLeft2);
                }
            } else if (maxLeft1 > minRight2) {
                right = part1 - 1;
            } else {
                left = part1 + 1;
            }
        }
        return 0.0;        
    }
}
