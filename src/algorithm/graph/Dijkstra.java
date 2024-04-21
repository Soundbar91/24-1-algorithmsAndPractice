package algorithm.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Dijkstra {
    static int N;
    static int M;
    static int K;
    static int X;
    static int[] dist;
    static boolean[] visited;
    static ArrayList<ArrayList<Node>> map = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        dist = new int[N + 1];
        visited = new boolean[N + 1];

        for (int i = 0; i <= N; i++) map.add(new ArrayList<>());

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map.get(x).add(new Node(y, 1));
        }

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[X] = 0;

        solve();
    }

    public static void solve() {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(X, 0));

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            visited[node.node] = true;

            for (Node nextNode : map.get(node.node)) {
                if (visited[nextNode.node]) continue;
                if (dist[nextNode.node] > dist[node.node] + nextNode.weight) {
                    dist[nextNode.node] = dist[node.node] + nextNode.weight;
                    queue.add(new Node(nextNode.node, dist[nextNode.node]));
                }
            }
        }
    }

    public static void print() {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            if (dist[i] == K) {
                result.add(i);
            }
        }

        if (result.isEmpty()) System.out.println(-1);
        else for (int city : result) System.out.println(city);
    }

    public static class Node implements Comparable<Node> {
        int node;
        int weight;

        public Node(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node n) {
            return weight - n.weight;
        }
    }
}
