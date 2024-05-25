package chap11;

import java.io.*;
import java.util.StringTokenizer;

public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int G = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            String X = st.nextToken();
            String Y = st.nextToken();

            System.out.println(solve(G, M, X, Y));
        }

        br.close();
    }

    public static int solve(int G, int M, String X, String Y) {
        int[][] dp = new int[X.length() + 1][Y.length() + 1];

        for (int i = 1; i <= X.length(); i++) dp[i][0] = i * G;
        for (int j = 1; j <= Y.length(); j++) dp[0][j] = j * G;

        for (int i = 1; i <= X.length(); i++) {
            for (int j = 1; j <= Y.length(); j++) {
                int p = X.charAt(i - 1) == Y.charAt(j - 1) ? 0 : M;
                dp[i][j] = Math.min(p + dp[i - 1][j - 1], Math.min(G + dp[i - 1][j], G + dp[i][j - 1]));
            }
        }

        return dp[X.length()][Y.length()];
    }
}