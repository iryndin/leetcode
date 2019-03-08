import org.junit.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * https://leetcode.com/problems/distribute-coins-in-binary-tree/
 *
 * Given the root of a binary tree with N nodes, each node in the tree has node.val coins, and there are N coins total.
 *
 * In one move, we may choose two adjacent nodes and move one coin from one node to another.
 * (The move may be from parent to child, or from child to parent.)
 *
 * Return the number of moves required to make every node have exactly one coin.
 *
 */
public class Leetcode979 {

    static class TreeNode {
        static int CNT = 10;
        final int id;
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; id=CNT+1; CNT++;}

        @Override
        public String toString() {
            return "TreeNode(id=" + id + ", val="+val+")";
        }
    }

    // it returns number of excessive coins in the subtree of the given node
    int dfs(TreeNode n) {
        if (n == null) {
            return 0;
        }
        int l = dfs(n.left);
        int r = dfs(n.right);
        int res = n.val + l + r - 1;
        System.out.println(String.format("n.id=%d, dfs=%d", n.id, res));
        return res;
    }

    /**
     * Approach:
     * build graph, by which we can find cost of path from each node to each node.
     * for each empty node find closest non-empty nodes for which val >=2 (val>1).
     * then calc min number of moves.
     *
     * I need following:
     * getPath(node1, node2) - get cost of path from node1 to node2.
     * getListOfAllNodes - get list of all tree nodes.
     * getClosestNotEmptyNodes(node1).
     *
     * Vertex(int id, int val, Vertex left, Vertex right, Vertex parent)
     *
     * @param root
     * @return
     */
    public int distributeCoins(TreeNode root) {
        // key is child
        // value is parent node
        IdentityHashMap<TreeNode, TreeNode> childToParentMap = new IdentityHashMap<>();
        List<TreeNode> emptyNodes = new ArrayList<>();

        // parse tree and:
        //  fill childToParentMap to get parent by given node
        //  fill emptyNodes array
        {
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            while (!queue.isEmpty()) {
                TreeNode n = queue.remove();
                if (n.val == 0) {
                    emptyNodes.add(n);
                }
                if (n.left != null) {
                    childToParentMap.put(n.left, n);
                    queue.offer(n.left);
                }
                if (n.right != null) {
                    childToParentMap.put(n.right, n);
                    queue.offer(n.right);
                }
            }
        }

        int totalMoves = 0;

        class TreeNodeAndPathCost {
            final TreeNode node;
            final int pathCost;

            public TreeNodeAndPathCost(TreeNode node, int pathCost) {
                this.node = node;
                this.pathCost = pathCost;
            }
        }

        while (!emptyNodes.isEmpty()) {
            TreeNode emptyNode = emptyNodes.remove(0);
            // now find closest non-empty node to emptyNode, BFS
            Queue<TreeNodeAndPathCost> queue = new LinkedList<>();
            queue.offer(new TreeNodeAndPathCost(emptyNode, 0));

            Set<TreeNode> visited = Collections.newSetFromMap( new IdentityHashMap<>() );

            int minimalCost = Integer.MAX_VALUE;
            TreeNode nodeToDecreaseVal = null;

            // now perform BFS
            while (!queue.isEmpty()) {
                TreeNodeAndPathCost tnpc = queue.remove();
                if (!visited.contains(tnpc.node)) {
                    if (tnpc.node.val > 1) {
                        if (tnpc.pathCost < minimalCost) {
                            minimalCost = tnpc.pathCost;
                            nodeToDecreaseVal = tnpc.node;
                        }
                    }
                    // put adjacent nodes
                    {
                        int newPathCost = tnpc.pathCost + 1;
                        TreeNode n = tnpc.node;
                        if (n.left != null) {
                            queue.offer(new TreeNodeAndPathCost(n.left, newPathCost));
                        }
                        if (n.right != null) {
                            queue.offer(new TreeNodeAndPathCost(n.right, newPathCost));
                        }
                        TreeNode parent = childToParentMap.get(n);
                        if (parent != null) {
                            queue.offer(new TreeNodeAndPathCost(parent, newPathCost));
                        }
                    }
                    visited.add(tnpc.node);
                }
            }
            totalMoves += minimalCost;
            emptyNode.val = 1;
            nodeToDecreaseVal.val--;
        }

        return totalMoves;
    }

    static void test0() {
        int[] a = {1,1,1};
        TreeNode tree = treeFromArray(a);
        int res = new Leetcode979().distributeCoins(tree);
        Assert.assertEquals(0, res);
        System.out.println("test0 ok");
    }

    static void test1() {
        int[] a = {3,0,0};
        TreeNode tree = treeFromArray(a);
        int res = new Leetcode979().distributeCoins(tree);
        Assert.assertEquals(2, res);
        System.out.println("test1 ok");
    }

    static void test2() {
        int[] a = {0,3,0};
        TreeNode tree = treeFromArray(a);
        int res = new Leetcode979().distributeCoins(tree);
        Assert.assertEquals(3, res);
        System.out.println("test2 ok");
    }

    static void test3() {
        int[] a = {1,0,2};
        TreeNode tree = treeFromArray(a);
        int res = new Leetcode979().distributeCoins(tree);
        Assert.assertEquals(2, res);
        System.out.println("test3 ok");
    }

    static void test4() {
        int[] a = {1,0,0,NOELEM,3};
        TreeNode tree = treeFromArray(a);
        int res = new Leetcode979().distributeCoins(tree);
        Assert.assertEquals(4, res);
        System.out.println("test4 ok");
    }

    static void test5() {
        int[] a = {3,NOELEM,0,0,NOELEM,0,NOELEM,2};
        TreeNode tree = treeFromArray(a);
        /*
        int res = new Leetcode979().distributeCoins(tree);
        Assert.assertEquals(4, res);
        System.out.println("test5 ok");
        */
        new Leetcode979().dfs(tree);
    }

    public static void main(String[] args) {
        /*test0();
        test1();
        test2();
        test3();
        test4();*/
        test5();
    }

    static final int NOELEM = -1;

    static TreeNode treeFromArray(int[] a) {
        TreeNode[] nodes = new TreeNode[a.length];
        for (int i=0; i<a.length; i++) {
            nodes[i] = a[i] != NOELEM ? new TreeNode(a[i]) : null;
        }

        int idx=0;
        for (int i=0; i<a.length; i++) {
            int leftIdx = idx*2+1;
            int rightIdx = leftIdx+1;
            if (nodes[i] != null) {
                if (leftIdx < a.length) {
                    nodes[i].left = nodes[leftIdx];
                }
                if (rightIdx < a.length) {
                    nodes[i].right = nodes[rightIdx];
                }
                idx++;
            }
        }
        return nodes[0];
    }
}
