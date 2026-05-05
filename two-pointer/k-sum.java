public class Solution {
    private List<List<Integer>> res;
    private List<Integer> quad;

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        res = new ArrayList<>();
        quad = new ArrayList<>();
        kSum(nums, 4, 0, target);
        return res;
    }

    private void kSum(int[] nums, int k, int start, long target) {

        // for 2 sum, use two pointers to find pairs that sum up to target
        if (k == 2) {
            int l = start, r = nums.length - 1;
            while (l < r) {
                long sum = nums[l] + nums[r];
                if (sum < target) {
                    l++;
                } else if (sum > target) {
                    r--;
                } else {
                    res.add(new ArrayList<>(quad));
                    res.get(res.size() - 1).add(nums[l]);
                    res.get(res.size() - 1).add(nums[r]);
                    l++;
                    r--;
                    while (l < r && nums[l] == nums[l - 1]) l++;// skip duplicates from the left
                    while (l < r && nums[r] == nums[r + 1]) r--;// skip duplicates from the right
                }
            }
            return;
        }

        for (int i = start; i < nums.length - k + 1; i++) {
            if (i > start && nums[i] == nums[i - 1]) continue;
            quad.add(nums[i]);// add current number to the current combination
            kSum(nums, k - 1, i + 1, target - nums[i]);
            quad.remove(quad.size() - 1);// backtrack: remove the last number added to try the next one
        }
    }
}
