Here’s a **clean, interview-ready summary table** comparing **Closed Interval** vs **Half-Open Interval** 👇

***

# ✅ Binary Search Patterns Summary Table

| Feature                         | ✅ Closed Interval `[left, right]` | ✅ Half-Open Interval `[left, right)`       |
| ------------------------------- | --------------------------------- | ------------------------------------------ |
| **Range**                       | left and right **both included**  | left included, right **excluded**          |
| **Loop Condition**              | `left <= right`                   | `left < right`                             |
| **Mid Handling**                | mid is **removed after check**    | mid is **sometimes kept**                  |
| **Update (if condition true)**  | `right = mid - 1`                 | `right = mid`                              |
| **Update (if condition false)** | `left = mid + 1`                  | `left = mid + 1`                           |
| **Ans Handling**                | store answer separately           | no extra variable needed                   |
| **Return**                      | return stored `answer` OR `left`  | return `left`                              |
| **Goal**                        | find exact match OR track answer  | find **boundary (first true / min value)** |
| **Typical Use Case**            | search element in array           | binary search on **answer space**          |
| **Risk**                        | easy to skip correct answer       | safer, less error-prone                    |
| **Infinite Loop Risk**          | low (if ±1 used properly)         | low (natural shrinking)                    |
| **Common Problems**             | Binary Search (LeetCode 704)      | Koko, Capacity, Min Days                   |

***

# ✅ Update Rules Cheat Sheet

## 🔹 Closed Interval `[left <= right]`

*   We **remove mid**
*   So we must use `±1`

<!---->

    if (condition(mid)) {
        right = mid - 1;
    } else {
        left = mid + 1;
    }

***

## 🔹 Half-Open Interval `[left < right]`

*   We **keep mid when valid**
*   No `-1`

<!---->

    if (condition(mid)) {
        right = mid;
    } else {
        left = mid + 1;
    }

***

# ✅ Goal Mapping

| Problem Type             | Best Pattern                 |
| ------------------------ | ---------------------------- |
| Find exact target        | Closed `[left <= right]`     |
| Find minimum valid value | Half-open `[left < right]` ✅ |
| Find first occurrence    | Half-open ✅                  |
| Binary search on answer  | Half-open ✅                  |

***

# ✅ One-Line Memory Trick

👉 **Closed interval**  
→ "I checked mid, so remove it"

👉 **Half-open interval**  
→ "Mid might be answer, keep it"

***

# ✅ Quick Example Mapping

| Problem                        | Pattern     |
| ------------------------------ | ----------- |
| Search element in sorted array | Closed      |
| Koko Eating Bananas            | Half-open ✅ |
| Minimum capacity to ship       | Half-open ✅ |
| First bad version              | Half-open ✅ |

***

# 🚀 Final Takeaway

*   **90% interview problems → Half-open**
*   Use **Closed** only for classic array search
*   Don’t mix rules (biggest cause of bugs ❌)


