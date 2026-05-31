A monotonic stack is a specialized stack data structure where elements are kept in a strictly increasing or strictly decreasing order. It is a highly optimized technique used to solve array problems involving "next greater" or "previous smaller" elements in linear time. [1, 2, 3, 4]  
The Core Mechanism 
To maintain the monotonic property, a strict rule is followed: when pushing a new element, you must pop elements from the stack until the order is restored. Then, you push the new element. [5]  
Because each element is pushed and popped at most once, this technique reduces time complexity from $O(n^2)$ (brute force) to $O(n)$. [4, 6]  
Types of Monotonic Stacks 
1. Monotonically Increasing Stack 

• Order: Elements from bottom to top are strictly increasing. 
• Top of stack: The smallest element. 
• Usage: Best for finding the previous smaller or next smaller element in a sequence. [3, 5, 6, 7]  

2. Monotonically Decreasing Stack 

• Order: Elements from bottom to top are strictly decreasing. 
• Top of stack: The largest element. 
• Usage: Best for finding the previous greater or next greater element. [3, 5, 6, 7]  

Common Problem Applications 
A monotonic stack can help solve many common algorithmic problems efficiently: 

• Next Greater/Smaller Element: Finding the first element to the left/right that is greater or smaller than the current element. 
• Largest Rectangle in Histogram: Finding the maximum rectangular area formed by a series of contiguous bars. 
• Daily Temperatures: Calculating how many days to wait for a warmer temperature. [2, 4, 5, 8]  

Quick Implementation Template 
This is a generic $O(n)$ pattern for processing an array to find "next" occurrences using a Monotonic Stack: [4, 9]  

AI responses may include mistakes.

[1] https://leetcode.com/discuss/study-guide/5148505/Monotonic-Stack-Guide-%2B-List-of-Problems/
[2] https://www.hellointerview.com/learn/code/stack/monotonic-stack
[3] https://dev.to/ashutosh049/monotonic-stack-4lkb
[4] https://purpletutor.com/monotonic-stack/
[5] https://www.geeksforgeeks.org/dsa/introduction-to-monotonic-stack-2/
[6] https://medium.com/@megha_bh/monotonic-stack-playbook-a-visual-guide-with-mind-map-e07f24f14e17
[7] https://www.youtube.com/watch?v=vTG32RrjJaQ
[8] https://dev.to/devcorner/mastering-the-monotonic-stack-pattern-in-java-with-real-world-examples-352l
[9] https://www.youtube.com/watch?v=e7XQLtOQM3I

