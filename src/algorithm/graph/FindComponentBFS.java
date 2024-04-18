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
    static int M;
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> componentPath = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> map = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        visited = new boolean[N + 1];
        for (int i = 0; i <= N; i++) map.add(new ArrayList<>());

        StringTokenizer st;
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            map.get(x).add(y);
            map.get(y).add(x);
        }

        int count = 0;
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                componentPath.add(BFS(i));
                count++;
            }
        }

        System.out.println(count);
        for (ArrayList<Integer> path : componentPath) System.out.println(path.toString());
    }

    public static ArrayList<Integer> BFS(int startPoint) {
        Queue<Integer> queue = new LinkedList<>();
        ArrayList<Integer> path = new ArrayList<>();
        visited[startPoint] = true;
        queue.add(startPoint);
        path.add(startPoint);

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next : map.get(cur)) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                    path.add(next);
                }
            }
        }

        return path;
    }
}
