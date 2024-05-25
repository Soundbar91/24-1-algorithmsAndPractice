package chap10;

import java.io.*;
import java.util.StringTokenizer;

public class D {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int W = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());

            Item[] items = new Item[N + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++)
                items[i] = new Item(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

            System.out.println(solve(W, N, items));
        }

        br.close();
    }

    public static int solve(int W, int N, Item[] items) {
        int[] dp = new int[W + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = W; j >= items[i - 1].weight; j--) {
                dp[j] = Math.max(dp[j], dp[j - items[i - 1].weight] + items[i - 1].value);
            }
        }

        return dp[W];
    }

    public static class Item {
        int value;
        int weight;

        public Item(int value, int weight) {
            this.value = value;
            this.weight = weight;
        }
    }
}