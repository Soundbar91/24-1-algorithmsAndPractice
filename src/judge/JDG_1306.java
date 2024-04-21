package judge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JDG_1306 {
    static int[][] land;
    static int[][] visit;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int h;
    static int w;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            land = new int[h][w];
            visit = new int[h][w];
            int ans = 0;

            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    land[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (visit[i][j] != 1 && land[i][j] == 1) {
                        dfs(i, j);
                        ans++;
                    }
                }
            }
            System.out.println(ans);
        }
    }

    static void dfs(int x, int y) {
        visit[x][y] = 1;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= h || ny >= w) continue;
            if (land[nx][ny] == 1 && visit[nx][ny] != 1)
                dfs(nx, ny);
        }
    }
}
