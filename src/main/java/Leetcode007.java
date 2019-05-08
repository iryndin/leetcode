import org.junit.Assert;

public class Leetcode007 {

    public int reverse(int x) {
        if (-10 < x && x < 10) {
            return x;
        }

        long result=0;
        boolean negative = x<0;
        x = Math.abs(x);

        while (x>0) {
            int n = x % 10;
            x = x/10;
            if (n == 0 && result == 0) {
                continue;
            }
            result = result*10+n;
        }

        if (result > Integer.MAX_VALUE) {
            return 0;
        }
        if (negative) {
            result = -result;
        }
        return (int)result;
    }

    static void test1() {
        int x = 102;
        int res = new Leetcode007().reverse(x);
        Assert.assertEquals(201, res);
        System.out.println("test1 ok");
    }

    static void test2() {
        int x = 123;
        int res = new Leetcode007().reverse(x);
        Assert.assertEquals(321, res);
        System.out.println("test2 ok");
    }

    static void test3() {
        int x = -123;
        int res = new Leetcode007().reverse(x);
        Assert.assertEquals(-321, res);
        System.out.println("test3 ok");
    }

    static void test4() {
        int x = 120;
        int res = new Leetcode007().reverse(x);
        Assert.assertEquals(21, res);
        System.out.println("test4 ok");
    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
    }
}
