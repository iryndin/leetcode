import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * https://leetcode.com/problems/longest-common-prefix/
 *
 * Write a function to find the longest common prefix string amongst an array of strings.
 *
 * If there is no common prefix, return an empty string "".
 *
 */
public class Leetcode014 {

    public String longestCommonPrefix(String[] strs) {
        final class Node {
            public final char c;
            public final Map<Character, Node> children = new HashMap<>();

            public Node(char c) {
                this.c = c;
            }
        }

        Node root = new Node((char)0);

        int minLength = Integer.MAX_VALUE;
        // construct prefix tree
        {
            for (String s : strs) {
                minLength = Math.min(minLength, s.length());
                Node n = root;
                for (char c : s.toCharArray()) {
                    Node n2 = n.children.get(c);
                    if (n2 == null) {
                        n2 = new Node(c);
                        n.children.put(c, n2);
                    }
                    n = n2;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        Node n = root;
        while (n.children.size() == 1) {
            Node n2 = n.children.values().iterator().next();
            if (sb.length() < minLength) {
                sb.append(n2.c);
            }
            n = n2;
        }
        return sb.toString();
    }

    static void test1() {
        String[] strs = {"flow", "flowers", "floating"};
        String res = new Leetcode014().longestCommonPrefix(strs);
        Assert.assertEquals("flo", res);
        System.out.println("test1 ok");
    }

    static void test2() {
        String[] strs = {"", "b"};
        String res = new Leetcode014().longestCommonPrefix(strs);
        Assert.assertEquals("", res);
        System.out.println("test2 ok");
    }

    static void test3() {
        String[] strs = {"aa", "a"};
        String res = new Leetcode014().longestCommonPrefix(strs);
        Assert.assertEquals("a", res);
        System.out.println("test3 ok");
    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }

}
