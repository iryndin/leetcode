import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/roman-to-integer/
 *
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 *
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * For example, two is written as II in Roman numeral, just two one's added together.
 * Twelve is written as, XII, which is simply X + II.
 * The number twenty seven is written as XXVII, which is XX + V + II.
 *
 * Roman numerals are usually written largest to smallest from left to right.
 * However, the numeral for four is not IIII.
 * Instead, the number four is written as IV.
 * Because the one is before the five we subtract it making four.
 * The same principle applies to the number nine, which is written as IX.
 * There are six instances where subtraction is used:
 *
 * I can be placed before V (5) and X (10) to make 4 and 9.
 * X can be placed before L (50) and C (100) to make 40 and 90.
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 * Given a roman numeral, convert it to an integer. Input is guaranteed to be within the range from 1 to 3999.
 */
public class Leetcode013 {

    public int romanToInt(String s) {
        int result = 0;
        Map<String, Integer> map = new HashMap<>();
        {
            map.put("I", 1);
            map.put("V", 5);
            map.put("X", 10);
            map.put("L", 50);
            map.put("C", 100);
            map.put("D", 500);
            map.put("M", 1000);
            map.put("IV", 4);
            map.put("IX", 9);
            map.put("XL", 40);
            map.put("XC", 90);
            map.put("CD", 400);
            map.put("CM", 900);
        }

        for (int i=0; i<s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = i+1 < s.length() ? s.charAt(i+1) : '?';

            String ss = c1 + "" + c2;
            Integer n = map.get(ss);
            if (n != null) {
                result += n;
                i++;
            } else {
                result += map.get(Character.toString(c1));
            }
        }

        return result;
    }

    static void test1() {
        String s = "MCMXCIV";
        int res = new Leetcode013().romanToInt(s);
        Assert.assertEquals(1994, res);
        System.out.println("test1 ok");
    }

    static void test2() {
        String s = "III";
        int res = new Leetcode013().romanToInt(s);
        Assert.assertEquals(3, res);
        System.out.println("test2 ok");
    }

    static void test3() {
        String s = "IV";
        int res = new Leetcode013().romanToInt(s);
        Assert.assertEquals(4, res);
        System.out.println("test3 ok");
    }

    static void test4() {
        String s = "IX";
        int res = new Leetcode013().romanToInt(s);
        Assert.assertEquals(9, res);
        System.out.println("test4 ok");
    }

    static void test5() {
        String s = "LVIII";
        int res = new Leetcode013().romanToInt(s);
        Assert.assertEquals(58, res);
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
