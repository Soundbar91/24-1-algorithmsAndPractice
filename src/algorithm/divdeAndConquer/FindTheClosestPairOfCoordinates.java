package algorithm.divdeAndConquer;

import java.io.*;
import java.util.*;

public class FindTheClosestPairOfCoordinates {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            List<Point> list = new ArrayList<>(N);

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++)
                list.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));

            double answer = solve(list);
            System.out.printf("%.2f%n",  answer);
        }
    }

    public static double solve(List<Point> list) {
        if (list.size() < 2) return 0;

        List<Point> Px = new ArrayList<>(list);
        List<Point> Py = new ArrayList<>(list);

        Px.sort(Comparator.comparing(Point::getX).thenComparing(Point::getY));
        Py.sort(Comparator.comparing(Point::getY).thenComparing(Point::getX));

        return closestPair(Px, Py);
    }

    public static double closestPair(List<Point> Px, List<Point> Py) {
        if (Px.size() <= 3) return bruteForce(Px);

        int mid = Px.size() / 2;
        List<Point> Lx = Px.subList(0, mid);
        List<Point> Rx = Px.subList(mid, Px.size());

        List<Point> Ly = new ArrayList<>();
        List<Point> Ry = new ArrayList<>();

        int midX = Px.get(mid).getX();
        for (Point point : Py) {
            if (point.getX() < midX) Ly.add(point);
            else Ry.add(point);
        }

        double dist = Math.min(closestPair(Lx, Ly), closestPair(Rx, Ry));
        return closestSplitPair(Px, Py, dist);
    }

    public static double closestSplitPair(List<Point> Px, List<Point> Py, double dist) {
        int midX = Px.get(Px.size() / 2).getX();

        List<Point> band = new ArrayList<>();
        for (Point point : Py) {
            if (Math.abs(point.getX() - midX) < dist) band.add(point);
        }

        double result = dist;
        for (int i = 0; i < band.size() - 1; i++) {
            for (int j = 1; j < Math.min(7, band.size() - i); j++) {
                result = Math.min(result, calcDist(band.get(i), band.get(i + j)));
            }
        }

        return result;
    }

    public static double bruteForce(List<Point> A) {
        double min = Double.MAX_VALUE;

        for (int i = 0; i < A.size() - 1; i++) {
            for (int j = i + 1; j < A.size(); j++) {
                min = Math.min(min, calcDist(A.get(i), A.get(j)));
            }
        }

        return min;
    }

    public static double calcDist(Point P, Point Q) {
        return Math.sqrt((P.x - Q.x) * (P.x - Q.x) + (P.y - Q.y) * (P.y - Q.y));
    }

    public static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}
