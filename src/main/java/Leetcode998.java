import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/maximum-binary-tree-ii/
 *
 * We are given the root node of a maximum tree: a tree where every node has a value greater than any other value in its subtree.
 *
 * Just as in the previous problem, the given tree was constructed from an list A (root = Construct(A))
 * recursively with the following Construct(A) routine:
 *
 *  - If A is empty, return null.
 *  - Otherwise, let A[i] be the largest element of A.  Create a root node with value A[i].
 *  - The left child of root will be Construct([A[0], A[1], ..., A[i-1]])
 *  - The right child of root will be Construct([A[i+1], A[i+2], ..., A[A.length - 1]])
 *  - Return root.
 *
 * Note that we were not given A directly, only a root node root = Construct(A).
 *
 * Suppose B is a copy of A with the value val appended to it.  It is guaranteed that B has unique values.
 *
 * Return Construct(B).
 */
public class Leetcode998 {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * Approach:
     * since {@code val} is appended to the array {@code A}, it should appear in the right child,
     * if only it is not max element.
     *
     * So we do following:
     *  - for given {@code TreeNode node} if {@code val} is greater than {@code node.val}
     *    then create new TreeNode with {@code node.val = val}, and make this {@code node} left chile of it
     *  - for given {@code TreeNode node} if {@code val} is less than {@code node.val}
     *
     *
     * @param root
     * @param val
     * @return
     */
    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        insertIntoMaxTreeRecursive(root, val);
        return root;
    }

    private void insertIntoMaxTreeRecursive(TreeNode root, int val) {
        if (root != null) {
            if (val > root.val) {
                TreeNode n = new TreeNode(root.val);
                n.left = root.left;
                n.right = root.right;

                root.val = val;
                root.right = null;
                root.left = n;
            } else {
                if (root.right == null) {
                    TreeNode n = new TreeNode(val);
                    root.right = n;
                } else {
                    insertIntoMaxTreeRecursive(root.right, val);
                }
            }
        }
    }

    static final int NOELEM = Integer.MIN_VALUE;

    static void test1() {
        int[] a = {4,1,3,NOELEM,NOELEM,2};
        int val = 5;
        TreeNode root = unpackBinaryTree(a);
        TreeNode result = new Leetcode998().insertIntoMaxTree(root, val);
        int[] res = packBinaryTree(result);
        int[] EXPECTED = {5,4,NOELEM,1,3,NOELEM,NOELEM,2};
        Assert.assertTrue(Arrays.equals(EXPECTED, res));
        System.out.println("test1 ok");
    }

    static void test2() {
        int[] a = {5,2,4,NOELEM,1};
        int val = 3;
        TreeNode root = unpackBinaryTree(a);
        TreeNode result = new Leetcode998().insertIntoMaxTree(root, val);
        int[] res = packBinaryTree(result);
        int[] EXPECTED = {5,2,4,NOELEM,1,NOELEM,3};
        Assert.assertTrue(Arrays.equals(EXPECTED, res));
        System.out.println("test2 ok");

    }

    public static void main(String[] args) {
        testPackUnpack();
        test1();
        test2();
    }

    static void testPackUnpack() {
        int[] a = {5,2,4,NOELEM,1};
        TreeNode n = unpackBinaryTree(a);
        int[] b = packBinaryTree(n);
        Assert.assertTrue(Arrays.equals(a,b));
        System.out.println("pack/unpack is OK");
    }

    static int[] packBinaryTree(TreeNode root) {
        List<Integer> list = new ArrayList<>(128);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode n = queue.poll();
            if (n == null) {
                list.add(NOELEM);
            } else {
                list.add(n.val);
                queue.offer(n.left);
                queue.offer(n.right);
            }
        }

        // remove last NOELEM elements
        while (!list.isEmpty() && list.get(list.size()-1) == NOELEM) {
            list.remove(list.size()-1);
        }

        return list.stream().mapToInt(i -> i).toArray();
    }

    static TreeNode unpackBinaryTree(int[] a) {
        TreeNode[] nodes = new TreeNode[a.length];

        for (int i=0; i<a.length; i++) {
            nodes[i] = a[i] != NOELEM ? new TreeNode(a[i]) : null;
        }

        int currentIdx = 0;
        for (int i=0; i<a.length; i++) {
            TreeNode node = nodes[i];
            if (node != null) {
                int leftIdx = currentIdx * 2 + 1;
                int rightIdx = leftIdx + 1;
                if (leftIdx < a.length) {
                    node.left = nodes[leftIdx];
                }
                if (rightIdx < a.length) {
                    node.right = nodes[rightIdx];
                }
                currentIdx++;
            }
        }

        return nodes[0];
    }
}
