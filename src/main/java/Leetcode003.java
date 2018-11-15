import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 *
 * Given a string, find the length of the longest substring without repeating characters.
 *
 * Example 1:
 *
 * Input: "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 *
 * Input: "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 *
 * Input: "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 *              Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 *
 */
public class Leetcode003 {

    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        for (int i=0; i<s.length(); i++) {
            Set<Character> chars = new HashSet<>();
            for (int j=i; j<s.length(); j++) {
                if (!chars.add(s.charAt(j))) {
                    break;
                }
            }
            if (chars.size() > maxLength) {
                maxLength = chars.size();
            }
        }

        return maxLength;
    }
}
