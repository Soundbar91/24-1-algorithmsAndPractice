package chap10;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());

            int[] nums = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();

            List<Integer> solve = solve(M, N, nums);

            if (solve == null) System.out.println(-1);
            else {
                System.out.print(solve.size() + " ");
                solve.forEach(x -> System.out.print(x + " "));
                System.out.println();
            }
        }

        br.close();
    }

    public static List<Integer> solve(int M, int N, int[] nums) {
        List<Integer>[] dp = new List[M + 1];
        dp[0] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            if (dp[i] != null) {
                for (int j = 0; j < N; j++) {
                    if (i + nums[j] <= M && dp[i + nums[j]] == null) {
                        dp[i + nums[j]] = new ArrayList<>(dp[i]);
                        dp[i + nums[j]].add(nums[j]);
                    }
                }
            }
        }

        return dp[M];
    }
}