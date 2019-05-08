
/**
 *
 * https://leetcode.com/problems/merge-two-sorted-lists/
 *
 * Merge two sorted linked lists and return it as a new list.
 * The new list should be made by splicing together the nodes of the first two lists.
 *
 * Example:
 *
 * Input: 1->2->4, 1->3->4
 * Output: 1->1->2->3->4->4
 */
public class Leetcode021 {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode cur = null;

        while (!(l1 == null && l2 == null)) {
            if (l1 != null) {
                if (l2 != null) {
                    // l1 != null && l2 != null
                    if (l1.val == l2.val) {
                        ListNode n1 = new ListNode(l1.val);
                        if (head == null) {
                            head = n1;
                            cur = n1;
                        } else {
                            cur.next = n1;
                            cur = n1;
                        }
                        ListNode n2 = new ListNode(l1.val);
                        cur.next = n2;
                        cur = n2;

                        l1 = l1.next;
                        l2 = l2.next;
                    } else if (l1.val < l2.val) {
                        ListNode n1 = new ListNode(l1.val);
                        if (head == null) {
                            head = n1;
                            cur = n1;
                        } else {
                            cur.next = n1;
                            cur = n1;
                        }
                        l1 = l1.next;
                    } else {
                        ListNode n1 = new ListNode(l2.val);
                        if (head == null) {
                            head = n1;
                            cur = n1;
                        } else {
                            cur.next = n1;
                            cur = n1;
                        }
                        l2 = l2.next;
                    }
                } else {
                    // l1 != null && l2 == null
                    if (head == null) {
                        head = l1;
                        cur = l1;
                    } else {
                        cur.next = l1;
                    }
                    break;
                }
            } else {
                // l1 == null && l2 != null
                if (head == null) {
                    head = l2;
                    cur = l2;
                } else {
                    cur.next = l2;
                }
                break;
            }
        }

        return head;
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
