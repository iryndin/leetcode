import org.junit.Assert;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/maximize-sum-of-array-after-k-negations/
 *
 * Given an array A of integers, we must modify the array in the following way:
 * we choose an i and replace A[i] with -A[i], and we repeat this process K times in total.
 * (We may choose the same index i multiple times.)
 *
 * Return the largest possible sum of the array after modifying it in this way.
 */
public class Leetcode1005 {

    /**
     * Approach:
     * 1. Sort Array ASC order
     * 2. If there are negative elements in the array, negate minimal of them and then count sum
     * 3. After all negative elements are processed, sort array once more, and negate K times minimal positive elements.
     * 4. Then count sum
     *
     * @param A
     * @param K
     * @return
     */
    public int largestSumAfterKNegations(int[] A, int K) {
        if (A.length == 0) {
            return 0;
        }
        if (K == 0) {
            return Arrays.stream(A).sum();
        }

        // 1. Copy original array to local copy, to not corrupt input data
        int[] a = new int[A.length];
        System.arraycopy(A, 0, a, 0, A.length);
        // 2. Sort array, to have negative elements first
        Arrays.sort(a);
        // 3. Negate negative elements or stop loop immediately if there are no negative elements
        for (int i=0; i<a.length && K > 0; i++) {
            if (a[i] < 0) {
                a[i] = -a[i];
                K--;
            } else {
                break;
            }
        }
        // 4. If all negations are done, return sum
        if (K == 0) {
            return Arrays.stream(a).sum();
        }
        // 5. Sort array again to have smallest elements first
        Arrays.sort(a);
        // 6. Negate smallest element K times, to minimize its effect to final sum
        while (K > 0) {
            a[0] = -a[0];
            K--;
        }
        return Arrays.stream(a).sum();
    }

    static void test1() {
        int[] A = {4,2,3};
        int K=1;
        int res = new Leetcode1005().largestSumAfterKNegations(A,K);
        Assert.assertEquals(5, res);
        System.out.println("test1 ok");
    }

    public static void main(String[] args) {
        test1();
    }
}
