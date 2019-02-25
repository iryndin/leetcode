import java.util.HashSet;
import java.util.Set;

public class Leetcode817 {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public int numComponents(ListNode head, int[] G) {
        Set<Integer> gset = new HashSet<>();
        for (int i : G) {
            gset.add(i);
        }

        int connectedComponents = 0;

        boolean connected = false;
        ListNode n = head;
        while (n != null) {
            if (gset.contains(n.val)) {
                connected = true;
            } else {
                if (connected) {
                    connectedComponents++;
                }
                connected = false;
            }
            n = n.next;
        }

        if (connected) {
            connectedComponents++;
        }

        return connectedComponents;
    }
}
