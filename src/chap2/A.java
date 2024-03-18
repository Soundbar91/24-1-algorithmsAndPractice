package chap2;

import java.io.*;
import java.util.StringTokenizer;

public class A {
    static int H;
    static int W;
    static char[][] map;
    static int[][] dx = {
            {0, 1, 0},
            {0, 1, 1},
            {0, 0, 1},
            {0, 1, 1}
    };

    static int[][] dy = {
            {0, 0, 1},
            {0, 0, 1},
            {0, 1, 1},
            {0, 0, 1}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
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
                continue;
            }
            else {
                for (int i = 0; i < H; i++) {
                    for (int j = 0; j < W; j++) {
                        if (map[i][j] == '.') solve(i, j, 0, cnt);
                    }
                }
            }
        }
    }

    public static void solve(int x, int y, int depth, int cnt) {
        if (cnt / 3 == depth) {

        }
        else {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 3; j++) {
                    int ux = x + dx[i][j];
                    int uy = y + dx[i][j];

                    if (valid(ux, uy) || map[uy][ux] == '#') continue;
                }

            }
        }
    }

    public static boolean valid(int x, int y) {
        return (x >= 0 && x < W) && (y >= 0 && y < H);
    }
}