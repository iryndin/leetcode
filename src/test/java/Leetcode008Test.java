import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Leetcode008Test {

    @Test
    public void testSimplePositiveNumber() {
        assertEquals(1100556, new Leetcode008().myAtoi("1100556"));
    }

    @Test
    public void testSimpleNegativeNumber() {
        assertEquals(-21100556, new Leetcode008().myAtoi("-21100556"));
    }

    @Test
    public void testSimplePositiveNumberWithSpacesAhead() {
        assertEquals(1100556, new Leetcode008().myAtoi("   1100556"));
    }

    @Test
    public void testSimpleNegativeNumberWithSpacesAhead() {
        assertEquals(-21100556, new Leetcode008().myAtoi("       -21100556"));
    }

    @Test
    public void testValueGreaterThanIntegerMaxValue() {
        assertEquals(Integer.MAX_VALUE, new Leetcode008().myAtoi("214748364715675675675675675675675675675675676571"));
    }

    @Test
    public void testValueLessThanIntegerMinValue() {
        assertEquals(Integer.MIN_VALUE, new Leetcode008().myAtoi("-214748364715675675675675675675675675675675676571"));
    }

    @Test
    public void testSimplePositiveNumberWithLettersAfter() {
        assertEquals(1100556, new Leetcode008().myAtoi("1100556 go johhny go!"));
    }

    @Test
    public void testLettersAheadNumber() {
        assertEquals(0, new Leetcode008().myAtoi("words and 987"));
    }

    @Test
    public void testDecimalNumber() {
        assertEquals(3, new Leetcode008().myAtoi("3.14159"));
    }
}
