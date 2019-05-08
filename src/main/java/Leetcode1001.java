import org.junit.Assert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * https://leetcode.com/problems/grid-illumination/
 *
 * On a N x N grid of cells, each cell (x, y) with 0 <= x < N and 0 <= y < N has a lamp.
 *
 * Initially, some number of lamps are on.  lamps[i] tells us the location of the i-th lamp that is on.
 * Each lamp that is on illuminates every square on its x-axis, y-axis, and both diagonals (similar to a Queen in chess).
 *
 * For the i-th query queries[i] = (x, y), the answer to the query is 1 if the cell (x, y) is illuminated, else 0.
 *
 * After each query (x, y) [in the order given by queries], we turn off any lamps that are at cell (x, y)
 * or are adjacent 8-directionally (ie., share a corner or edge with cell (x, y).)
 *
 * Return an array of answers.  Each value answer[i] should be equal to the answer of the i-th query queries[i].
 */
public class Leetcode1001 {

    static final class Point {
        final int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "Point("+x+","+y+")";
        }
    }

    /**
     * Space complexity: O(4*lamps.length)
     * Time complexity: O(lamps.length) + O(lamps.length) ==> O(lamps.length)
     *
     * @param N
     * @param lamps
     * @param queries
     * @return
     */
    public int[] gridIllumination(int N, int[][] lamps, int[][] queries) {
        Map<Integer, Set<Point>> xmap = new HashMap<>();
        Map<Integer, Set<Point>> ymap = new HashMap<>();
        Map<Integer, Set<Point>> d1map = new HashMap<>();
        Map<Integer, Set<Point>> d2map = new HashMap<>();

        for (int[] lamp : lamps) {
            Point p = new Point(lamp[0], lamp[1]);
            // add horizontal coord
            addToMap(p.x, p, xmap);
            // add vertical coord
            addToMap(p.y, p, ymap);
            // add diag1
            // diag1 equation is: y = a1*x + b1, a1=1 ==> b1 = y-x
            int b1 = p.y - p.x;
            addToMap(b1, p, d1map);
            // add diag2
            // diag2 equation is: y = a2*x + b2, a2=-1 ==> b2 = y+x
            int b2 = p.y + p.x;
            addToMap(b2, p, d2map);
        }

        int[] answer = new int[queries.length];

        for (int i=0; i<queries.length; i++) {
            int[] q = queries[i];
            Point p = new Point(q[0], q[1]);
            // check if cell is illuminated
            {
                boolean isIlluminated = xmap.containsKey(p.x) ||
                        ymap.containsKey(p.y) ||
                        d1map.containsKey(p.y-p.x) ||
                        d2map.containsKey(p.y+p.x);
                answer[i] = isIlluminated ? 1 : 0;
            }

            // generate adjacent points
            Point[] adjacent = {
                    p,
                    new Point(p.x+1,p.y),
                    new Point(p.x-1,p.y),
                    new Point(p.x,p.y+1),
                    new Point(p.x,p.y-1),
                    new Point(p.x+1,p.y+1),
                    new Point(p.x-1,p.y-1),
                    new Point(p.x+1,p.y-1),
                    new Point(p.x-1,p.y+1)
            };

            // remove adjacent points
            for (Point a : adjacent) {
                removeFromMap(a.x, a, xmap);
                removeFromMap(a.y, a, ymap);
                removeFromMap(a.y - a.x, a, d1map);
                removeFromMap(a.y + a.x, a, d2map);
            }
        }

        return answer;
    }

    static void addToMap(int a, Point p, Map<Integer, Set<Point>> map) {
        Set<Point> set = map.get(a);
        if (set == null) {
            set = new HashSet<>();
            map.put(a, set);
        }
        set.add(p);
    }

    static void removeFromMap(int a, Point p, Map<Integer, Set<Point>> map) {
        Set<Point> set = map.get(a);
        if (set != null) {
            set.remove(p);
            if (set.isEmpty()) {
                map.remove(a);
            }
        }
    }

    static void test1() {
        int N = 5;
        int[][] lamps = {{0,0},{4,4}};
        int[][] queries = {{1,1},{1,0}};

        int[] a = new Leetcode1001().gridIllumination(N, lamps, queries);
        int[] expected = {1,0};
        Assert.assertTrue(Arrays.equals(a, expected));
        System.out.println("test1 ok");
    }

    static void test2() {
        int N = 10;

        int[][] lamps = {{3,4},{6,6},{1,8},{4,5},{8,7},{0,6},{5,2},{1,9}};
        int[][] queries = {{7,9},{2,8},{8,6},{6,8},{2,8}};

        int[] a = new Leetcode1001().gridIllumination(N, lamps, queries);
        int[] expected = {1,1,1,1,1};
        Assert.assertTrue(Arrays.equals(a, expected));
        System.out.println("test2 ok");
    }

    public static void main(String[] args) {
        test1();
        test2();
    }
}
