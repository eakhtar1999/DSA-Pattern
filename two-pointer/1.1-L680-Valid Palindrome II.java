// Given a string s, return true if the s can be palindrome after deleting at most one character from it.
// Example 1:
// Input: s = "aba"
// Output: true
  
// Example 2:
// Input: s = "abca"
// Output: true
// Explanation: You could delete the character 'c'.
// TC - O(N)
//  SC - O(1)


class Solution {
    public boolean validPalindrome(String s) {
        int start=0, end= s.length()-1;
        while(start< end){
            if(s.charAt(start)!= s.charAt(end)){
                return palindrome(s,start+1,end) || palindrome(s,start,end-1);
            }
            start++;end--;
        }
        return true;
    }
    private boolean palindrome(String s, int start, int end){
        while(start < end){
            if(s.charAt(start)!= s.charAt(end)){
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
