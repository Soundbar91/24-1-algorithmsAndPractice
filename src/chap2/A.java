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

        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
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
        br.close();
    }

    public static void solve(int cnt) {
        int[] point = find();

        if (point == null) {
            result++;
        }
        else {
            int x = point[0];
            int y = point[1];

            for (int n = 0; n < 4; n++) {
                if (check(x, y, n)) {
                    flip(x, y, n, '#');
                    solve(cnt - 3);
                    flip(x, y, n, '.');
                }
            }
        }
    }

    // 맵에서 '.'의 좌표를 찾는 함수
    public static int[] find() {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (map[i][j] == '.') return new int[]{j, i};
            }
        }

        return null;
    }

    // 해당 좌표에서 블럭을 넣을 수 있는지 확인하는 함수
    public static boolean check(int x, int y, int n) {
        for (int i = 0; i < 3; i++) {
            int ux = x + dx[n][i];
            int uy = y + dy[n][i];

            if (!valid(ux, uy) || map[uy][ux] == '#') return false;
        }
        return true;
    }

    // 좌표의 범위가 맵 밖으로 나가는지 확인하는 함수
    public static boolean valid(int x, int y) {
        return (x >= 0 && x < W) && (y >= 0 && y < H);
    }

    // 맵 값을 바꾸는 함수
    public static void flip(int x, int y, int n, char ch) {
        for (int i = 0; i < 3; i++) {
            int ux = x + dx[n][i];
            int uy = y + dy[n][i];

            map[uy][ux] = ch;
        }
    }
}