import org.junit.Assert;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/find-the-town-judge/
 *
 * In a town, there are N people labelled from 1 to N.  There is a rumor that one of these people is secretly the town judge.
 *
 * If the town judge exists, then:
 *
 *  - The town judge trusts nobody.
 *  - Everybody (except for the town judge) trusts the town judge.
 *  - There is exactly one person that satisfies properties 1 and 2.
 *
 * You are given trust, an array of pairs trust[i] = [a, b] representing that the person labelled a trusts the person labelled b.
 *
 * If the town judge exists and can be identified, return the label of the town judge.  Otherwise, return -1.
 *
 * Note:
 *
 * 1 <= N <= 1000
 * trust.length <= 10000
 * trust[i] are all different
 * trust[i][0] != trust[i][1]
 * 1 <= trust[i][0], trust[i][1] <= N
 */
public class Leetcode997 {

    public int findJudge(int N, int[][] trust) {
        if (N == 1) {
            return 1;
        }
        Set<Integer> allN = new HashSet<>(N);
        for (int i=1; i<=N; i++) {
            allN.add(i);
        }
        // set of people whi trust to someone (judge should not be among these persons!)
        Set<Integer> peopleWhoTrustSomeone = new HashSet<>();
        for (int[] t : trust) {
            peopleWhoTrustSomeone.add(t[0]);
        }
        Set<Integer> peopleWhoDoNotTrustAnyone = new HashSet<>();
        for (int i=1; i<=N; i++) {
            if (!peopleWhoTrustSomeone.contains(i)) {
                peopleWhoDoNotTrustAnyone.add(i);
            }
        }

        // now check that for people who do not trust anyone, everyone trust them
        Map<Integer, Set> map = new HashMap<>();
        for (int possibleJudge : peopleWhoDoNotTrustAnyone) {
            Set<Integer> peopleWhoDoNotTrustToThisPerson = new HashSet<>(allN);
            peopleWhoDoNotTrustToThisPerson.remove(possibleJudge);
            map.put(possibleJudge, peopleWhoDoNotTrustToThisPerson);
        }

        for (int[] t : trust) {
            Set<Integer> s = map.get(t[1]);
            if (s != null) {
                s.remove(t[0]);
            }
        }

        int result = -1;
        for (Map.Entry<Integer, Set> e : map.entrySet()) {
            if (e.getValue().isEmpty()) {
                if (result != -1) {
                    return -1;
                } else {
                    result = e.getKey();
                }
            }
        }

        return result;
    }



    static void test1() {
        int N=2;
        int[][] trust = {{1,2}};

        int res = new Leetcode997().findJudge(N, trust);
        Assert.assertEquals(2, res);
        System.out.println("test1 ok");
    }

    static void test2() {
        int N=3;
        int[][] trust = {{1,3}, {2,3}};

        int res = new Leetcode997().findJudge(N, trust);
        Assert.assertEquals(3, res);
        System.out.println("test2 ok");
    }

    static void test3() {
        int N=3;
        int[][] trust = {{1,3}, {2,3}, {3,1}};

        int res = new Leetcode997().findJudge(N, trust);
        Assert.assertEquals(-1, res);
        System.out.println("test3 ok");
    }

    static void test4() {
        int N=3;
        int[][] trust = {{1,2}, {2,3}};

        int res = new Leetcode997().findJudge(N, trust);
        Assert.assertEquals(-1, res);
        System.out.println("test4 ok");
    }

    static void test5() {
        int N=4;
        int[][] trust = {{1,3},{1,4},{2,3},{2,4},{4,3}};

        int res = new Leetcode997().findJudge(N, trust);
        Assert.assertEquals(3, res);
        System.out.println("test5 ok");
    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
        test5();
    }
}
