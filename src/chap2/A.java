package chap2;

import java.io.*;
import java.util.StringTokenizer;

public class A {
    static int H;
    static int W;
    static int result;
    static char[][] map;
    static int[][] dx = {
            {0, 0, -1},
            {0, 0, 1},
            {0, 1, 1},
            {0, 1, 0}
    };

    static int[][] dy = {
            {0, 1, 1},
            {0, 1, 1},
            {0, 0, 1},
            {0, 0, 1}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            map = new char[H][W];
            result = 0;
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
                solve(cnt);
                sb.append(result).append('\n');
            }
        }

        System.out.print(sb);
    }

    public static void solve(int cnt) {
        if (cnt == 0) {
            result++;
        }
        else {
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    if (map[i][j] == '.') {
                        for (int k = 0; k < 4; k++) {
                            if (check(j, i, k)) {
                                flip(j, i, k, '#');
                                solve(cnt - 3);
                                flip(j, i, k, '.');
                            }
                        }
                    }
                }
            }
        }
    }

    public static boolean check(int x, int y, int n) {
        for (int i = 0; i < 3; i++) {
            int ux = x + dx[n][i];
            int uy = y + dy[n][i];

            if (!valid(ux, uy) || map[uy][ux] == '#') return false;
        }
        return true;
    }

    public static boolean valid(int x, int y) {
        return (x >= 0 && x < W) && (y >= 0 && y < H);
    }

    public static void flip(int x, int y, int n, char ch) {
        for (int i = 0; i < 3; i++) {
            int ux = x + dx[n][i];
            int uy = y + dy[n][i];

            map[uy][ux] = ch;
        }
    }
}