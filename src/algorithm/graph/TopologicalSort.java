package algorithm.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TopologicalSort {
    static int N;
    static int M;
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> map = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        visited = new boolean[N];
        for (int i = 0; i < N; i++) map.add(new ArrayList<>());

        StringTokenizer st;
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            map.get(x).add(y);
        }

        System.out.print(Arrays.toString(TopologicalSort()));
    }

    public static int[] TopologicalSort() {
        List<Integer> order = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                TopologicalSort(order, i);
            }
        }

        int[] label = new int[N];
        int j = N;

        for (int i = 0; i < N; i++) label[order.get(i)] = j--;
        return label;
    }

    public static void TopologicalSort(List<Integer> order, int cur) {
        visited[cur] = true;

        for (int next : map.get(cur)) {
            if (!visited[next]) {
                TopologicalSort(order, next);
            }
        }

        order.add(cur);
    }
}
