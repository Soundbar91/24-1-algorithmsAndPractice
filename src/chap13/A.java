package chap13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class A {
    static int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[][] graph = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    graph[i][j] = Integer.parseInt(st.nextToken());
                    if (graph[i][j] == -1) {
                        graph[i][j] = INF;
                    }
                }
            }

            System.out.println(solve(graph));
        }

        br.close();
    }

    public static int solve(int[][] graph) {
        int n = graph.length;
        int minLength = INF;
        List<Integer> bestTour = null;
        PriorityQueue<Node> queue = new PriorityQueue<>();

        Node root = new Node(n);
        root.bound = computeBound(graph, root);
        queue.add(root);

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.bound >= minLength) continue;

            for (int i = 1; i < n; i++) {
                if (node.included[i]) continue;
                if (graph[node.tour.get(node.level)][i] == -1) continue;

                Node next = new Node(n);
                next.level = node.level + 1;
                next.tour = new ArrayList<>(node.tour);
                next.tour.add(i);
                System.arraycopy(node.included, 0, next.included, 0, n);
                next.included[i] = true;

                if (next.level == n - 1) {
                    next.tour.add(0);
                    int tourLength = 0;
                    for (int j = 1; j <= n; j++) {
                        tourLength += graph[next.tour.get(j - 1)][next.tour.get(j)];
                    }
                    if (tourLength < minLength) {
                        minLength = tourLength;
                        bestTour = next.tour;
                    }
                } else {
                    next.bound = computeBound(graph, next);
                    if (next.bound < minLength) {
                        queue.add(next);
                    }
                }
            }
        }

        return minLength;
    }

    public static int computeBound(int[][] graph, Node node) {
        int n = graph.length;
        int bound = 0;

        for (int i = 1; i <= node.level; i++) {
            bound += graph[node.tour.get(i - 1)][node.tour.get(i)];
        }

        int minEdge = Integer.MAX_VALUE;
        for (int w = 1; w < n; w++) {
            if (node.included[w]) continue;
            minEdge = Math.min(minEdge, graph[node.tour.get(node.level)][w]);
        }
        bound += minEdge;

        for (int v = 0; v < n; v++) {
            if (node.included[v]) continue;
            minEdge = INF;
            for (int w = 0; w < n; w++) {
                if (w != 0 && node.included[w]) continue;
                minEdge = Math.min(minEdge, graph[v][w]);
            }
            bound += minEdge;
        }

        return bound;
    }

    public static class Node implements Comparable<Node> {
        int level;
        int bound;
        boolean[] included;
        List<Integer> tour;

        public Node(int n) {
            this.level = 0;
            this.bound = 0;
            this.included = new boolean[n];
            this.tour = new ArrayList<>();
            this.tour.add(0);
        }

        @Override
        public int compareTo(Node other) {
            return bound - other.bound;
        }
    }
}
