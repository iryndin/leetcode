import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/broken-calculator/
 *
 * On a broken calculator that has a number showing on its display, we can perform two operations:
 *
 * Double: Multiply the number on the display by 2, or;
 * Decrement: Subtract 1 from the number on the display.
 * Initially, the calculator is displaying the number X.
 *
 * Return the minimum number of operations needed to display the number Y.
 */
public class Leetcode991 {

    static class Pair {
        public final int num;
        public final int ops;

        public Pair(int num, int ops) {
            this.num = num;
            this.ops = ops;
        }
    }

    /**
     * Approach:
     * if Y==X, return 0;
     * if Y < X: return X-Y;
     * if Y>X:
     * here can be variants.
     * each next number can be achieved either by doubling prev number, or by decrementing prev number.
     * This is DP task.
     * So:
     * X_i = X_(i-1)*2 or X_i = X_(i-1)-1
     *
     * op(x) - number of operations to reach x starting from t
     * op(x,t) = min(op(x/2,t), op(x+1, t));
     *
     * @param X
     * @param Y
     * @return
     */
    public int brokenCalc_TimeLimitExceeds(int X, int Y) {

        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(X, 0));

        int minOps = Integer.MAX_VALUE;

        Set<Integer> processed = new HashSet<>();

        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            if (p.num == Y) {
                int ops = p.ops;
                if (ops < minOps) {
                    minOps = ops;
                }
            } else if (p.num > Y) {
                int ops = p.ops + (p.num - Y);
                if (ops < minOps) {
                    minOps = ops;
                }
            } else {
                // p.num < Y

                int nextNum = p.num * 2;
                if (!processed.contains(nextNum)) {
                    queue.add(new Pair(nextNum, p.ops + 1));
                    processed.add(nextNum);
                }
                nextNum = p.num-1;
                if (nextNum > 0 && !processed.contains(nextNum)) {
                    queue.add(new Pair(nextNum, p.ops + 1));
                    processed.add(nextNum);
                }
            }
        }

        return minOps;
    }



    // While Y is larger than X, add 1 if it is odd, else divide by 2. After, we need to do X - Y additions to reach X.
    public int brokenCalc(int X, int Y) {
        int ans = 0;
        while (Y > X) {
            ans++;
            if (Y % 2 == 1)
                Y++;
            else
                Y /= 2;
        }

        return ans + X - Y;
    }

    static void test1() {
        int res = new Leetcode991().brokenCalc(2,3);
        assertEquals(2, res);
        System.out.println("test1 ok");
    }

    static void test2() {
        int res = new Leetcode991().brokenCalc(5,8);
        assertEquals(2, res);
        System.out.println("test2 ok");
    }

    static void test3() {
        int res = new Leetcode991().brokenCalc(3,10);
        assertEquals(3, res);
        System.out.println("test3 ok");
    }

    static void test4() {
        int res = new Leetcode991().brokenCalc(1024,1);
        assertEquals(1023, res);
        System.out.println("test4 ok");
    }

    static void test5() {
        int res = new Leetcode991().brokenCalc(1,1000000000);
        assertEquals(1023, res);
        System.out.println("test5 ok");
    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
        test5();
    }
}
