/**
 * https://leetcode.com/problems/string-without-aaa-or-bbb/
 *
 * Given two integers A and B, return any string S such that:
 *
 * S has length A + B and contains exactly A 'a' letters, and exactly B 'b' letters;
 * The substring 'aaa' does not occur in S;
 * The substring 'bbb' does not occur in S.
 *
 *
 *
 */
public class Leetcode984 {

    /**
     * If A > B:
     *   minimum number of 'b' is A/2-1.
     *   max Number of B: A-1
     *
     *   S
     *   while (B > 0 && A > 0) {
     *
     *   }
     *
     *
     * @param A
     * @param B
     * @return
     */
    public String strWithout3a3b(int A, int B) {
        if (A > B) {
            StringBuilder sb = new StringBuilder(A+B);
            while (A > 0 && B > 0) {
                if (A > B) {
                    sb.append('a').append('a').append('b');
                    B--;
                    A--;
                    A--;
                } else {
                    sb.append('a').append('b');
                    B--;
                    A--;
                }
            }
            while (A > 0) {
                sb.append('a');
                A--;
            }
            return sb.toString();
        } else {
            // A < B
            StringBuilder sb = new StringBuilder(A+B);
            while (A > 0 && B > 0) {
                if (B > A) {
                    sb.append('b').append('b').append('a');
                    A--;
                    B--;
                    B--;
                } else {
                    sb.append('b').append('a');
                    B--;
                    A--;
                }
            }
            while (B > 0) {
                sb.append('b');
                B--;
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        test1();
        test2();
    }

    private static void test1() {
        int A=4, B=1;
        String res = new Leetcode984().strWithout3a3b(A,B);
        System.out.println(res);
    }

    private static void test2() {
        int A=1, B=2;
        String res = new Leetcode984().strWithout3a3b(A,B);
        System.out.println(res);
    }
}
