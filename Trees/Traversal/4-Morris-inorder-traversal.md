# Trees: Morris Traversal (Inorder + Preorder)

---
 
## 1. How to recognize this pattern from the question
 
Say "Morris" when you see:
 
- An inorder or preorder traversal with **O(1) extra space**, or "no recursion, no stack."
- Follow-ups like "Kth smallest in BST", "Recover BST", or "Validate BST" **in O(1) space**.
**Anti-signals:**
- O(h) space is acceptable → use recursive DFS or iterative DFS with a `Deque`. They're simpler and less bug-prone. Morris is the answer to a *follow-up*, not your default.
- Postorder → Morris postorder needs right-chain reversal and is rarely asked. Use one stack, or do Ro-R-L preorder and reverse the result.
- Level-by-level structure → BFS with a queue, not Morris.
---
 
## 2. Memory model — the lines to code from
 
- **State:** `cur` is the node being processed. `pred` is the rightmost node of `cur.left`, which is cur's inorder predecessor.
- **Three cases:**
  1. **No left child** → visit `cur` (both traversals), go right.
  2. **Left exists, `pred.right == null` (first visit)** → create thread `pred.right = cur`, go left. *Preorder visits here.*
  3. **Left exists, `pred.right == cur` (second visit)** → left subtree is done; remove thread `pred.right = null`, go right. *Inorder visits here.*
- **Invariant:** "When I reach `cur` through a thread, its whole left subtree has already been processed."
- **The only difference between the two traversals:** preorder records when the thread is **created**; inorder records when the thread is **removed**. The no-left case records in both.
---
 
## 3. Variables and their jobs
 
| Variable | Job |
|---|---|
| `cur` | The node being processed. Moves exactly once per loop iteration: left after threading, right otherwise. |
| `pred` | A scout that walks to the rightmost node of `cur.left`. It never replaces `cur`. |
| `pred.right` | Doubles as a "have I been here" flag: `null` = first visit, `cur` = second visit. |
| `cur.right` (in case 1) | Either a real right child, or a thread that jumps back up to an ancestor. This jump is how Morris returns without a stack. |
 
---
 
## 4. One-line rule
 
> *"No left: record and go right. Otherwise find the rightmost node of the left subtree. If it's empty, thread it and go left (preorder records here). If it already points to me, cut it and go right (inorder records here)."*
 
---
 
## 5. Iteration rules — the NPE and infinite-loop traps
 
1. **`pred` starts at `cur.left`, never at `cur`.** Safe because you're in the `else` branch, so `cur.left != null`.
2. **Only `pred` scouts.** If you catch yourself reassigning `cur` inside the predecessor search, you've lost the node you're processing.
3. **The predecessor loop needs both stop conditions:** `pred.right != null && pred.right != cur`. Without the second one, on the second visit the loop follows the thread back to `cur` and cycles forever.
4. **Every path through the loop body moves `cur` exactly once.** A path that doesn't move it is an infinite loop.
5. **Every thread created must be removed.** Otherwise the caller's tree comes back corrupted, and any later traversal cycles.
6. **The thread direction is `pred.right = cur`**, from predecessor to current, never the reverse.
---
 
## 6. Code — one method for both traversals (verified)
 
```java
class Solution {
    // preorder = true  -> Ro, L, R
    // preorder = false -> L, Ro, R (inorder)
    public List<Integer> morris(TreeNode root, boolean preorder) {
        List<Integer> res = new ArrayList<>();
        TreeNode cur = root;
        while (cur != null) {
            // CASE 1: no left subtree -> record (BOTH traversals), go right
            //         (right may be a real child OR a thread back up to an ancestor)
            if (cur.left == null) {
                res.add(cur.data);
                cur = cur.right;
            } else {
                // find inorder predecessor = rightmost node of left subtree
                // stop at null (no thread yet) OR at cur (thread exists)
                TreeNode pred = cur.left;
                while (pred.right != null && pred.right != cur) {
                    pred = pred.right;
                }
                if (pred.right == null) {
                    // CASE 2: first visit -> create thread, go left
                    if (preorder) res.add(cur.data);      // PREORDER records here
                    pred.right = cur;
                    cur = cur.left;
                } else {
                    // CASE 3: second visit -> left subtree done, remove thread, go right
                    if (!preorder) res.add(cur.data);     // INORDER records here
                    pred.right = null;                    // restore original tree
                    cur = cur.right;
                }
            }
        }
        return res;
    }
}
```
 
- *Complexity:* O(n) time (each edge is walked at most ~3 times), O(1) extra space.
### Quick trace — tree `1 → (2 → (4, 5), 3)`
 
| Step | `cur` | What happens | Preorder output | Inorder output |
|---|---|---|---|---|
| 1 | 1 | pred = 5, no thread → thread 5→1, go left | **1** | — |
| 2 | 2 | pred = 4, no thread → thread 4→2, go left | **2** | — |
| 3 | 4 | no left → record, follow thread to 2 | **4** | **4** |
| 4 | 2 | thread 4→2 exists → cut, go right to 5 | — | **2** |
| 5 | 5 | no left → record, follow thread to 1 | **5** | **5** |
| 6 | 1 | thread 5→1 exists → cut, go right to 3 | — | **1** |
| 7 | 3 | no left → record, `cur` = null | **3** | **3** |
 
Preorder: `[1, 2, 4, 5, 3]` · Inorder: `[4, 2, 5, 1, 3]`
 
---
 
## 7. Interview explanation (say this out loud)
 
> I use Morris traversal to walk the tree without recursion or a stack, in O(1) extra space. For each node, if it has no left child, I record it and move right. Otherwise I find its inorder predecessor, the rightmost node in its left subtree. If the predecessor has no right link yet, this is my first visit: I link it back to the current node temporarily and move left. For preorder, I record the node at this point. If the link is already there, the left subtree is finished: I remove the link and move right. For inorder, I record the node at this point. The temporary links let me return to each node after its left subtree is done, and removing them restores the original tree.
 
---
