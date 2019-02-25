import org.junit.Assert;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/cousins-in-binary-tree/
 * <p>
 * In a binary tree, the root node is at depth 0, and children of each depth k node are at depth k+1.
 * <p>
 * Two nodes of a binary tree are cousins if they have the same depth, but have different parents.
 * <p>
 * We are given the root of a binary tree with unique values, and the values x and y of two different nodes in the tree.
 * <p>
 * Return true if and only if the nodes corresponding to the values x and y are cousins.
 */
public class Leetcode993 {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        public String toString() {
            return String.format("TreeNode(%d)", val);
        }
    }

    public boolean isCousins(TreeNode root, int x, int y) {

        class NodeWithParent {
            public final TreeNode node;
            public final int depth;
            public final TreeNode parent;

            public NodeWithParent(TreeNode node, int depth, TreeNode parent) {
                this.node = node;
                this.depth = depth;
                this.parent = parent;
            }
        }

        Queue<NodeWithParent> queue = new LinkedList<>();
        queue.add(new NodeWithParent(root, 0, null));

        NodeWithParent xnode = null;
        NodeWithParent ynode = null;

        while (!queue.isEmpty() && (xnode == null || ynode == null)) {
            NodeWithParent n = queue.poll();

            if (n.node.val == x) {
                xnode = n;
            } else if (n.node.val == y) {
                ynode = n;
            }

            if (n.node.left != null) {
                queue.add(new NodeWithParent(n.node.left, n.depth+1, n.node));
            }
            if (n.node.right != null) {
                queue.add(new NodeWithParent(n.node.right, n.depth+1, n.node));
            }
        }

        if (xnode == null || ynode == null) {
            return false;
        }

        return xnode.parent != ynode.parent && xnode.depth == ynode.depth;
    }

    static void test1() {
        int[] a = {1,2,3,4};
        int x=4;
        int y=3;
        TreeNode root = treeFromArray(a);
        boolean res = new Leetcode993().isCousins(root, x, y);
        Assert.assertEquals(false, res);
        System.out.println("test1 ok");
    }

    static void test2() {
        int[] a = {1,2,3,NOELEM,4,NOELEM,5};
        int x=5;
        int y=4;
        TreeNode root = treeFromArray(a);
        boolean res = new Leetcode993().isCousins(root, x, y);
        Assert.assertEquals(true, res);
        System.out.println("test2 ok");
    }

    static void test3() {
        int[] a = {1,2,3,NOELEM,4};
        int x=2;
        int y=3;
        TreeNode root = treeFromArray(a);
        boolean res = new Leetcode993().isCousins(root, x, y);
        Assert.assertEquals(false, res);
        System.out.println("test3 ok");
    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }

    private static final int NOELEM = Integer.MIN_VALUE;

    private static TreeNode treeFromArray(int[] a) {
        TreeNode[] nodes = new TreeNode[a.length];

        for (int i=0; i<a.length; i++) {
            if (a[i] != NOELEM) {
                TreeNode node = new TreeNode(a[i]);
                nodes[i] = node;
            } else {
                nodes[i] = null;
            }
        }

        int currentIdx=0;
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
