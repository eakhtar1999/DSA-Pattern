// Reverse the characters of a string, excluding vowels.
public class Solution {
    public static String reverseExceptVowels(String s) {
        String vowels = "aeiouAEIOU";
        StringBuilder nonVowels = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (vowels.indexOf(c) == -1) {
                nonVowels.append(c);
            }
        }
        nonVowels.reverse();
        StringBuilder result = new StringBuilder();
        int idx = 0;
        for (char c : s.toCharArray()) {
            if (vowels.indexOf(c) != -1) {
                result.append(c);
            } else {
                result.append(nonVowels.charAt(idx++));
            }
        }
        return result.toString();
    }
}

//  check cycle in linkedlist
class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; this.next = null; }
}

public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }
}
