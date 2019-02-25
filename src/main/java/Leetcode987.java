import org.junit.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/
 * <p>
 * Given a binary tree, return the vertical order traversal of its nodes values.
 * <p>
 * For each node at position (X, Y), its left and right children respectively will be at positions (X-1, Y-1) and (X+1, Y-1).
 * <p>
 * Running a vertical line from X = -infinity to X = +infinity, whenever the vertical line touches some nodes, we report the values of the nodes in order from top to bottom (decreasing Y coordinates).
 * <p>
 * If two nodes have the same position, then the value of the node that is reported first is the value that is smaller.
 * <p>
 * Return an list of non-empty reports in order of X coordinate.  Every report will have a list of values of nodes.
 */
public class Leetcode987 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return "TreeNode{" + val + '}';
        }
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {

        class TreeNodePos {
            public final TreeNode node;
            // X (horizontal) position of the node.
            // For left child it will be xPos-1.
            // For right child it will be xPos+1.
            public final int xPos;
            // Y (vertical) position of the node.
            // For both children it will be yPos+1.
            public final int yPos;

            public TreeNodePos(TreeNode node, int xPos, int yPos) {
                this.node = node;
                this.xPos = xPos;
                this.yPos = yPos;
            }
        }

        class NodeValYPos {
            public final int val;
            public final int yPos;

            public NodeValYPos(int val, int yPos) {
                this.val = val;
                this.yPos = yPos;
            }
        }

        // Map sorted by X (horizontal) position
        SortedMap<Integer, List<NodeValYPos>> sortedNodes = new TreeMap<>();

        Queue<TreeNodePos> queue = new LinkedList<>();
        queue.add(new TreeNodePos(root, 0, 0));

        while (!queue.isEmpty()) {
            TreeNodePos p = queue.poll();

            int key = p.xPos;

            List<NodeValYPos> nodes = sortedNodes.get(key);
            if (nodes == null) {
                nodes = new ArrayList<>();
                sortedNodes.put(key, nodes);
            }
            nodes.add(new NodeValYPos(p.node.val, p.yPos));
            Collections.sort(nodes, (n1,n2) -> {
                if (n1.yPos == n2.yPos) {
                    return n1.val - n2.val;
                } else {
                    return n1.yPos - n2.yPos;
                }
            });

            if (p.node.left != null) {
                queue.add(new TreeNodePos(p.node.left, p.xPos-1, p.yPos+1));
            }
            if (p.node.right != null) {
                queue.add(new TreeNodePos(p.node.right, p.xPos+1, p.yPos+1));
            }
        }

        List<List<Integer>> result = new ArrayList<>(sortedNodes.size());
        for (List<NodeValYPos> l : sortedNodes.values()) {
            List<Integer> lst = l.stream()
                    .map(n -> n.val)
                    .collect(Collectors.toList());
            result.add(lst);

        }

        return result;
    }

    private static final int NOELEM = Integer.MIN_VALUE;

    static void test1() {
        int[] a = {3,9,20,NOELEM,NOELEM,15,7};
        TreeNode root = treeFromArray(a);
        List<List<Integer>> res = new Leetcode987().verticalTraversal(root);
        String resStr = res.toString();
        Assert.assertEquals("[[9], [3, 15], [20], [7]]", resStr);
        System.out.println("test1 ok");
    }

    static void test2() {
        int[] a = {1,2,3,4,5,6,7};
        TreeNode root = treeFromArray(a);
        List<List<Integer>> res = new Leetcode987().verticalTraversal(root);
        String resStr = res.toString();
        Assert.assertEquals("[[4], [2], [1, 5, 6], [3], [7]]", resStr);
        System.out.println("test2 ok");
    }

    static void test3() {
        int[] a = {0,5,1,9,NOELEM,2,NOELEM,  NOELEM,NOELEM, NOELEM,3,4,8,6,NOELEM,NOELEM,NOELEM,7};
        TreeNode root = treeFromArray(a);
        List<List<Integer>> res = new Leetcode987().verticalTraversal(root);
        String resStr = res.toString();
        Assert.assertEquals("[[9, 7], [5, 6], [0, 2, 4], [1, 3], [8]]", resStr);
        System.out.println("test3 ok");
    }

    static void test4() {
        int[] a = {0,2,1,3,NOELEM,NOELEM,NOELEM,4,5,NOELEM,7,6,NOELEM,10,8,11,9};
        TreeNode root = treeFromArray(a);
        List<List<Integer>> res = new Leetcode987().verticalTraversal(root);
        String resStr = res.toString();
        Assert.assertEquals("[[4, 10, 11], [3, 6, 7], [2, 5, 8, 9], [0], [1]]", resStr);
        System.out.println("test4 ok");
    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
    }

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
