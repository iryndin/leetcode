import java.util.*;

public class Leetcode020 {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (isOpening(c)) {
                stack.push(c);
            } else if (isClosing(c)) {
                if (stack.isEmpty()) {
                    return false;
                }
                char d = stack.pop();
                if (!isComplement(d,c)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    boolean isClosing(char c) {
        return c == ')' ||
                c == '}' ||
                c == ']';
    }

    boolean isOpening(char c) {
        return c == '(' ||
                c == '{' ||
                c == '[';
    }

    boolean isComplement(char a, char b) {
        switch (a) {
            case '(': return b == ')';
            case '[': return b == ']';
            case '{': return b == '}';
        }
        return false;
    }
}
