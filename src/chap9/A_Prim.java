package chap9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class A_Prim {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            List<List<Edge>> map = new ArrayList<>();
            for (int i = 0; i < N; i++) map.add(new ArrayList<>());

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < E; i++) {
                int to = Integer.parseInt(st.nextToken());
                int from = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                map.get(to).add(new Edge(from, weight));
                map.get(from).add(new Edge(to, weight));
            }

            System.out.println(solve(map, 0));
        }

        br.close();
    }

    public static long solve(List<List<Edge>> map, int start) {
        Queue<Edge> queue = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        boolean[] visited = new boolean[map.size()];
        long[] score = new long[map.size()];

        visited[start] = true;
        for (Edge edge : map.get(start)) queue.offer(edge);

        while (!queue.isEmpty()) {
            Edge cur = queue.poll();
            int u = cur.v;
            int weight = cur.weight;

            if (visited[u]) continue;

            visited[u] = true;
            score[u] += weight;

            for (Edge next : map.get(u)) {
                if (!visited[next.v]) {
                    queue.offer(next);
                }
            }
        }

        return Arrays.stream(score).sum();
    }

    static class Edge {
        int v;
        int weight;

        public Edge(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }
    }
}