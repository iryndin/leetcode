import org.junit.Assert;

/**
 *
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array/
 *
 * Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.
 *
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 */
public class Leetcode026 {

    public int removeDuplicatesSlow(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }

        final int EMPTY = 0;

        int len = nums.length;

        // 1,1,2
        int a = nums[0];
        for (int i=1; i<len; ) {
            if (nums[i] == a) {
                if (i == len-1) {
                    nums[i]=EMPTY;
                } else {
                    for (int j=i+1; j<len; j++) {
                        nums[j-1] = nums[j];
                    }
                }
                len--;
            } else {
                a = nums[i];
                i++;
            }
        }

        return len;
    }

    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }

    static void test1() {
        int[] a = {1,1,2};
        int len = new Leetcode026().removeDuplicates(a);
        Assert.assertEquals(2,len);
        int[] b = {1,2};
        for (int i=0; i<len; i++) {
            Assert.assertEquals(b[i], a[i]);
        }
    }

    static void test2() {
        int[] a = {0,0,1,1,1,2,2,3,3,4};
        int len = new Leetcode026().removeDuplicates(a);
        Assert.assertEquals(5,len);
        int[] b = {0,1,2,3,4};
        for (int i=0; i<len; i++) {
            Assert.assertEquals(b[i], a[i]);
        }
    }

    public static void main(String[] args) {
        test1();
        test2();
    }

}
