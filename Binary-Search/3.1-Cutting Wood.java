// Cutting Wood similar to Koko eating banana with some variations

// You are given an array representing the heights of trees, and an integer k representing the total length of wood that needs to be cut.

// For this task, a woodcutting machine is set to a certain height, H. The machine cuts off the top part of all trees taller than H, while trees shorter than H remain untouched. Determine the highest possible setting of the woodcutter (H) so that it cuts at least k meters of wood.

// Assume the woodcutter cannot be set higher than the height of the tallest tree in the array.

// Example:
// Image represents a bar chart illustrating a coding pattern, likely related to data structures or algorithms.  The horizontal axis (labeled 'i' at the far right) represents an index or position, while the vertical axis is labeled 'height'. Four bars are shown, with heights varying. The first bar, at index 0, has a height of 2. The second bar, at index 1, has a base height of 3 (light gray) and an additional height of 3 (orange hatched pattern), totaling 6. The third bar, at index 2, has a height of 3. The fourth bar, at index 3, has a base height of 3 (light gray) and an additional height of 5 (orange hatched pattern), totaling 8. A horizontal orange line is drawn at height 3, labeled 'H = 3'.  Braces in light blue indicate the additional height above this line for bars at indices 1 and 3, with the values '3' and '5' respectively written next to them.  The chart visually demonstrates a pattern where a base height is consistently present, with varying additional heights stacked on top.
// Input: heights = [2, 6, 3, 8], k = 7
// Output: 3
// Explanation: The highest possible height setting that yields at least k = 7 meters of wood is 3, which yields 8 meters of wood. Any height setting higher than this will yield less than 7 meters of wood.


import java.util.ArrayList;

public class Main {
    public int cutting_wood(ArrayList<Integer> heights, int k) {
        int left = 0;
        int right = max(heights);
        while (left < right) {
            // Bias the midpoint to the right during the upper-bound binary search.
            int mid = (left + right) / 2 + 1;
            if (cutsEnoughWood(mid, k, heights)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return right;
    }

    // Determine if the current value of 'H' cuts at least 'k' meters of wood.
    public boolean cutsEnoughWood(int H, int k, ArrayList<Integer> heights) {
        int wood_collected = 0;
        for (int height : heights) {
            if (height > H) {
                wood_collected += (height - H);
            }
        }
        return wood_collected >= k;
    }

    private int max(ArrayList<Integer> list) {
        int maxVal = Integer.MIN_VALUE;
        for (int num : list) {
            if (num > maxVal) {
                maxVal = num;
            }
        }
        return maxVal;
    }
}
