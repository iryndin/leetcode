import org.junit.Assert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Leetcode001 {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i=0; i<nums.length; i++) {
            map.put(nums[i], i);
        }
        int[] result = new int[2];

        for (int i=0; i<nums.length; i++) {
            int a = nums[i];
            Integer i2 = map.get(target-a);
            if (i2 != null && i2 != i) {
                result[0]=i;
                result[1]=i2;
                break;
            }
        }
        return result;
    }

    static void test1() {
        int[] nums = {3,2,4};
        int target=6;
        int[] res = new Leetcode001().twoSum(nums, target);
        int[] expected = {1,2};
        Assert.assertTrue(Arrays.equals(expected, res));
        System.out.println("test1 ok");
    }

    public static void main(String[] args) {
        test1();
    }

}
