import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Leetcode018Test {

    @Test
    public void testSimple() {
        int[] nums = {1, 0, -1, 0, -2, 2};
        List<List<Integer>> res = new Leetcode018().fourSum(nums, 0);
        Set<List<Integer>> shouldHave = new HashSet<>();
        shouldHave.add(Arrays.asList(-1,  0, 0, 1));
        shouldHave.add(Arrays.asList(-2, -1, 1, 2));
        shouldHave.add(Arrays.asList(-2,  0, 0, 2));

        assertEquals(shouldHave.size(), res.size());
        for (List<Integer> l : res) {
            assertTrue(shouldHave.contains(l));
        }
    }

    @Test
    public void testSimple2() {
        int[] nums = {0,0,0,0};
        List<List<Integer>> res = new Leetcode018().fourSum(nums, 0);
        Set<List<Integer>> shouldHave = new HashSet<>();
        shouldHave.add(Arrays.asList(0,0,0,0));

        assertEquals(shouldHave.size(), res.size());
        for (List<Integer> l : res) {
            assertTrue(shouldHave.contains(l));
        }
    }
}
