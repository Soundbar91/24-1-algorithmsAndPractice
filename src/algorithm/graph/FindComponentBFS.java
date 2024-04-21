package algorithm.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class FindComponentBFS {
    static int N;
    static int E;
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            visited = new boolean[N];
            map = new ArrayList<>();
            for (int i = 0; i < N; i++) map.add(new ArrayList<>());

            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                map.get(x).add(y);
                map.get(y).add(x);
            }

            int count = 0;
            int max = Integer.MIN_VALUE;

            for (int i = 0; i < N; i++) {
                if (!visited[i]) {
                    max = Math.max(BFS(i), max);
                    count++;
                }
            }

            System.out.println(count + " " + max);
        }
    }

    public static int BFS(int startPoint) {
        Queue<Integer> queue = new LinkedList<>();
        visited[startPoint] = true;
        queue.add(startPoint);
        int cnt = 0;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            cnt++;

            for (int next : map.get(cur)) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }

        return cnt;
    }
}
