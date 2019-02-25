import java.util.Arrays;

public class Leetcode985 {

    public int[] sumEvenAfterQueries(int[] A, int[][] queries) {
        int sumAllEven = 0;
        for (int a : A) {
            if ((a & 1) == 0) {
                sumAllEven += a;
            }
        }
        int[] answer = new int[queries.length];
        for (int i=0; i<queries.length;i++) {
            int[] q = queries[i];
            int val = q[0];
            int idx = q[1];
            int initialA = A[idx];
            int newA = initialA + val;
            A[idx] = newA;
            // if even becomes odd, then add subtract initialA from sumAllEven
            // if even stays even, then add difference (newA-initialA) to sumAllEven
            // if odd becomes even, then add newA to sumAllEven
            // if odd remains odd, do not update sumAllEven
            if ((initialA & 1) == 0) {
                // even remains even
                if ((newA & 1) == 0) {
                    sumAllEven += (newA-initialA);
                } else {
                    // even becomes odd
                    sumAllEven -= initialA;
                }
            } else {
                if ((newA & 1) == 0) {
                    sumAllEven += newA;
                }
            }
            answer[i] = sumAllEven;
        }
        return answer;
    }

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
         int[] A = {1,2,3,4};
         int[][] queries = {{1,0},{-3,1},{-4,0},{2,3}};
         int[] b = new Leetcode985().sumEvenAfterQueries(A, queries);
        System.out.println(Arrays.toString(b));
    }
}
