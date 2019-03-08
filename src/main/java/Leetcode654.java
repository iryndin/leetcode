/**
 * https://leetcode.com/problems/maximum-binary-tree/
 * <p>
 * Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:
 * <p>
 * - The root is the maximum number in the array.
 * - The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
 * - The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
 * <p>
 * Construct the maximum tree by the given array and output the root node of this tree.
 */
public class Leetcode654 {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return maxtree(nums, 0, nums.length);
    }

    static TreeNode maxtree(int[] a, int start, int end) {
        int max = Integer.MIN_VALUE;
        int maxIdx = -1;

        // 1. find max element
        for (int i = start; i < end; i++) {
            if (a[i] > max) {
                max = a[i];
                maxIdx = i;
            }
        }
        if (maxIdx == -1) {
            return null;
        }

        // 2. construct tree
        TreeNode n = new TreeNode(max);
        n.left = maxtree(a, start, maxIdx);
        n.right = maxtree(a, maxIdx + 1, end);
        return n;
    }
}
