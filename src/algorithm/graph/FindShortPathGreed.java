package algorithm.graph;

import java.util.*;

public class FindShortPathGreed {
    static class Point implements Comparable<Point> {
        int x;
        int y;
        int sum;

        public Point(int x, int y, int sum) {
            this.x = x;
            this.y = y;
            this.sum = sum;
        }

        @Override
        public int compareTo(Point other) {
            return this.sum - other.sum;
        }
    }

    public static int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dist = new int[m][n];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.offer(new Point(0, 0, grid[0][0]));
        dist[0][0] = grid[0][0];

        int[] dx = {1, 0};
        int[] dy = {0, 1};

        while (!pq.isEmpty()) {
            Point cur = pq.poll();
            int x = cur.x;
            int y = cur.y;
            int sum = cur.sum;

            if (x == m - 1 && y == n - 1) {
                return dist[x][y];
            }

            for (int i = 0; i < 2; i++) {
                int ux = x + dx[i];
                int uy = y + dy[i];

                if (valid(ux, uy, m, n)) {
                    int newSum = sum + grid[ux][uy];
                    if (newSum < dist[ux][uy]) {
                        dist[ux][uy] = newSum;
                        pq.offer(new Point(ux, uy, newSum));
                    }
                }
            }
        }

        // 도달할 수 없는 경우
        return -1;
    }

    public static boolean valid(int x, int y, int m, int n) {
        return (0 <= x && x < m) && (0 <= y && y < n);
    }

    public static void main(String[] args) {
        int[][] grid = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        System.out.println(minPathSum(grid)); // 7 출력
    }
}
