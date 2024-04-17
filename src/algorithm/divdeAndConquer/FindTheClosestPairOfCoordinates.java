package algorithm.divdeAndConquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

import static java.lang.Math.*;

public class FindTheClosestPairOfCoordinates {
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

        /*
        * x축 기준으로 오름차순 정렬
        * x축이 같은 경우 y축 기준으로 오름차순 정렬*/
        Px.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                int compareX = Integer.compare(o1.getX(), o2.getX());
                if (compareX == 0) {
                    return Integer.compare(o1.getY(), o2.getY());
                } else {
                    return compareX;
                }
            }
        });

        /*
        * y축 기준으로 오름차순 정렬
        * y축이 같은 경우 x축 기준으로 오름차순 정렬*/
        Py.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                int compareY = Integer.compare(o1.getY(), o2.getY());
                if (compareY == 0) {
                    return Integer.compare(o1.getX(), o2.getX());
                } else {
                    return compareY;
                }
            }
        });

        return closestPair(Px, Py);
    }

    public static double closestPair(List<Point> Px, List<Point> Py) {
        // 점이 3개 이하가 된 경우 전수 조사를 통해 가장 가까운 쌍을 찾는다.
        if (Px.size() <= 3) {
            return bruteForce(Px);
        }

        int mid = Px.size() / 2;
        // Px의 왼쪽 절반과 오른쪽 절반
        List<Point> Lx = Px.subList(0, mid);
        List<Point> Rx = Px.subList(mid, Px.size());

        List<Point> Ly = new ArrayList<>();
        List<Point> Ry = new ArrayList<>();

        // Px의 중간 값과 리스트 Py를 이용하여 y축 기준으로 정렬된 Px의 왼쪽 절반, 오른쪽 절반을 구한다.
        for (Point point : Py) {
            if (point.getX() < Px.get(mid).getX()) Ly.add(point);
            else Ry.add(point);
        }

        // 왼쪽과 오른쪽에서 가장 가까운 쌍의 거리를 구한다. 
        double dist = min(closestPair(Lx, Ly), closestPair(Rx, Ry));
        return closestSplitPair(Px, Py, dist);
    }

    public static double closestSplitPair(List<Point> Px, List<Point> Py, double dist) {
        int midX = Px.get(Px.size() / 2).getX();

        /*
        * 왼쪽과 오른쪽에서 구한 가장 가까운 쌍의 거리를 이용
        * 중간값 기준으로 거리를 +- 값보다 작은 점만 취급*/
        List<Point> band = new ArrayList<>();
        for (Point point : Py) {
            if (abs(point.getX() - midX) < dist) band.add(point);
        }

        /*
        * 취급한 점끼리의 거리를 구하여 가장 가까운 쌍의 거리를 구한다. */
        double result = dist;
        for (int i = 0; i < band.size() - 1; i++) {
            for (int j = 1; j < min(7, band.size() - i); i++) {
                result = min(result, calcDist(band.get(i), band.get(i + j)));
            }
        }

        return result;
    }

    // 전수 조사를 통해 가장 가까운 쌍의 거리를 찾는 함수
    public static double bruteForce(List<Point> A) {
        double min = Double.MAX_VALUE;

        for (int i = 0; i < A.size() - 1; i++) {
            for (int j = i + 1; j < A.size(); j++) {
                min = min(min, calcDist(A.get(i), A.get(j)));
            }
        }

        return min;
    }

    // 두 점의 거리를 구해주는 함수
    public static double calcDist(Point P, Point Q) {
        return sqrt((P.x - Q.x) * (P.x - Q.x) + (P.y - Q.y) * (P.y - Q.y));
    }

    // 점의 좌표를 관리하는 클래스
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