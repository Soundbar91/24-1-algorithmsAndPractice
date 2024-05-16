package chap10;

import java.io.*;
import java.util.Arrays;

public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] nums = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int sum = Arrays.stream(nums).sum();
            if (sum % 2 != 0) {
                System.out.println("false");
                continue;
            }

            System.out.println(solve(nums, sum));
        }

        br.close();
    }

    public static boolean solve(int[] nums, int sum) {
        int target = sum / 2;
        boolean[][] dp = new boolean[nums.length + 1][target + 1];

        dp[0][0] = true;

        for (int i = 1; i <= nums.length; i++) {
            int num = nums[i - 1];
            for (int j = 0; j <= target; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= num) {
                    dp[i][j] = dp[i][j] || dp[i - 1][j - num];
                }
            }
        }

        return dp[nums.length][target];
    }
}
