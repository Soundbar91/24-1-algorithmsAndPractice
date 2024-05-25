package chap11;

import java.util.*;
import java.io.*;
import java.util.stream.IntStream;

public class C {
    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            int[][] dist = new int[N][N];
            for (int i = 0; i < N; i++) {
                Arrays.fill(dist[i], INF);
                dist[i][i] = 0;
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < E; i++) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                dist[from][to] = weight;
            }

            solve(N, dist);
        }

        br.close();
    }

    public static void solve(int N, int[][] dist) {
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (dist[i][k] == INF || dist[k][j] == INF) continue;
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        if (checkNegativeCycle(N, dist)) System.out.println("-1");
        else printResult(N, dist);
    }

    public static boolean checkNegativeCycle(int N, int[][] dist) {
        return IntStream.range(0, N).anyMatch(i -> dist[i][i] < 0);
    }

    public static void printResult(int N, int[][] dist) {
        int min = -INF;
        int start = INF;
        int end = INF;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (dist[i][j] != INF) {
                    if (dist[i][j] > min) {
                        min = dist[i][j];
                        start = i;
                        end = j;
                    }
                    else if (dist[i][j] == min) {
                        if (i < start) {
                            start = i;
                            end = j;
                        }
                        else if (i == start && j < end) {
                            end = j;
                        }
                    }
                }
            }
        }

        System.out.println(start + " " + end + " " + min);
    }
}
