# Java's `Collections.binarySearch()` and the "Tails" Technique for O(n log n) LIS

A cross-pattern reference, not tied to one problem — the `-(insertionPoint+1)` encoding shows up anywhere Java's binary search is used to find where something *would* go, not just LIS. LIS (LeetCode 300) is the worked example because it's the first place you hit it, and because it's the exception case your DP Progression doc already flagged (unbounded lookback — `dp[i]` depends on *every* `j < i`, not a constant `k`, so the rolling-window space-optimization trick doesn't apply; this binary-search technique is a genuinely different algorithm, not an optimized version of the DP).

---

## 1. What `Collections.binarySearch()` actually returns

It needs to communicate two different things through one `int`:
- **Found:** here's the index.
- **Not found:** here's where it *would* go to keep the list sorted (the insertion point).

**Found → returns the index directly (always `>= 0`).**

**Not found → returns `-(insertionPoint + 1)`** — always `< 0`.

### Why encode it this way, specifically

1. **Why negative at all?** So the sign alone tells you which case happened. If "not found" returned the insertion point as a plain non-negative number, you couldn't distinguish "found at index 0" from "would insert at index 0" — both would just be `0`.
2. **Why `+1` before negating, not just `-insertionPoint`?** Because insertion point `0` negated is still `0` (`-0 == 0` for integers) — the exact same collision, just moved. Adding `1` first shifts every insertion point up by one before negating, so insertion point `0` becomes `-1`, insertion point `1` becomes `-2`, and so on. Now every not-found result is *strictly* negative, no exceptions, no collisions.

### Recovering the insertion point

```java
int result = Collections.binarySearch(list, key);
if (result < 0) {
    int insertionPoint = -(result + 1);   // equivalently: -result - 1
}
```

### Worked example

`tails = [2, 5, 7]`, searching for `6`:
- `6` isn't in the list; it belongs between `5` and `7` → insertion point `2`.
- Returned value: `-(2 + 1) = -3`.
- Recovered: `-(-3) - 1 = 3 - 1 = 2`. ✅

A common shortcut, since you almost always want the insertion point regardless of found/not-found:
```java
int idx = Collections.binarySearch(list, key);
if (idx < 0) idx = -(idx + 1);
// idx is now "the position for key" either way
```

---

## 2. Using this to build LIS in O(n log n) — the "tails" / patience-sorting technique

### The core idea
Maintain an array `tails` where **`tails[i]` = the smallest possible tail value among all increasing subsequences of length `i+1` seen so far.** `tails` is always sorted ascending — that's exactly what makes binary search valid on it.

For each `num` in `nums`:
1. Binary search `tails` for `num`, recover the insertion point `idx` (as above).
2. If `idx == tails.size()` — `num` is bigger than everything in `tails` — **append** it (this extends the longest sequence found so far by one).
3. Otherwise — **overwrite** `tails.set(idx, num)`.

The answer is `tails.size()` at the end.

```java
public int lengthOfLIS(int[] nums) {
    List<Integer> tails = new ArrayList<>();
    for (int num : nums) {
        int idx = Collections.binarySearch(tails, num);
        if (idx < 0) idx = -(idx + 1);          // recover insertion point
        if (idx == tails.size()) {
            tails.add(num);                      // extend
        } else {
            tails.set(idx, num);                 // overwrite, NOT insert
        }
    }
    return tails.size();
}
```
Verified against the O(n²) brute-force DP over 5000 random arrays plus the standard example (`[10,9,2,5,3,7,101,18] → 4`) — all match.

---

## 3. Why overwrite, never insert-with-shift — and why it's still correct

**Performance reason:** `List.add(index, value)` in the middle of an `ArrayList` shifts every element after `index` over by one — O(n) per call. Doing that for every element of `nums` turns the whole algorithm back into O(n²), the exact complexity you were trying to escape by using binary search in the first place. `set(index, value)` is O(1) — no shifting, just an overwrite. That's the entire performance win.

**Correctness reason (why overwriting doesn't break anything):** `tails[i]` only ever needs to hold the *smallest* tail value achievable for length `i+1` — that's the whole invariant. When you overwrite `tails[idx]` with a smaller-or-equal `num`, you're recording "I found a way to achieve a subsequence of this same length that ends even smaller" — which is strictly at least as good, and nothing about that changes what any other index in `tails` means. No index depends on a previous value at another index being preserved, so overwriting in place is safe.

---

## 4. The one thing this technique does NOT give you

**`tails` is not an actual subsequence that appeared in `nums`** — because of all the overwriting, the values sitting in `tails` at the end don't necessarily form a real increasing subsequence you could point to in the original array. Only `tails.size()` (the *length*) is guaranteed correct.

If a problem asks you to **reconstruct** the actual longest increasing subsequence (not just its length), this technique alone isn't enough — you'd need a separate parent-pointer array recorded alongside each element of `nums` (tracking, for each `num`, which earlier index it extended), then walk that chain backward from wherever the longest chain ended. That's a genuinely different bookkeeping addition, not a tweak to `tails` itself — worth remembering so it doesn't surprise you mid-problem if the ask changes from "length" to "the subsequence."

---

## 5. One-line rule to memorize this by

> *"`binarySearch` encodes 'not found' as `-(insertionPoint+1)` so the sign alone disambiguates found vs. not-found, with the `+1` preventing a 0/-0 collision; recover the real index with `-(result+1)`. In the LIS `tails` trick, that recovered index is always used to overwrite (O(1)), never to insert-with-shift (O(n)) — overwriting preserves the 'smallest tail per length' invariant without disturbing anything else, which is what keeps the whole algorithm at O(n log n)."*
