
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.
Example 1:
![<img width="412" height="161" alt="image" src="https://github.com/user-attachments/assets/31fe01aa-d6cb-43fb-940c-494a136a70de" />]

Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.

### 1. Two Pointers (The Main Strategy)
* **Decision:** Why move from both ends?
* **Logic:** Water trapping is a **boundary problem**. By starting at the edges, you effectively "squeeze" the problem inward. Since the amount of water is limited by the **shorter** wall, you only ever need to move the pointer at the shorter boundary to find a potentially taller one.

### 2. The Bottleneck Principle (Min-Max Logic)
* **Decision:** Why compare `leftMax` and `rightMax`?
* **Logic:** At any point, the water level is $\min(\text{leftMax}, \text{rightMax})$. By always processing the side with the **smaller** maximum, you guarantee that the "other side" (the larger max) is already tall enough to support whatever water you calculate on the current side. You don't need to know the *exact* height of the far-off wall, just that it's taller than your current side.

### 3. Dynamic Boundary Tracking (`leftMax`, `rightMax`)
* **Decision:** Why update these values before calculating `res`?
* **Logic:** These variables represent the "container walls."
* If the current height is **less than** the max, you trap the difference: `max - current`.
* If the current height is **greater than** the max, you update the max: `max = current`. No water is trapped at the peak itself.



### 4. Memory vs. Speed Trade-off

* **Decision:** Choosing Two Pointers over Prefix/Suffix arrays.
* **Logic:** * **Prefix/Suffix Arrays:** Require $O(n)$ space to store the max heights.
* **Two Pointers:** Accomplishes the same thing in $O(1)$ space by calculating those "maxes" on the fly. This is the optimal "Interview Ready" decision.

```java
public class Solution {
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int l = 0, r = height.length - 1;
        int leftMax = height[l], rightMax = height[r];
        int res = 0;
        while (l < r) {
            if (leftMax < rightMax) {
                l++;
                leftMax = Math.max(leftMax, height[l]);
                res += leftMax - height[l];
            } else {
                r--;
                rightMax = Math.max(rightMax, height[r]);
                res += rightMax - height[r];
            }
        }
        return res;
    }
}
```

 **Container With Most Water** Vs **Trapping Rain Water**, you are seeing the "Two Pointers" technique evolve from a simple comparison to a complex boundary-tracking system.

Here is a comparison of the decisions and strategies used in both, so you can connect the dots in your notes:

### 1. The Strategy: "Greedy" Pointer Movement

* **Container With Most Water:** You move the pointer pointing to the **shorter line**.
* *Decision:* The area is limited by the shorter line. Moving the taller line can only make the area smaller (same height or worse, smaller width).


* **Trapping Rain Water:** You also process the side with the **smaller maximum height** (`leftMax < rightMax`).
* *Decision:* Water level is determined by the "bottleneck" (the lower of the two boundaries). By processing the smaller side, you ensure you don't need to know the exact height of the taller side—you just need to know it's "tall enough" to hold the water.



### 2. The Strategy: Tracking State

* **Container With Most Water:** You only care about the **current** heights at `l` and `r`.
* *Note:* It’s a "stateless" calculation (Width × Min Height).


* **Trapping Rain Water:** You must track the **historical** maximums (`leftMax` and `rightMax`).
* *Note:* It’s a "stateful" calculation. You need to know the tallest wall you've seen *so far* to know if a "dip" or "hole" exists where water can be trapped.



### 3. The Strategy: Result Accumulation

* **Container With Most Water:** You use `Math.max(res, area)` because you only want the single **best** container.
* **Trapping Rain Water:** You use `res += ...` because you are **summing up** many small pockets of water across the entire terrain.

---

### Comparison Summary Table for your Notes

| Feature | [Container With Most Water](https://leetcode.com/problems/container-with-most-water/) | [Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/) |
| --- | --- | --- |
| **Pointer Movement** | Move the shorter line. | Move from the side with the smaller max. |
| **Calculation** | `Math.min(h1, h2) * width` | `CurrentMax - CurrentHeight` |
| **Variables** | Just pointers and `maxArea`. | Pointers, `leftMax`, and `rightMax`. |
| **Goal** | Find the single maximum volume. | Find the total accumulated volume. |

### Why this matters for the "Next" Problem:

When you see a problem involving arrays and "boundaries" or "ranges," ask yourself:

1. Do I need the **global best** (like Container)? → Use Two Pointers + `Math.max`.
2. Do I need to **accumulate** values (like Trapping Water)? → Use Two Pointers + Boundary Tracking.


