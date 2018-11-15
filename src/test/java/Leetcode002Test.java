import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class Leetcode002Test {

    @Test
    public void shouldCorrectlyAddAllNumbers() {
        testAddTwoNumbers("342", "465");
        testAddTwoNumbers("0", "342342342");
        testAddTwoNumbers("454554545", "44");
        testAddTwoNumbers("5", "5");
    }

    @Test
    public void testSimple() {
        final String n1 = "342";
        final String n2 = "465";
        final String nres = Integer.toString(Integer.parseInt(n1)+Integer.parseInt(n2));
        Leetcode002.ListNode l1 = createList(n1);
        Leetcode002.ListNode l2 = createList(n2);
        Leetcode002.ListNode result = new Leetcode002().addTwoNumbers(l1, l2);
        checkEqual(nres, result);
    }

    @Test
    public void shouldCreateListFromNumber() {
        final String number = "45435242432";
        Leetcode002.ListNode l = createList(number);
        checkEqual(number, l);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionBecauseOfDecimalPointCharacter() {
        final String number = "45435242.432";
        Leetcode002.ListNode l = createList(number);
    }

    public static void testAddTwoNumbers(String s1, String s2) {
        final String sres = Integer.toString(Integer.parseInt(s1)+Integer.parseInt(s2));
        Leetcode002.ListNode l1 = createList(s1);
        Leetcode002.ListNode l2 = createList(s2);
        Leetcode002.ListNode result = new Leetcode002().addTwoNumbers(l1, l2);
        checkEqual(sres, result);
    }

    public static void checkEqual(String number, Leetcode002.ListNode list) {
        char[] chars = number.toCharArray();
        for (int i=chars.length-1; i>=0 ; i--) {
            int val = Integer.parseInt(""+chars[i]);
            assertEquals(val, list.val);
            list = list.next;
        }
    }

    public static Leetcode002.ListNode createList(String number) {
        Leetcode002.ListNode result = null;
        Leetcode002.ListNode cur = null;
        for (int i=number.length()-1; i>=0 ; i--) {
            char c = number.charAt(i);
            if (!Character.isDigit(c)) {
                throw new IllegalArgumentException(String.format("String '%s' should contain only digits", number));
            }
            Leetcode002.ListNode node = new Leetcode002.ListNode(c - '0');
            if (cur == null) {
                result = cur = node;
            } else {
                cur.next = node;
                cur = node;
            }
        }
        return result;
    }
}