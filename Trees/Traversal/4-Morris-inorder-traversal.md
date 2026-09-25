Interview Explanation
> I traverse the tree with a current pointer. If the current node has no left child, I add it to the result and move right. Otherwise, I find the rightmost node in its left subtree. If that node has no thread yet, I link it temporarily to the current node and move left. When I encounter the thread again, the left subtree is complete, so I remove the thread, add the current node, and move right. This gives inorder traversal without recursion or an auxiliary stack.
> **One-line rule:** *"No left: print and go right. Otherwise find the rightmost node of the left subtree. If it's empty, thread it and go left. If it already points to me, cut it, print, and go right."*

## Recognition: when Morris is the answer
- The question asks for an inorder or preorder traversal with **O(1) extra space**, or says "no recursion, no stack."
- Follow-ups like "Kth smallest in BST / Recover BST / validate BST **in O(1) space**."
- **Anti-signal:** if O(h) space is fine, use a recursive traversal or an iterative traversal with a `Deque`. They're simpler and less bug-prone in an interview. Morris is the answer to a follow-up question, not your default.

## Memory model: the lines to code from
- **State:** `cur` is the node being processed, and `pred` is the rightmost node of `cur.left`, which is cur's inorder predecessor.
- **Three cases:**
  - **No left child:** visit `cur`, then go right.
  - **Left child exists and `pred.right == null` (first visit):** thread `pred.right = cur`, then go left.
  - **Left child exists and `pred.right == cur` (second visit):** unthread `pred.right = null`, visit `cur`, then go right.
- **Invariant:** "When I reach `cur` through a thread, its whole left subtree has already been output."
- **Preorder variant:** the only change is to visit `cur` when you *create* the thread instead of when you remove it.

## Variables and their jobs
| Variable | Job |
|---|---|
| `cur` | The node being processed. It only moves at the end of a case: left after threading, right after visiting. |
| `pred` | A scout that walks to the rightmost node of `cur.left`. It never replaces `cur`. |
| `pred.right` | Doubles as a "have I been here" flag: `null` means first visit, `cur` means second visit. |


## Iteration rules (the NPE and infinite-loop traps)
1. `pred` starts at `cur.left`, never at `cur`. This is safe because you're in the `else` branch, so `cur.left != null`.
2. The predecessor loop needs **both** stop conditions, `pred.right != null && pred.right != cur`. Without the second one you get an infinite loop.
3. Every path through the loop body must move `cur` exactly once. If a path doesn't move it, you get an infinite loop.
4. Every thread you create must be removed. Otherwise the caller's tree comes back corrupted, and a later traversal of it cycles.
5. Only one scouting pointer walks down the tree (`pred`). If you catch yourself reassigning `cur` inside a search loop, that's the bug from #1 again.

## Fixed code
```java
class Solution {
    public List<Integer> getInorder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left == null) {                 // case 1: no left subtree
                res.add(cur.data);
                cur = cur.right;                    // may follow a thread up; that's intended
            } else {
                TreeNode pred = cur.left;           // scout, NOT cur
                while (pred.right != null && pred.right != cur) {
                    pred = pred.right;              // stop at null OR at existing thread
                }
                if (pred.right == null) {           // case 2: first visit
                    pred.right = cur;               // create thread
                    cur = cur.left;
                } else {                            // case 3: second visit, left done
                    pred.right = null;              // remove thread (restore tree)
                    res.add(cur.data);
                    cur = cur.right;
                }
            }
        }
        return res;
    }
}
```
**Complexity:** O(n) time, because each edge is walked at most about 3 times. O(1) extra space.

Noted on Java + Spring Boot. Your memory setting is off, so I can't save that across chats myself. It's already the stated language in your pattern docs, though, and you can turn memory on in Settings if you want it remembered everywhere. Want me to write this up as the start of a Trees pattern doc in the same format as your Two Pointers and DP docs?
