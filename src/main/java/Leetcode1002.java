import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/find-common-characters/
 *
 * Given an array A of strings made only from lowercase letters,
 * return a list of all characters that show up in all strings within the list (including duplicates).
 *
 * For example, if a character occurs 3 times in all strings but not 4 times,
 * you need to include that character three times in the final answer.
 *
 * You may return the answer in any order.
 *
 */
public class Leetcode1002 {

    public List<String> commonChars(String[] A) {
        final int ALPHABET_LENGTH = 26;
        // this is a map of minimal frequencies of
        int[] charsInAllStrings = new int[ALPHABET_LENGTH];
        Arrays.fill(charsInAllStrings, Integer.MAX_VALUE);

        int[] charFreqsMap = new int[ALPHABET_LENGTH];

        for (String s : A) {
            Arrays.fill(charFreqsMap, 0);
            for (int i=0; i<s.length(); i++) {
                char c = s.charAt(i);
                int idx = c - 'a';
                charFreqsMap[idx]++;
            }
            for (int i=0; i<ALPHABET_LENGTH; i++) {
                charsInAllStrings[i] = Math.min(charsInAllStrings[i], charFreqsMap[i]);
            }
        }

        // now capture characters
        List<String> res = new ArrayList<>();

        for (int i=0; i<ALPHABET_LENGTH; i++) {
            if (charsInAllStrings[i] > 0) {
                char c = (char)('a' + i);
                for (int j=0; j<charsInAllStrings[i]; j++) {
                    res.add(Character.toString(c));
                }
            }
        }

        return res;
    }

    static void test1() {
        String[] a = {"bella","label","roller"};
        List<String> res = new Leetcode1002().commonChars(a);
        Assert.assertEquals(Arrays.asList("e", "l", "l"), res);
        System.out.println("test1 ok");
    }

    static void test2() {
        String[] a = {"cool","lock","cook"};
        List<String> res = new Leetcode1002().commonChars(a);
        Assert.assertEquals(Arrays.asList("c", "o"), res);
        System.out.println("test2 ok");
    }

    public static void main(String[] args) {
        test1();
        test2();
    }
}
