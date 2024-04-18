package algorithm.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class FindTreeDiameter {
    static int V;
    static int tmpIndex = 1;
    static boolean[] visited;
    static ArrayList<ArrayList<int[]>> map = new ArrayList<>();
    static int result = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        V = Integer.parseInt(br.readLine());
        visited = new boolean[V + 1];

        for (int i = 0; i <= V; i++) map.add(new ArrayList<>());

        for (int i = 0; i < V; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());

            while (true) {
                int y = Integer.parseInt(st.nextToken());
                if (y == -1) break;
                int wight = Integer.parseInt(st.nextToken());

                map.get(x).add(new int[]{y, wight});
                map.get(y).add(new int[]{x, wight});
            }
        }

        dfs(1, 0);
        result = 0;
        Arrays.fill(visited, false);
        dfs(tmpIndex, 0);

        System.out.print(result);
    }

    public static void dfs(int startPoint, int weight) {
        visited[startPoint] = true;
        if (weight > result) {
            result = weight;
            tmpIndex = startPoint;
        }

        for (int[] node : map.get(startPoint)) {
            int nextPoint = node[0];
            int lineWight = node[1];

            if (!visited[nextPoint]) {
                dfs(nextPoint, weight + lineWight);
            }
        }
    }
}
