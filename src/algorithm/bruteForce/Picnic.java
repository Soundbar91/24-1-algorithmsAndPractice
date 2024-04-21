package algorithm.bruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Picnic {
    static int N;
    static boolean[][] map;
    static boolean[] visit;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            map = new boolean[N][N];
            visit = new boolean[N];
            count = 0;

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                map[a][b] = map[b][a] = true; // 서로 친구인 학생 표시
            }

            search();
            System.out.println(count);
        }
    }

    static void search() {
        int firstFree = -1;
        for (int i = 0; i < N; ++i) {
            if (!visit[i]) {
                firstFree = i;
                break;
            }
        }

        if (firstFree == -1) {
            count++;
            return;
        }

        for (int pairWith = firstFree + 1; pairWith < N; ++pairWith) {
            if (!visit[pairWith] && map[firstFree][pairWith]) {
                visit[firstFree] = visit[pairWith] = true;
                search();
                visit[firstFree] = visit[pairWith] = false;
            }
        }
    }
}
