package algorithm.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class FindShortPathBFS {
    static int N;
    static int M;
    static boolean[] visited;
    static int[] dist;
    static ArrayList<ArrayList<Integer>> map = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        visited = new boolean[N + 1];
        dist = new int[N + 1];
        for (int i = 0; i <= N; i++) map.add(new ArrayList<>());

        StringTokenizer st;
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            map.get(x).add(y);
            map.get(y).add(x);
        }

        Arrays.fill(dist, Integer.MAX_VALUE);
        BFS(1);
    }

    public static void BFS(int startPoint) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(startPoint, 0));
        visited[startPoint] = true;
        dist[startPoint] = 0;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            System.out.println(cur.index + " " + cur.weight);

            for (int next : map.get(cur.index)) {
                if (!visited[next]) {
                    visited[next] = true;
                    dist[next] = cur.weight + 1;
                    queue.add(new Node(next, dist[next]));
                }
            }
        }
    }

    public static class Node {
        int index;
        int weight;

        public Node(int index, int weight) {
            this.index = index;
            this.weight = weight;
        }
    }
}
