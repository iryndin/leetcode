import java.util.Arrays;

/**
 * https://leetcode.com/problems/check-if-word-is-valid-after-substitutions/
 *
 * We are given that the string "abc" is valid.
 *
 * From any valid string V, we may split V into two pieces X and Y such that X + Y (X concatenated with Y) is equal to V.
 * (X or Y may be empty.)  Then, X + "abc" + Y is also valid.
 *
 * If for example S = "abc", then examples of valid strings are: "abc", "aabcbc", "abcabc", "abcabcababcc".
 * Examples of invalid strings are: "abccba", "ab", "cababc", "bac".
 *
 * Return true if and only if the given string S is valid.
 *
 * Note:
 *
 * 1 <= S.length <= 20000
 * S[i] is 'a', 'b', or 'c'
 */
public class Leetcode1003 {

    public boolean isValid(String s) {


        return false;
    }

    public static void main(String[] args) {
        String s = "abccba";
        String[] a = s.split("abc");
        System.out.println(Arrays.toString(a));
    }

}
