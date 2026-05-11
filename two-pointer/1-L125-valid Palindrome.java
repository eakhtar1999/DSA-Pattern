// A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.
// Given a string s, return true if it is a palindrome, or false otherwise.
// Example 1:

// Input: s = "A man, a plan, a canal: Panama"
// Output: true
// Explanation: "amanaplanacanalpanama" is a palindrome.


// TC - O(N)
//  SC - O(1)
class Solution {
    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            char lChar = s.charAt(left);
            char rChar = s.charAt(right);

            // 1. Skip non-alphanumeric from the left
            if (!Character.isLetterOrDigit(lChar)) {
                left++;
            } 
            // 2. Skip non-alphanumeric from the right
            else if (!Character.isLetterOrDigit(rChar)) {
                right--;
            } 
            // 3. Compare characters after converting to lowercase
            else {
                if (Character.toLowerCase(lChar) != Character.toLowerCase(rChar)) {
                    return false;
                }
                left++;
                right--;
            }
        }
        return true;
    }
}


//  to remember

        // Convert uppercase to lowercase - 
        //In Java, adding an int (32) to a char results in an int. You must explicitly cast it back: arr[i] = (char)(arr[i] + 32);.
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= 'A' && arr[i] <= 'Z') {
                arr[i] = (char)(arr[i] + 32);
            }
        }
// ASCII
    public boolean isAlphaNum(char c) {
        return (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9');
    }



// Using StringBuilder
// TC - O(N)
//  SC - O(N)
class Solution {
    public boolean isPalindrome(String s) {
         StringBuilder sb=new StringBuilder();
        for(char ch:s.toCharArray()){
            if(Character.isLetterOrDigit(ch))
                sb.append(Character.toLowerCase(ch));
        }
        if(sb.toString().equals(sb.reverse().toString()))
            return true;
        return false;
    }
}

// least effecient 
// TC - O(N)
//  SC - O(N)  > toLowerCase() and replaceAll() both create new string objects in memory.
class Solution {
    public boolean isPalindrome(String s) {
        s = s.toLowerCase().replaceAll("[^a-z0-9]", "");
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;        
    }
}


