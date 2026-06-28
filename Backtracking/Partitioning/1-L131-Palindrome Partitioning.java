// Given a string s, partition s such that every substring of the partition is a palindrome. 
// Return all possible palindrome partitioning of s.
// Example 1:
// Input: s = "aab"
// Output: [["a","a","b"],["aa","b"]]

  
class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        List<String> part = new ArrayList<>();
        dfs(0, s, part, res);
        return res;
    }
    
    private void dfs(int i, String s, List<String> part, List<List<String>> res) {
        
        // ✅ Base case: reached end → valid partition formed
        if (i >= s.length()) {
            res.add(new ArrayList<>(part));
            return;
        }
        
        // ✅ Loop DFS: try all substrings starting from index i
        for (int j = i; j < s.length(); j++) {
            
            // ✅ Choose only if substring is palindrome (pruning)
            if (isPali(s, i, j)) {
                // 👉 3 steps Happens inside loop, because: Each substring is a separate choice and inside if: for pruning
                part.add(s.substring(i, j + 1));   // choose
                dfs(j + 1, s, part, res);          // explore
                part.remove(part.size() - 1);      // backtrack
            }
        }
    }
    
    private boolean isPali(String s, int l, int r) {
        // ✅ Check palindrome
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) return false;
            l++;
            r--;
        }
        return true;
    }
}
