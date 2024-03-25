package chap2;

import java.io.*;
import java.util.StringTokenizer;

public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int[][] dx = {
                {0, 0, -1},
                {0, 0, 1},
                {0, 1, 1},
                {0, 1, 0}
        };
        int[][] dy = {
                {0, 1, 1},
                {0, 1, 1},
                {0, 0, 1},
                {0, 0, 1}
        };

        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int H = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());
            char[][] map = new char[H][W];
            int cnt = 0;

            for (int i = 0; i < H; i++) {
                String str = br.readLine();
                for (int j = 0; j < W; j++) {
                    map[i][j] = str.charAt(j);
                    if (map[i][j] == '.') cnt++;
                }
            }

            if (cnt % 3 != 0) {
                sb.append(0).append('\n');
            }
            else {
                sb.append(solve(cnt, H, W, map, dx, dy)).append('\n');
            }
        }

        System.out.print(sb);
        br.close();
    }

    public static int solve(int cnt, int H, int W, char[][] map, int[][] dx, int[][] dy) {
        int[] point = find(H, W, map);
        int result = 0;

        if (point == null) {
            return 1;
        }
        else {
            int x = point[0];
            int y = point[1];

            for (int n = 0; n < 4; n++) {
                if (check(x, y, n, H, W, map, dx, dy)) {
                    flip(x, y, n, '#', map, dx, dy);
                    result += solve(cnt - 3, H, W, map, dx, dy);
                    flip(x, y, n, '.', map, dx, dy);
                }
            }
        }

        return result;
    }

    // 맵에서 '.'의 좌표를 찾는 함수
    public static int[] find(int H, int W, char[][] map) {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (map[i][j] == '.') return new int[]{j, i};
            }
        }

        return null;
    }

    // 해당 좌표에서 블럭을 넣을 수 있는지 확인하는 함수
    public static boolean check(int x, int y, int n, int H, int W, char[][] map, int[][] dx, int[][] dy) {
        for (int i = 0; i < 3; i++) {
            int ux = x + dx[n][i];
            int uy = y + dy[n][i];

            if (!valid(ux, uy, H, W) || map[uy][ux] == '#') return false;
        }
        return true;
    }

    // 좌표의 범위가 맵 밖으로 나가는지 확인하는 함수
    public static boolean valid(int x, int y, int H, int W) {
        return (x >= 0 && x < W) && (y >= 0 && y < H);
    }

    // 맵 값을 바꾸는 함수
    public static void flip(int x, int y, int n, char ch, char[][] map, int[][] dx, int[][] dy) {
        for (int i = 0; i < 3; i++) {
            int ux = x + dx[n][i];
            int uy = y + dy[n][i];

            map[uy][ux] = ch;
        }
    }
}