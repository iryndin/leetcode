import org.junit.Assert;

import java.util.Stack;

/**
 *
 * https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 *
 * Given a linked list, remove the n-th node from the end of list and return its head.
 *
 * Example:
 *
 * Given linked list: 1->2->3->4->5, and n = 2.
 *
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 * Note:
 *
 * Given n will always be valid.
 *
 * Follow up:
 *
 * Could you do this in one pass?
 */
public class Leetcode019 {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        Stack<ListNode> stack = new Stack<>();

        // 1. Put the list into stack
        ListNode cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }

        // 2. Find n-th element from the end
        ListNode n1=null, n2=null;
        while (n != 0) {
            n2 = n1;
            n1 = stack.pop();
            n--;
        }

        // 3. relink list nodes
        if (stack.isEmpty()) {
            head = n2;
        } else {
            ListNode n0 = stack.pop();
            n0.next = n2;
        }

        return head;
    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
    }

    static void test1() {
        ListNode l = createList(1,2,3,4,5);
        Assert.assertEquals("1->2->3->4->5", printList(l));
        ListNode l2 = new Leetcode019().removeNthFromEnd(l, 2);
        Assert.assertEquals("1->2->3->5", printList(l2));
    }

    static void test2() {
        ListNode l = createList(1,2,3,4,5);
        Assert.assertEquals("1->2->3->4->5", printList(l));
        ListNode l2 = new Leetcode019().removeNthFromEnd(l, 1);
        Assert.assertEquals("1->2->3->4", printList(l2));
    }

    static void test3() {
        ListNode l = createList(1,2,3,4,5);
        Assert.assertEquals("1->2->3->4->5", printList(l));
        ListNode l2 = new Leetcode019().removeNthFromEnd(l, 4);
        Assert.assertEquals("1->3->4->5", printList(l2));
    }

    static void test4() {
        ListNode l = createList(1,2,3,4,5);
        Assert.assertEquals("1->2->3->4->5", printList(l));
        ListNode l2 = new Leetcode019().removeNthFromEnd(l, 5);
        Assert.assertEquals("2->3->4->5", printList(l2));
    }

    static String printList(ListNode head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val);
            if (head.next != null) {
                sb.append("->");
            }
            head = head.next;
        }
        return sb.toString();
    }

    static ListNode createList(int... aa) {
        ListNode head = null;
        ListNode prev = null;
        for (int a : aa) {
            ListNode n = new ListNode(a);
            if (head == null) {
                head = n;
                prev = n;
            } else {
                prev.next = n;
                prev = n;
            }
        }
        return head;
    }



    static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }

      @Override
      public String toString() {
          return String.format("ListNode(%d)", val);
      }
    }
}
