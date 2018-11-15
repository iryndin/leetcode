/**
 * https://leetcode.com/problems/add-two-numbers/
 *
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Example:
 *
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 *
 */
public class Leetcode002 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode result = null;
        ListNode resultPtr = null;
        int overflow = 0;

        while (l1 != null) {
            int l2val = 0;
            if (l2 != null) {
                l2val = l2.val;
                l2 = l2.next;
            }
            int res = overflow + l2val + l1.val;
            if (res > 9) {
                overflow = 1;
                res = res - 10;
            } else {
                overflow = 0;
            }
            ListNode listNode = new ListNode(res);
            if (resultPtr == null) {
                result = resultPtr = listNode;
            } else {
                resultPtr.next = listNode;
                resultPtr = listNode;
            }
            l1 = l1.next;
        }
        while (l2 != null) {
            int res = overflow + l2.val;
            if (res > 9) {
                overflow = 1;
                res = res - 10;
            } else {
                overflow = 0;
            }
            ListNode listNode = new ListNode(res);

            if (resultPtr == null) {
                result = resultPtr = listNode;
            } else {
                resultPtr.next = listNode;
                resultPtr = listNode;
            }

            l2 = l2.next;
        }

        if (overflow == 1) {
            resultPtr.next = new ListNode(1);
        }

        return result;
    }
}
