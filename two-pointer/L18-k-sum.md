```text
Given an array nums of n integers, return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:
0 <= a, b, c, d < n
a, b, c, and d are distinct.
nums[a] + nums[b] + nums[c] + nums[d] == target
You may return the answer in any order.

Example 1:
Input: nums = [1,0,-1,0,-2,2], target = 0
Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]

Hints- Array + 2 pointer + Sorting + recursion
three-methods to remember:
`fourSum()` initializes the data.
`kSum()` recursively fixes values.
`twoSum()` finds the final pair using two pointers.

Approach
1. Sort the array.
2. Recursively fix values until only two values remain.
3. Use the two-pointer technique to find the final two values.
4. Skip duplicates at every level to avoid duplicate quadruplets.
5. Use `long` for sums to prevent integer overflow.
```

```java
import java.util.*;

public class Solution {
    private List<List<Integer>> res;
    private List<Integer> quad;

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        res = new ArrayList<>();
        quad = new ArrayList<>();
        kSum(nums, 4, 0, target);
        return res;
    }

    private void kSum(int[] nums, int k, int start, long target) {
        if (k == 2) {
            twoSum(nums, start, target);
            return;
        }

        for (int i = start; i + k - 1 < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) continue; // avoid duplicate i

            quad.add(nums[i]); // i is fixed so we can add in quad
            kSum(nums, k - 1, i + 1, target - nums[i]); // we fix i and decrease target
            quad.remove(quad.size() - 1); // backtrack, remove chosen i from quad
        }
    }

    private void twoSum(int[] nums, int start, long target) {
        int l = start, r = nums.length - 1;

        while (l < r) {
            long sum = (long) nums[l] + nums[r];

            if (sum < target) {
                l++;
            } else if (sum > target) {
                r--;
            } else {
                // prepare new quadruplets, till now quad has all fixed elements chosen recursively
                res.add(new ArrayList<>(quad));
                res.get(res.size() - 1).add(nums[l]);
                res.get(res.size() - 1).add(nums[r]);

                l++;
                r--;

                // avoid l and r duplicates
                while (l < r && nums[l] == nums[l - 1]) l++;

                // It is a small optimization that skips unnecessary checks.
                while (l < r && nums[r] == nums[r + 1]) r--;
            }
        }
    }
}
```

```text
Complexity
- **Time:** O(n^{k-1})
- For 4Sum: O(n^3)
- **Auxiliary space:** O(k) for recursion and the current quadruplet.
- **Output space:** O(m), where m is the number of quadruplets returned.
```


