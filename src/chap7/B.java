package chap7;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            int S = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[] dest = new int[K];
            for (int i = 0; i < K; i++) dest[i] = Integer.parseInt(st.nextToken());

            int[] dist = new int[N];
            boolean[] visited = new boolean[N];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[S] = 0;

            ArrayList<ArrayList<Node>> map = new ArrayList<>();
            for (int i = 0; i < N; i++) map.add(new ArrayList<>());

            st = new StringTokenizer(br.readLine());

            while (E-- > 0) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                map.get(a).add(new Node(b, c));
            }

            solve(map, S, dist, dest, visited);
        }

        br.close();
    }

    public static void solve(ArrayList<ArrayList<Node>> map, int startPoint, int[] dist, int[] dest, boolean[] visited) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(startPoint, 0));

        while(!queue.isEmpty()) {
            Node node = queue.poll();

            visited[node.node] = true;

            for (Node nextNode: map.get(node.node)) {
                if (visited[nextNode.node]) continue;
                if (dist[nextNode.node] > nextNode.weight + dist[node.node]) {
                    dist[nextNode.node] = dist[node.node] + nextNode.weight;
                    queue.add(new Node(nextNode.node, dist[nextNode.node]));
                }
            }
        }

        print(dist, dest);
    }

    public static void print(int[] dist, int[] dest) {
        for (int i : dest) {
            System.out.print(dist[i] == Integer.MAX_VALUE ? -1 + " " : dist[i] + " ");
        }
        System.out.println();
    }

    static class Node implements Comparable<Node> {
        int node, weight;
        public Node (int index, int dist) {
            this.node = index;
            this.weight = dist;
        }
        @Override public int compareTo(Node n) {
            return weight - n.weight;
        }
    }
}