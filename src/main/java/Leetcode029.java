/**
 * https://leetcode.com/problems/divide-two-integers/
 *
 * Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.
 *
 * Return the quotient after dividing dividend by divisor.
 *
 * The integer division should truncate toward zero.
 */
public class Leetcode029 {

    public int divide(int dividend, int divisor) {
        boolean minus = false;
        if ((dividend < 0 && divisor > 0 ) || (dividend > 0 && divisor < 0 )) {
            minus = true;
        }

        long l1 =  Math.abs((long)dividend);
        long l2  = Math.abs((long)divisor);

        if (l2 == 1) {
            return minus ? filter(-l1) : filter(l1);
        }
        if (l2 > l1) {
            return 0;
        }
        int n=1;
        long d = l2;
        while (d < l1) {
            d = d << 1;
            n = n << 1;
        }
        while (d > l1) {
            d -= l2;
            n--;
        }

        return minus ? filter(-n) : filter(n);
    }

    static int filter(long a) {
        if (a > 0) {
            return a >= Integer.MAX_VALUE ? Integer.MAX_VALUE : (int)a;
        } else {
            return a <= Integer.MIN_VALUE ? Integer.MIN_VALUE : (int)a;
        }
    }

    public static void main(String[] args) {
        Leetcode029 l = new Leetcode029();
        //System.out.println(l.divide(-800000,2));
        //System.out.println(l.divide(-1,1));
        //System.out.println(l.divide(-2147483648,-1));
        System.out.println(l.divide(-1010369383,-2147483648));
    }



}

