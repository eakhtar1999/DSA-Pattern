
// ----     496. Next Greater Element I
// The next greater element of some element x in an array is the first greater element that is to the right of x in the same array.
// You are given two distinct 0-indexed integer arrays nums1 and nums2, where nums1 is a subset of nums2.
// For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j] and determine the next greater element 
// of nums2[j] in nums2. If there is no next greater element, then the answer for this query is -1.

// Return an array ans of length nums1.length such that ans[i] is the next greater element as described above.

// Example 1:

// Input: nums1 = [4,1,2], nums2 = [1,3,4,2]
// Output: [-1,3,-1]
// Explanation: The next greater element for each value of nums1 is as follows:
// - 4 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
// - 1 is underlined in nums2 = [1,3,4,2]. The next greater element is 3.
// - 2 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.

  
class Solution {
    // monotonic stack. similar problem: daily temperature
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> ng = new HashMap<>();
        Deque<Integer> st = new ArrayDeque<>();

        for (int num : nums2) {
            while (!st.isEmpty() && st.peek() < num) {
                ng.put(st.pop(), num);
            }
            st.push(num);
        }

        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            res[i] = ng.getOrDefault(nums1[i], -1);
        }
        return res;        
    }
}


//  --- 503. Next Greater Element II

// Given a circular integer array nums (i.e., the next element of nums[nums.length - 1] is nums[0]), 
// return the next greater number for every element in nums.
// The next greater number of a number x is the first greater number to its traversing-order next in the array, 
// which means you could search circularly to find its next greater number. If it doesn't exist, return -1 for this number.

// Example 1:
// Input: nums = [1,2,1]
// Output: [2,-1,2]
// Explanation: The first 1's next greater number is 2; 
// The number 2 can't find next greater number. 
// The second 1's next greater number needs to search circularly, which is also 2.

// 💡 This is similar to 'Next Greater Element I', but the array is circular. How can you simulate the circular nature?
// 💡 Use a monotonic decreasing stack that stores indices (not values). When you find a greater element, 
//    you can update multiple waiting indices at once.
// 💡 Iterate through the array twice (2n iterations using i % n). Only push indices during the first pass, 
//    but check for greater elements in both passes.

class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, -1);
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < 2 * n; i++) {
            int idx = i % n;
            while (!stack.isEmpty() && nums[stack.peek()] < nums[idx]) {
                result[stack.pop()] = nums[idx];
            }
            if (i < n) {
                stack.push(i);
            }
        }
        return result;
    }
}
