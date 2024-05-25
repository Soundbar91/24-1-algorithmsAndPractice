package chap9;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
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

            int[] size = new int[parents.length];
            Arrays.fill(size, 1);

            List<Line> lines = new ArrayList<>();

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int to = Integer.parseInt(st.nextToken());
                int from = Integer.parseInt(st.nextToken());

                lines.add(new Line(from, to));
            }

            Line solve = solve(lines, parents, size);
            System.out.println(solve.to + " " + solve.from);
        }

        br.close();
    }

    public static Line solve(List<Line> lines, int[] parents, int[] size) {
        for (Line line : lines) if (!union(line.to, line.from, parents, size)) return line;
        return new Line(0, 0);
    }

    public static int find(int x, int[] parents) {
        if (parents[x] == x) return x;
        else return find(parents[x], parents);
    }

    public static boolean union(int x, int y, int[] parents, int[] size) {
        int rx = find(x, parents);
        int ry = find(y, parents);

        if (rx != ry) {
            if (size[rx] >= size[ry]) {
                parents[ry] = rx;
                size[rx] += size[ry];
                size[y] = 0;
            }
            else {
                parents[ry] = rx;
                size[rx] += size[y];
                size[y] = 0;
            }
            return true;
        }
        return false;
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