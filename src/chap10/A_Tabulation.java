package chap10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class A_Tabulation {
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

            System.out.println(solve(M, nums));
        }

        br.close();
    }

    public static boolean solve(int M, int[] nums) {
        boolean[] visited = new boolean[M + 1];
        visited[0] = true;

        for (int i = 0; i < M; i++) {
            if (visited[i]) {
                for (int num : nums) {
                    if (i + num <= M) visited[i + num] = true;
                }
            }
        }

        return visited[M];
    }
}
