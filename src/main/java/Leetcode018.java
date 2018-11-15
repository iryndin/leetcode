import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/4sum/
 *
 * Given an array nums of n integers and an integer target,
 * are there elements a, b, c, and d in nums such that a + b + c + d = target?
 *
 * Find all unique quadruplets in the array which gives the sum of target.
 *
 * Note:
 *
 * The solution set must not contain duplicate quadruplets.
 *
 * Example:
 *
 * Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.
 *
 * A solution set is:
 * [
 *   [-1,  0, 0, 1],
 *   [-2, -1, 1, 2],
 *   [-2,  0, 0, 2]
 * ]
 */
public class Leetcode018 {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        int[] aa = Arrays.copyOf(nums, nums.length);
        Arrays.sort(aa);
        Set<List<Integer>> results = new HashSet<>();
        for (int i=0; i<aa.length-3; i++) {
            find3Sum(target-aa[i], i, aa, results);
        }
        return new ArrayList<>(results);
    }

    private void find3Sum(int target, int i1, int[] nums, Set<List<Integer>> results) {
        for (int i2=i1+1; i2<nums.length; i2++) {
            find2Sum(target-nums[i2], i1, i2, nums, results);
        }
    }

    private void find2Sum(int target, int i1, int i2, int[] nums, Set<List<Integer>> results) {
        for (int i3 = i2+1; i3<nums.length; i3++) {
            int target2 = target-nums[i3];
            for (int i4=i3+1; i4<nums.length; i4++) {
                if (nums[i4] == target2) {
                    results.add(Arrays.asList(nums[i1], nums[i2], nums[i3], nums[i4]));
                } else if (nums[i4] > target2) {
                    break;
                }
            }
        }
    }
}
