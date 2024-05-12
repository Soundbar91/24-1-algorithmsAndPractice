package chap9;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());

            int[] parents = new int[N + 1];
            for (int i = 1; i <= N; i++) parents[i] = i;
            List<Line> lines = new ArrayList<>();

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int to = Integer.parseInt(st.nextToken());
                int from = Integer.parseInt(st.nextToken());

                lines.add(new Line(from, to));
            }

            Line solve = solve(lines, parents);
            System.out.println(solve.to + " " + solve.from);
        }

        br.close();
    }

    public static Line solve(List<Line> lines, int[] parents) {
        Line result = null;

        // 부모 노드가 같은 경우에 주기가 생기므로 트리의 조건에 부합하지 않는다.
        for (Line line : lines) {
            if (find(line.from, parents) == find(line.to, parents)) result = line;
            else union(line.from, line.to, parents);
        }

        return result;
    }

    public static int find(int x, int[] parents) {
        if (parents[x] == x) return x;
        else return find(parents[x], parents);
    }

    public static void union(int x, int y, int[] parents) {
        int rx = find(x, parents);
        int ry = find(y, parents);

        if (rx != ry) parents[ry] = rx;
    }

    public static class Line {
        int from;
        int to;

        public Line(int to, int from) {
            this.from = to;
            this.to = from;
        }
    }
}