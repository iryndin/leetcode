import java.util.ArrayList;
import java.util.List;

public class Leetcode100 {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * Approach: use some tree traversal, traverse each tree and then compare resulted sequences
     * Let's use e.g. in-order traversal.
     *
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // p and q are both null
        if (p == null && q == null) return true;
        // one of p and q is null
        if (q == null || p == null) return false;
        if (p.val != q.val) return false;
        return isSameTree(p.right, q.right) &&
                isSameTree(p.left, q.left);
    }

    /**
     * In-order traversal: left, root, right
     *
     * @param t
     * @param list
     */
    static void inorder(TreeNode t, List<Integer> list) {
        if (t != null) {
            inorder(t.left, list);
            list.add(t.val);
            inorder(t.right, list);
        }
    }
}
