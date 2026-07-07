import java.util.*;

class Solution {

    public int minimumDifference(int[] nums) {

        int n = nums.length;
        int half = n / 2;

        // Total array sum
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        // Split array into two halves: this method includes start, excludes end
        int[] left = Arrays.copyOfRange(nums, 0, half);
        int[] right = Arrays.copyOfRange(nums, half, n);

        // leftSums[k] = all subset sums formed by choosing exactly k elements
        List<Integer>[] leftSums = generateSubsetSums(left);
        List<Integer>[] rightSums = generateSubsetSums(right);

        // Sort right half sums for binary search
        for (int i = 0; i <= half; i++) {
            Collections.sort(rightSums[i]);
        }

        int ans = Integer.MAX_VALUE;

        // Choose k elements from left half
        for (int k = 0; k <= half; k++) {

            List<Integer> leftList = leftSums[k];

            // Need (half - k) elements from right half
            List<Integer> rightList = rightSums[half - k];

            for (int leftSum : leftList) {

                /*
                 * We want `selectedSum` as close as possible to totalSum/2
                 * selectedSum = leftSum + rightSum
                 * So:  rightSum ≈ totalSum/2 - leftSum
                 */
                double target = totalSum / 2.0 - leftSum;

                int idx = Collections.binarySearch(
                        rightList,
                        (int) target);
                // The negative value is Java's way of encoding "not found + insertion position", and recovering it lets us examine the nearest candidates around where the target would have been inserted.
                // Convert insertion point if not found -> Because we need the actual insertion point.
                if (idx < 0) {
                    idx = -idx - 1;
                }

                // Check candidate at insertion point
                if (idx < rightList.size()) {
                    int selectedSum = leftSum + rightList.get(idx);

                    ans = Math.min(
                            ans,
                            Math.abs(totalSum - 2 * selectedSum));
                }

                // Check previous candidate
                if (idx > 0) {
                    int selectedSum = leftSum + rightList.get(idx - 1);

                    ans = Math.min(
                            ans,
                            Math.abs(totalSum - 2 * selectedSum));
                }
            }
        }

        return ans;
    }

    /*
     * Generate all subset sums grouped by subset size.
     *
     * Example:
     * arr = [3,5]
     *
     * size 0 -> [0]
     * size 1 -> [3,5]
     * size 2 -> [8]
     */
    private List<Integer>[] generateSubsetSums(int[] arr) {

        int n = arr.length;

        List<Integer>[] sums = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++) {
            sums[i] = new ArrayList<>();
        }

        int totalMasks = 1 << n;

        // Generate all subsets using bitmasking
        // A bitmask is just an integer whose binary representation tells us which index elements are included in a subset.
        for (int mask = 0; mask < totalMasks; mask++) {

            int sum = 0;
            int count = 0;

            for (int i = 0; i < n; i++) {

                // If ith bit is set, include arr[i]
                // if mask is 5 and i is 0
                //  mask & (1 << 0) => 101 & 001 => 001 != 0, so 0th bit is set
                if ((mask & (1 << i)) != 0) {
                    sum += arr[i];
                    count++;
                }
            }

            sums[count].add(sum);
        }

        return sums;
    }

    /** for arr = {2,3,5}
    mask=0 -> 000 -> {}         -> sum=0
    mask=1 -> 001 -> {2}        -> sum=2
    mask=2 -> 010 -> {3}        -> sum=3
    mask=3 -> 011 -> {2,3}      -> sum=5
    mask=4 -> 100 -> {5}        -> sum=5
    mask=5 -> 101 -> {2,5}      -> sum=7
    mask=6 -> 110 -> {3,5}      -> sum=8
    mask=7 -> 111 -> {2,3,5}    -> sum=10

    sum[k numbers]
    sums[0] = [0]
    sums[1] = [2,3,5]
    sums[2] = [5,7,8]
    sums[3] = [10]
     */
}

/**
Group1 Sum = selectedSum
Group2 Sum = totalSum - selectedSum
Difference = |Group1 - Group2|
           = |selectedSum - (totalSum - selectedSum)|
           = |2 * selectedSum - totalSum|
           = |totalSum - 2 * selectedSum|
*/
/**
Why does binarySearch return -(insertionPoint) - 1 instead of just -insertionPoint?

If the target is found, binarySearch returns its index (0, 1, 2, ...).

If the target is NOT found, Java still wants to tell us where the element
should be inserted to keep the list sorted. This position is called the
"insertion point".

Java encodes it as:

    returnValue = -(insertionPoint) - 1

instead of just:

    returnValue = -insertionPoint

because insertion point can be 0.

Example:
    insertionPoint = 0

Using -insertionPoint:
    -0 = 0

But 0 is already a valid index meaning "found at index 0", so it would be
impossible to distinguish between:
    - Found at index 0
    - Not found, should be inserted at index 0

To avoid this ambiguity, Java uses:

    returnValue = -(insertionPoint) - 1

Examples:
    insertionPoint = 0 -> return -1
    insertionPoint = 1 -> return -2
    insertionPoint = 2 -> return -3

To recover the insertion point:

    insertionPoint = -(returnValue + 1)
                   = -returnValue - 1

So:

    idx = -idx - 1;

This gives the exact location where the target would be inserted, allowing us
to check the closest elements around that position (idx and idx - 1).
*/
