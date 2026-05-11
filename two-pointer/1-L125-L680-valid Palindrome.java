



class Solution {
    public boolean isPalindrome(String s) {
        char[] arr = s.toCharArray();

        // Convert uppercase to lowercase
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= 'A' && arr[i] <= 'Z') {
                arr[i] = (char)(arr[i] + 32);
            }
        }

        int start = 0;
        int end = arr.length - 1;

        while (start < end) {
            // Skip non-alphanumeric from start
            while (start < end && !isAlphaNum(arr[start])) {
                start++;
            }

            // Skip non-alphanumeric from end
            while (start < end && !isAlphaNum(arr[end])) {
                end--;
            }

            // Compare characters
            if (arr[start] != arr[end]) {
                return false;
            }

            start++;
            end--;
        }

        return true;
    }

    public boolean isAlphaNum(char c) {
        return (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9');
    }
}

// TC - O(N)
//  SC - O(1)
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


