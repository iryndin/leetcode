import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Leetcode003Test {

    @Test
    public void testSomeTestCases() {
        testStringAndLength("abcabcbb", 3);
        testStringAndLength("bbbbb", 1);
        testStringAndLength("pwwkew", 3);
    }

    public void testStringAndLength(String s, int maxLength) {
        assertEquals(maxLength, new Leetcode003().lengthOfLongestSubstring(s));
    }
}
