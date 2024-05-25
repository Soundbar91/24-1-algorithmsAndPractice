package chap11;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int W = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int[] nums = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();

            System.out.println(solve(W, N, nums));
        }

        br.close();
    }

    /*
    * 점화식 : dp[i][sum] = dp[i - 1][sum - nums[i - 1]] + dp[i - 1][sum + nums[i - 1]]
    * 모든 합의 범위가 1000 -> 차의 범위를 -1000으로 잡고 모든 값에 1000씩 더해서 수행*/
    public static int solve(int W, int N, int[] nums) {
        int offset = 1000;
        int[][] dp = new int[N + 1][2 * offset + 1];
        dp[0][offset] = 1;

        for (int i = 0; i < N; i++) {
            for (int j = -1000; j <= 1000; j++) {
                int temp = j + offset;
                if (dp[i][temp] != 0) {
                    dp[i + 1][temp + nums[i]] += dp[i][temp];
                    dp[i + 1][temp - nums[i]] += dp[i][temp];
                }
            }
        }

        return dp[N][W + offset];
    }
}