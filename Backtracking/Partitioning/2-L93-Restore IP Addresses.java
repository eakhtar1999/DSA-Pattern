// A valid IP address consists of exactly four integers separated by single dots. 
// Each integer is between 0 and 255 (inclusive) and cannot have leading zeros.
// For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses, but "0.011.255.245", "192.168.1.312" 
// and "192.168@1.1" are invalid IP addresses.
// Given a string s containing only digits, return all possible valid IP addresses that can be formed 
// by inserting dots into s. You are not allowed to reorder or remove any digits in s. 
// You may return the valid IP addresses in any order.
// Example 1:
// Input: s = "25525511135"
// Output: ["255.255.11.135","255.255.111.35"]
  
// | Concept | Palindrome Partition | Restore IP |
// |--------|--------------------|-----------|
// | Split string | ✅ | ✅ |
// | Loop DFS | ✅ | ✅ |
// | Validation | isPalindrome | valid IP segment |
// | Backtracking | add/remove | add/remove |


class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        backtrack(s, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(String s, int start, List<String> path, List<String> result) {

        // ✅ If 4 segments formed
        if (path.size() == 4) {
            // ✅ Valid only if entire string is used
            if (start == s.length()) {
                result.add(String.join(".", path));
            }
            return;
        }

        // ✅ Loop DFS → try segment lengths 1,2,3
        // ✅ Why Loop DFS here? Because: 👉 At each index → multiple choices (len = 1,2,3), 👉 We need to prepare all and validate and then choose it 
        // Not just include/exclude character like simple backtracking
        for (int len = 1; len <= 3; len++) {
            
            // logic to prepare segment
            if (start + len > s.length()) break;
            String segment = s.substring(start, start + len);

            // ✅ Pruning: segment validation
            // - no leading zeros (except "0")
            // - value <= 255
            if ((segment.length() > 1 && segment.charAt(0) == '0') 
                || Integer.parseInt(segment) > 255) continue;

            path.add(segment);                         // choose segment
            backtrack(s, start + len, path, result);   // explore
            path.remove(path.size() - 1);              // backtrack
        }
    }
}
