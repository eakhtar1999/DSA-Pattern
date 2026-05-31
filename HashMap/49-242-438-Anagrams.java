
//  242. Valid Anagram 
// 💡 Pattern: Frequency Counting
// 💡 Count ++ for s, -- for t
// 💡 Length must match
// 💡 Negative count => not anagram
class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            count[c - 'a']--;
            if (count[c - 'a'] < 0) return false;
        }
        return true;
    }
}

// 49. Group Anagrams - Hash Table
// 💡 Pattern: HashMap + Character Frequency Signature
// 💡 All anagrams produce identical frequency counts.
// 💡 Build count[26] for every word. Convert count array into a unique key.
// 💡 Store words having same key in same list.
// 💡 Frequency-array key avoids sorting each string.
// 💡 O(n * k) time, where k = average word length.
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> res = new HashMap();
        for(String s : strs){
            int[] count = new int[26];
            for(char c : s.toCharArray()){
                count[c-'a']++;
            }
            String key = Arrays.toString(count);
            res.putIfAbsent(key, new ArrayList<>());
            res.get(key).add(s);
        }

        return new ArrayList<>(res.values());
        
    }
}


// 438. Find All Anagrams in a String   -  Sliding Window
// 💡 Pattern: Sliding Window + Frequency Matching
// 💡 Maintain fixed-size window equal to p.length().
// 💡 Track char frequencies for pattern and current window.
// 💡 matches = number of indices where counts are equal.
// 💡 If matches == 26 => current window is an anagram.
// 💡 Slide window:
// 💡   1. Add right character.
// 💡   2. Remove left character.
// 💡   3. Update matches efficiently.
// 💡 Avoid comparing two arrays every time.
// 💡 O(n) time, O(1) space.
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s.length() < p.length()) return result;
        int[] pCount = new int[26];
        int[] sCount = new int[26];
        for (int i = 0; i < p.length(); i++) {
            pCount[p.charAt(i) - 'a']++;
            sCount[s.charAt(i) - 'a']++;
        }
        int matches = 0;
        for (int i = 0; i < 26; i++) {
            if (pCount[i] == sCount[i]) matches++;
        }
        for (int i = 0; i < s.length() - p.length(); i++) {
            if (matches == 26) result.add(i);
            int left = s.charAt(i) - 'a';
            int right = s.charAt(i + p.length()) - 'a';
            sCount[right]++;
            if (sCount[right] == pCount[right]) matches++;
            else if (sCount[right] == pCount[right] + 1) matches--;
            sCount[left]--;
            if (sCount[left] == pCount[left]) matches++;
            else if (sCount[left] == pCount[left] - 1) matches--;
        }
        if (matches == 26) result.add(s.length() - p.length());
        return result;
    }
}
