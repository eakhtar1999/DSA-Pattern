You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed and is protected by a security system with a color code.
You are given two integer arrays nums and colors, both of length n, where nums[i] is the amount of money in the ith house and colors[i] is the color code of that house.
You cannot rob two adjacent houses if they share the same color code.
Return the maximum amount of money you can rob.
Example 1:
Input: nums = [1,4,3,5], colors = [1,1,2,2]
Output: 9
Explanation:
Choose houses i = 1 with nums[1] = 4 and i = 3 with nums[3] = 5 because they are non-adjacent.
Thus, the total amount robbed is 4 + 5 = 9.

## One-line rule
> *"When an adjacency constraint is gated by a matching auxiliary property (same color, same type, same parity), the recurrence branches on that property — same-property adjacent pairs behave exactly like the ungated problem, but the moment the property differs, the constraint vanishes and the two elements can be combined freely."*

## Memory model
1. `dp[i]` = max money robbable considering houses `0..i`.
2. `dp[i] = colors[i]==colors[i-1] ? max(dp[i-1], dp[i-2]+nums[i]) : max(dp[i-1], dp[i-1]+nums[i])`.
3. Base cases: `dp[0] = nums[0]`; `dp[1] = colors[1]==colors[0] ? max(nums[0],nums[1]) : nums[0]+nums[1]`.
4. Answer = `dp[n-1]`.

## All four stages

```java
// Stage 1 — brute-force recursion
private int solve(int i, int[] nums, int[] colors) {
    if (i < 0) return 0;
    if (i == 0) return nums[0];
    int skip = solve(i - 1, nums, colors);
    if (colors[i] == colors[i - 1]) {
        return Math.max(skip, solve(i - 2, nums, colors) + nums[i]);   // must skip i-1 to take i
    } else {
        return Math.max(skip, skip + nums[i]);                        // no constraint at all between i, i-1
    }
}
```

```java
// Stage 2 — memoized (cache check after base cases, as always)
private int solveMemo(int i, int[] nums, int[] colors, Map<Integer, Integer> memo) {
    if (i < 0) return 0;
    if (i == 0) return nums[0];
    if (memo.containsKey(i)) return memo.get(i);
    int skip = solveMemo(i - 1, nums, colors, memo);
    int res = (colors[i] == colors[i - 1])
        ? Math.max(skip, solveMemo(i - 2, nums, colors, memo) + nums[i])
        : Math.max(skip, skip + nums[i]);
    memo.put(i, res);
    return res;
}
```

```java
// Stage 3 — tabulation
public int rob(int[] nums, int[] colors) {
    int n = nums.length;
    if (n == 1) return nums[0];
    int[] dp = new int[n];
    dp[0] = nums[0];
    dp[1] = (colors[1] == colors[0]) ? Math.max(nums[0], nums[1]) : nums[0] + nums[1];
    for (int i = 2; i < n; i++) {
        dp[i] = (colors[i] == colors[i - 1])
            ? Math.max(dp[i - 1], dp[i - 2] + nums[i])
            : Math.max(dp[i - 1], dp[i - 1] + nums[i]);
    }
    return dp[n - 1];
}
```

```java
// Stage 4 — space-optimized
public int rob(int[] nums, int[] colors) {
    int n = nums.length;
    if (n == 1) return nums[0];
    int prev2 = nums[0];
    int prev1 = (colors[1] == colors[0]) ? Math.max(nums[0], nums[1]) : nums[0] + nums[1];
    for (int i = 2; i < n; i++) {
        int curr = (colors[i] == colors[i - 1])
            ? Math.max(prev1, prev2 + nums[i])
            : Math.max(prev1, prev1 + nums[i]);
        prev2 = prev1;
        prev1 = curr;
    }
    return prev1;
}
```

## Iteration rules — what's new versus House Robber I
1. **Both base cases now need the color check too**, not just the main loop — `dp[1]` is wrong if you forget to test `colors[1]==colors[0]` there; it's easy to only branch inside the loop and leave the seed value using the old unconditional formula.
2. **The different-color branch reads `dp[i-1]` twice conceptually** (`max(prev1, prev1+nums[i])`) rather than reading two different indices — a common slip is to accidentally write `dp[i-2]` in that branch out of habit from the same-color case, which would silently under-count by ignoring whatever was optimal at `i-1`.
3. Since `nums[i] >= 0` per the problem (amounts of money), `max(prev1, prev1+nums[i])` always simplifies to just `prev1+nums[i]` — the `Math.max` is defensive rather than load-bearing here, worth recognizing so you're not confused about why it looks redundant.


Let's verify the recurrence before writing anything up — the color condition changes *when* the adjacency constraint even applies, which is a new twist worth testing carefully.All four stages verified against brute-force subset enumeration over 5000 random cases plus the given example. Here's the breakdown.

## Parsing — what's genuinely new here

- "cannot rob two adjacent houses **if they share the same color code**" — this isn't a new *kind* of constraint, it's the same "skip-or-take" constraint from House Robber I, but now **gated by a condition on an auxiliary array**. That's a new entry worth adding to your parsing guide's §5: previously "cannot rob adjacent" was unconditional; here the constraint only *activates* when `colors[i] == colors[i-1]`. The code decision this forces: **the recurrence itself branches on that condition** — when it's true, use the classic constrained formula; when it's false, the constraint simply doesn't exist for that pair, and you may combine both houses freely.

## Eligibility check
- Optimal substructure: at house `i`, if same color as `i-1`, exactly the classic skip-or-take applies. If different color, there's no restriction between `i` and `i-1` at all, so the best-through-`i` is just best-through-`i-1` plus `nums[i]`. ✅
- Overlapping subproblems: same shape as House Robber I. ✅
- State: one integer `i` — but the transition now also reads `colors[i]` and `colors[i-1]` to decide *which* formula to apply (state itself is still just `i`; the color check is a lookup, not an extra dimension). ✅
- Signal words: "maximum," "cannot rob adjacent... if... same color" → DP-1D, conditionally-gated skip-or-take.

## The key correctness argument (worth stating explicitly, since it's not obvious)

When colors differ, is it *always* safe to add `nums[i]` on top of `dp[i-1]`, regardless of whether house `i-1` was actually robbed in that optimal solution? **Yes** — because the constraint only ever forbids a *same-color adjacent pair*. If colors differ, robbing `i` is compatible with `i-1` being robbed **or** skipped; there's no scenario where adding `nums[i]` to the best-through-`i-1` total creates an invalid configuration. That's why the different-color branch doesn't need to look at `dp[i-2]` at all — unlike the same-color branch, which must fall back to `dp[i-2]` precisely because taking `i` there *does* force `i-1` to be skipped.
