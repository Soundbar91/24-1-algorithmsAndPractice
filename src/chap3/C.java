package chap3;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

import static java.lang.Double.compare;
import static java.lang.Math.*;

public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            List<Point> list = new ArrayList<>();

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

        Px.sort((o1, o2) -> {
            if (compare(o1.getX(), o2.getX()) == 0) return compare(o1.getY(), o2.getY());
            else return compare(o1.getX(), o2.getX());
        });

        Py.sort((o1, o2) -> {
            if (compare(o1.getY(), o2.getY()) == 0) return compare(o1.getX(), o2.getX());
            else return compare(o1.getY(), o2.getY());
        });

        return closestPair(Px, Py);
    }

    public static double closestPair(List<Point> Px, List<Point> Py) {
        if (Px.size() <= 3) {
            return bruteForce(Px);
        }

        int mid = Px.size() / 2;
        List<Point> Lx = Px.subList(0, mid);
        List<Point> Rx = Px.subList(mid, Px.size());

        List<Point> Ly = new ArrayList<>();
        List<Point> Ry = new ArrayList<>();

        for (Point point : Py) {
            if (point.getX() < Px.get(mid).getX()) Ly.add(point);
            else Ry.add(point);
        }

        double dist = min(closestPair(Lx, Ly), closestPair(Rx, Ry));
        return closestSplitPair(Px, Py, dist);
    }

    public static double closestSplitPair(List<Point> Px, List<Point> Py, double dist) {
        int midX = (int) Px.get(Px.size() / 2).getX();

        List<Point> band = new ArrayList<>();
        for (Point point : Py) {
            if (abs(point.getX() - midX) < dist) band.add(point);
        }

        double result = dist;
        for (int i = 0; i < band.size() - 1; i++) {
            for (int j = 1; j < min(7, band.size() - i); i++) {
                result = min(result, calcDist(band.get(i), band.get(i + j)));
            }
        }

        return result;
    }

    public static double bruteForce(List<Point> A) {
        double min = Double.MAX_VALUE;

        for (int i = 0; i < A.size() - 1; i++) {
            for (int j = i + 1; j < A.size(); j++) {
                min = min(min, calcDist(A.get(i), A.get(j)));
            }
        }

        return min;
    }

    public static double calcDist(Point P, Point Q) {
        return sqrt((P.x - Q.x) * (P.x - Q.x) + (P.y - Q.y) * (P.y - Q.y));
    }
}