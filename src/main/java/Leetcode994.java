import org.junit.Assert;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * https://leetcode.com/problems/rotting-oranges/
 *
 * In a given grid, each cell can have one of three values:
 *
 * the value 0 representing an empty cell;
 * the value 1 representing a fresh orange;
 * the value 2 representing a rotten orange.
 * Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.
 *
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange.  If this is impossible, return -1 instead.
 *
 */
public class Leetcode994 {

    public int orangesRotting(int[][] grid) {

        final int FRESH = 1;
        final int ROTTEN = 2;

        class Pair {
            public final int i;
            public final int j;

            public Pair(int i, int j) {
                this.i = i;
                this.j = j;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Pair pair = (Pair) o;
                return i == pair.i &&
                        j == pair.j;
            }

            @Override
            public int hashCode() {
                return Objects.hash(i, j);
            }
        }

        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        final int LENGTH = grid.length;
        final int WIDTH = grid[0].length;

        Set<Pair> fresh = new HashSet<>();
        Set<Pair> rotten = new HashSet<>();

        for (int i=0; i<LENGTH; i++) {
            for (int j=0; j<WIDTH; j++) {
                if (grid[i][j] == ROTTEN) {
                    rotten.add(new Pair(i,j));
                } else if (grid[i][j] == FRESH) {
                    fresh.add(new Pair(i,j));
                }
            }
        }

        if (fresh.isEmpty()) {
            return 0;
        }

        int minutes = 0;
        while (!fresh.isEmpty()) {
            Set<Pair> willBecomeRotten = new HashSet<>();
            for (Pair p : fresh) {
                boolean foundAdjacentRotten =
                        rotten.contains(new Pair(p.i-1, p.j)) ||
                        rotten.contains(new Pair(p.i+1, p.j)) ||
                        rotten.contains(new Pair(p.i, p.j-1)) ||
                        rotten.contains(new Pair(p.i, p.j+1));
                if (foundAdjacentRotten) {
                    willBecomeRotten.add(p);
                }
            }
            if (willBecomeRotten.isEmpty()) {
                return -1;
            }
            rotten.addAll(willBecomeRotten);
            fresh.removeAll(willBecomeRotten);
            minutes++;
        }

        return minutes;
    }

    static void test1() {
        int[][] a = {
                {2, 1, 1},
                {1, 1, 0},
                {0, 1, 1}
        };
        int res = new Leetcode994().orangesRotting(a);
        Assert.assertEquals(4, res);
        System.out.println("test1 ok");
    }

    static void test2() {
        int[][] a = {
                {2, 1, 1},
                {0, 1, 1},
                {1, 0, 1}
        };
        int res = new Leetcode994().orangesRotting(a);
        Assert.assertEquals(-1, res);
        System.out.println("test2 ok");
    }

    static void test3() {
        int[][] a = {
                {0, 2},
        };
        int res = new Leetcode994().orangesRotting(a);
        Assert.assertEquals(0, res);
        System.out.println("test3 ok");
    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }

}
