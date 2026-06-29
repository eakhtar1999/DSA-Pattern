// Its a GREEDY Problem

// You are given a 0-indexed integer array stones sorted in strictly increasing order representing the positions of stones in a river.
// A frog, initially on the first stone, wants to travel to the last stone and then return to the first stone. 
// However, it can jump to any stone at most once.
// The length of a jump is the absolute difference between the position of the stone the frog is currently on 
//   and the position of the stone to which the frog jumps.
// More formally, if the frog is at stones[i] and is jumping to stones[j], the length of the jump is |stones[i] - stones[j]|.
// The cost of a path is the maximum length of a jump among all jumps in the path.
// Return the minimum cost of a path for the frog.
// Example 1:
// Input: stones = [0,2,5,6,7]
// Output: 5
// Explanation: The above figure represents one of the optimal paths the frog can take.
// The cost of this path is 5, which is the maximum length of a jump.
// Since it is not possible to achieve a cost of less than 5, we return it.


class Solution {
    public int maxJump(int[] stones) {
        int n = stones.length;

        // minimum cost we are trying to compute
        int maxJump = 0;

        // check gap between i and i-2 (skip one stone)
        for (int i = 2; i < n; i++) {
            maxJump = Math.max(maxJump, stones[i] - stones[i - 2]);
        }

        return maxJump;
    }
}
