import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/k-closest-points-to-origin/
 *
 * We have a list of points on the plane.  Find the K closest points to the origin (0, 0).
 *
 * (Here, the distance between two points on a plane is the Euclidean distance.)
 *
 * You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)
 */
public class Leetcode973 {

    static class Point {
        final int[] coords;
        final double distance;

        public Point(int[] coords) {
            this.coords = coords;
            this.distance = coords[0]*coords[0] + coords[1]*coords[1];
        }

        public double getDistance() {
            return distance;
        }
    }

    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<Point> queue = new PriorityQueue(points.length, Comparator.comparingDouble(Point::getDistance));
        for (int[] p : points) {
            queue.add(new Point(p));
        }

        int[][] result = new int[K][];
        for (int i=0; i<K; i++) {
            result[i] = queue.poll().coords;
        }
        return result;
    }

    static void test1() {
        int[][] points = {{1,3},{-2,2}};
        int K = 1;
        int[][] res = new Leetcode973().kClosest(points, K);
        System.out.println(res);
    }

    public static void main(String[] args) {
        test1();
    }
}
