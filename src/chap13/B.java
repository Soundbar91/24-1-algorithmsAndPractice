package chap13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());

            Item[] items = new Item[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int v = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                items[i] = new Item(v, w);
            }

            System.out.println(solve(items, W));
        }

        br.close();
    }

    public static int solve(Item[] items, int W) {
        Arrays.sort(items, (a, b) -> b.value * a.weight - a.value * b.weight);

        PriorityQueue<Node> queue = new PriorityQueue<>();
        Node u, v = new Node(items.length);

        v.bound = computeBound(items, W, v);
        queue.add(v);

        int maxProfit = 0;

        while (!queue.isEmpty()) {
            v = queue.poll();

            if (v.bound > maxProfit) {
                u = new Node(items.length);

                u.level = v.level + 1;
                u.weight = v.weight + items[u.level].weight;
                u.profit = v.profit + items[u.level].value;

                if (u.weight <= W && u.profit > maxProfit) {
                    maxProfit = u.profit;
                }

                u.bound = computeBound(items, W, u);
                if (u.bound > maxProfit) {
                    queue.add(u);
                }

                u = new Node(items.length);
                u.level = v.level + 1;
                u.weight = v.weight;
                u.profit = v.profit;

                u.bound = computeBound(items, W, u);
                if (u.bound > maxProfit) {
                    queue.add(u);
                }
            }
        }

        return maxProfit;
    }

    public static int computeBound(Item[] items, int W, Node node) {
        if (node.weight > W) return 0;

        int profitBound = node.profit;
        int j = node.level + 1;
        int totalWeight = node.weight;

        while (j < items.length && totalWeight + items[j].weight <= W) {
            totalWeight += items[j].weight;
            profitBound += items[j].value;
            j++;
        }

        if (j < items.length) {
            profitBound += (W - totalWeight) * items[j].value / items[j].weight;
        }

        return profitBound;
    }

    public static class Item {
        int value, weight;

        public Item(int value, int weight) {
            this.value = value;
            this.weight = weight;
        }
    }

    public static class Node implements Comparable<Node> {
        int level, profit, weight, bound;
        boolean[] include;

        public Node(int n) {
            this.level = -1;
            this.profit = 0;
            this.weight = 0;
            this.bound = 0;
            this.include = new boolean[n];
        }

        @Override
        public int compareTo(Node other) {
            return other.bound - bound;
        }
    }
}

