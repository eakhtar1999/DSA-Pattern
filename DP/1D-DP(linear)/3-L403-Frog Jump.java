// 👉 "At each stone, store how I got there (jump size), and try k-1, k, k+1."
// 📦 Similar problems:
// 🟢 Frog Jump (this problem)
// 🟢 Word Break (state + transitions)
// 🟢 Jump Game variants
// 🟢 BFS with state (position + cost/action)


// A frog is crossing a river. The river is divided into some number of units, and at each unit, 
// there may or may not exist a stone. The frog can jump on a stone, but it must not jump into the water.
// Given a list of stones positions (in units) in sorted ascending order, determine if the frog can cross the river 
// by landing on the last stone. Initially, the frog is on the first stone and assumes the first jump must be 1 unit.
// If the frog's last jump was k units, its next jump must be either k - 1, k, or k + 1 units. 
// The frog can only jump in the forward direction.
// Example 1:
// Input: stones = [0,1,3,5,6,8,12,17]
// Output: true
// Explanation: The frog can jump to the last stone by jumping 1 unit to the 2nd stone, then 2 units to the 3rd stone, then 2 units to the 4th stone, then 3 units to the 6th stone, 4 units to the 7th stone, and 5 units to the 8th stone.


import java.util.*;

class Solution {
    public boolean canCross(int[] stones) {

        // first jump must be 1
        if (stones[1] != 1) return false;

        int lastStone = stones[stones.length - 1];

        // case: [0,1]
        if (lastStone == 1) return true;

        // store stones for O(1) lookup
        Set<Integer> stoneSet = new HashSet<>();
        for (int s : stones) stoneSet.add(s);

        // dp[stone] -> jumps that can reach here
        Map<Integer, Set<Integer>> dp = new HashMap<>();
        for (int stone : stones) dp.put(stone, new HashSet<>());

        // start: stone 1 reached with jump 1
        dp.get(1).add(1);

        for (int i = 1; i < stones.length; i++) {
            int stone = stones[i];

            // try all jumps reaching this stone
            for (int k : dp.get(stone)) {

                // next jumps: k-1, k, k+1
                for (int nextK : new int[]{k - 1, k, k + 1}) {

                    // valid jump + valid stone
                    if (nextK > 0 && stoneSet.contains(stone + nextK)) {

                        // reached last stone
                        if (stone + nextK == lastStone) return true;

                        // store reachable jump
                        dp.get(stone + nextK).add(nextK);
                    }
                }
            }
        }

        // cannot reach last stone
        return false;
    }
}
