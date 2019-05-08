/**
 * https://leetcode.com/problems/palindrome-number/
 *
 * Determine whether an integer is a palindrome.
 * An integer is a palindrome when it reads the same backward as forward.
 *
 */
public class Leetcode009 {

    public boolean isPalindrome(int x) {
        if (x<0) {
            return false;
        }
        if (x<10) {
            return true;
        }
        int rev = reverse(x);
        return rev == x;
    }

    private int reverse(int x) {
        int res = 0;
        while (x>0) {
            int n = x % 10;
            x = x/10;
            if (res == 0 && n == 0) {
                continue;
            }
            res = res*10 + n;
        }
        return res;
    }
}
