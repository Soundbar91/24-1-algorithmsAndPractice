package chap9;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class A_Kruskal {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            ArrayList<Node> list = new ArrayList<>();

            int N = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            int[] parents = new int[N];
            for (int i = 0; i < N; i++) parents[i] = i;

            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < E; i++) {
                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());
                int C = Integer.parseInt(st.nextToken());

                list.add(new Node(A, B, C));
            }

            System.out.println(solve(list, parents, E));
        }

        br.close();
    }

    public static long solve(ArrayList<Node> list, int[] parents, int E) {
        Collections.sort(list);
        long result = 0;
        int cnt = 0;

        for (Node node : list) {
            if (union(node.from, node.to, parents)) {
                result += node.wight;
                if (cnt++ == E - 1) break;
            }
        }

        return result;
    }

    public static int find(int x, int[] parents) {
        if (parents[x] == x) return x;
        else return find(parents[x], parents);
    }

    public static boolean union(int x, int y, int[] parents) {
        int rx = find(x, parents);
        int ry = find(y, parents);

        if (rx == ry) return false;
        else {
            if (rx < ry) parents[ry] = rx;
            else parents[rx] = ry;
        }
        return true;
    }

    public static class Node implements Comparable<Node> {
        int from;
        int to;
        int wight;

        public Node(int to, int from, int wight) {
            this.from = to;
            this.to = from;
            this.wight = wight;
        }

        @Override
        public int compareTo(Node o) {
            return wight - o.wight;
        }
    }
}