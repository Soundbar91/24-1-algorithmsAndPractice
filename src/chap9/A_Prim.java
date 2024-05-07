package chap9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class A_Prim {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            List<Node>[] map = new List[N];
            for (int i = 0; i < N; i++) map[i] = new ArrayList<>();

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < E; i++) {
                int to = Integer.parseInt(st.nextToken());
                int from = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                map[to].add(new Node(from, weight));
                map[from].add(new Node(to, weight));
            }

            System.out.println(solve(map, N, E));
        }

        br.close();
    }

    public static int solve(List<Node>[] map, int N, int E) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[N];
        pq.add(new Node(0, 0));
        int result = 0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if (visited[node.to]) continue;
            visited[node.to] = true;
            result += node.weight;

            for (Node next : map[node.to]) {
                if (!visited[next.to]) pq.add(next);
            }
        }

        return result;
    }

    public static class Node implements Comparable<Node> {
        int to;
        int weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return weight - o.weight;
        }
    }
}