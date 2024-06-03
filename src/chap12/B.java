package chap12;

import java.io.*;
import java.util.*;

public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());

            Item[] items = new Item[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int v = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                items[i] = new Item(w, v);
            }

            Arrays.sort(items, (a, b) -> Double.compare(b.score, a.score));

            boolean[] included = new boolean[N];
            Result ret = new Result(N);
            knapsack(items, included, W, 0, 0, 0, ret);

            System.out.println(ret.max);
        }

        br.close();
    }

    public static int computeBound(Item[] items, int W, int index, int currProfit, int currWeight) {
        int bound = currProfit;
        int totalWeight = currWeight;
        int i = index;

        while (i < items.length && totalWeight + items[i].weight <= W) {
            totalWeight += items[i].weight;
            bound += items[i].profit;
            i++;
        }

        if (i < items.length) {
            bound += (int) ((W - totalWeight) * items[i].score);
        }

        return bound;
    }

    public static boolean promising(Item[] items, int W, int index, int currProfit, int currWeight, Result ret) {
        return currWeight < W && computeBound(items, W, index, currProfit, currWeight) > ret.max;
    }

    public static void knapsack(Item[] items, boolean[] included, int W, int index, int currProfit, int currWeight, Result ret) {
        if (currWeight <= W && currProfit > ret.max) {
            ret.max = currProfit;
            ret.sol = included.clone();
        }

        if (index < items.length && promising(items, W, index, currProfit, currWeight, ret)) {
            included[index] = true;
            knapsack(items, included, W, index + 1, currProfit + items[index].profit, currWeight + items[index].weight, ret);
            included[index] = false;
            knapsack(items, included, W, index + 1, currProfit, currWeight, ret);
        }
    }

    public static class Item {
        int weight;
        int profit;
        double score;

        public Item(int weight, int profit) {
            this.weight = weight;
            this.profit = profit;
            this.score = (double) profit / weight;
        }
    }

    public static class Result {
        int max;
        boolean[] sol;

        public Result(int n) {
            this.max = 0;
            this.sol = new boolean[n];
        }
    }
}
