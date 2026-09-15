
## 27. Remove Element
Given an integer array nums and an integer val, remove all occurrences of val in nums in-place. The order of the elements may be changed. 
Then return the number of elements in nums which are not equal to val.
Consider the number of elements in nums which are not equal to val be k, to get accepted, you need to do the following things:

Change the array nums such that the first k elements of nums contain the elements which are not equal to val. 
The remaining elements of nums are not important as well as the size of nums.
Return k.

Example 1:
Input: nums = [3,2,2,3], val = 3
Output: 2, nums = [2,2,_,_]
Explanation: Your function should return k = 2, with the first two elements of nums being 2.
It does not matter what you leave beyond the returned k (hence they are underscores).

```java
class Solution {
    public int removeElement(int[] nums, int val) {
        int idx=0;
        for(int i=0;i<nums.length; i++){
            if(nums[i]!= val){
                nums[idx]= nums[i];
                idx++;
            }
        }
        return idx;
    }
}
```

## 26. Remove Duplicates from Sorted Array

Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once. 
The relative order of the elements should be kept the same.
Consider the number of unique elements in nums to be k​​​​​​​​​​​​​​. After removing duplicates, return the number of unique elements k.
The first k elements of nums should contain the unique numbers in sorted order. The remaining elements beyond index k - 1 can be ignored.
Example 1:
Input: nums = [1,1,2]
Output: 2, nums = [1,2,_]
Explanation: Your function should return k = 2, with the first two elements of nums being 1 and 2 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).
```java
class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int idx = 1;
        for (int k = 1; k < nums.length; k++) {
            if (nums[k] != nums[idx - 1]) {
                nums[idx] = nums[k];
                idx++;
            }
        }
        return idx;
    }
}
```

## 80. Remove Duplicates from Sorted Array II
Given an integer array nums sorted in non-decreasing order, remove some duplicates in-place such that each unique element appears at most twice. 
The relative order of the elements should be kept the same.
Example 1:
Input: nums = [1,1,1,2,2,3]
Output: 5, nums = [1,1,2,2,3,_]
Explanation: Your function should return k = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).

```java
class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 2) return nums.length;
        int idx = 2;
        for (int k = 2; k < nums.length; k++) {
            if (nums[k] != nums[idx - 2]) {
                nums[idx] = nums[k];
                idx++;
            }
        }
        return idx;
    }
}
```

## 82. Remove Duplicates from Sorted List II

You are given the head of a sorted linked list.
Delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
Return the linked list sorted as well.
Input: head = [1,2,3,3,4,4,5]
Output: [1,2,5]

```java
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode dummy = new ListNode(-1); // Dummy node to handle head removals
        dummy.next = head;
        ListNode prev = dummy;
        ListNode cur = head;

        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                // Skip all nodes with the same value
                while (cur.next != null && cur.val == cur.next.val) {
                    cur = cur.next;
                }
                prev.next = cur.next; // Remove duplicates
            } else {
                prev = prev.next; // Move to next distinct node
            }
            cur = cur.next;
        }

        return dummy.next;
    }
}
```

## 905. Sort Array By Parity

Given an integer array nums, move all the even integers at the beginning of the array followed by all the odd integers.
Return any array that satisfies this condition.
Example 1:

Input: nums = [3,1,2,4]
Output: [2,4,3,1]
Explanation: The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.
```java
class Solution {
    public int[] sortArrayByParity(int[] nums) {
        int idx=0;
        for(int i=0;i<nums.length; i++){
            if(nums[i]%2 == 0){
                int temp = nums[idx];
                nums[idx]= nums[i];
                nums[i]=temp;
                idx++;
            }
        }
        return nums;
    }
}
```
