package chap7;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            ArrayList<ArrayList<Integer>> map = new ArrayList<>();
            for (int i = 0; i < N; i++) map.add(new ArrayList<>());

            st = new StringTokenizer(br.readLine());

            while (E-- > 0) {
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                map.get(x).add(y);
            }

            boolean flag = false;

            for (int i = 0; i < N; i++) {
                boolean[] visited = new boolean[N];
                if (DFS(map, visited, i)) {
                    flag = true;
                    break;
                }
            }

            System.out.println(flag ? "true" : "false");
        }

        br.close();
    }

    public static boolean DFS(ArrayList<ArrayList<Integer>> map, boolean[] visited, int startPoint) {
        visited[startPoint] = true;

        for (int nextPoint : map.get(startPoint)) {
            if (!visited[nextPoint]) {
                if (DFS(map, visited, nextPoint)) return true;
                visited[nextPoint] = false;
            }
            else return true;
        }
        return false;
    }
}