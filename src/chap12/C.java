package chap12;

import java.io.*;
import java.util.StringTokenizer;

public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int W = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[] binaryTree = new int[M + 1];

            st = new StringTokenizer(br.readLine());
            for (int  i = 1; i <= M; i++) binaryTree[i] = Integer.parseInt(st.nextToken());

            System.out.println(solve(W, binaryTree, 1, 0));
        }

        br.close();
    }

    public static int solve(int W, int[] binaryTree, int startPoint, int sum) {
        if (binaryTree[startPoint] == 0) return 0;

        sum += binaryTree[startPoint];
        int left = startPoint * 2;
        int right = startPoint * 2 + 1;

        if (left >= binaryTree.length && right >= binaryTree.length
                || binaryTree[left] == 0 && binaryTree[right] == 0) {
            if (sum == W) return 1;
            else return 0;
        }

        int result = 0;

        if (binaryTree[left] != 0)
            result += solve(W, binaryTree, left, sum);
        if (right < binaryTree.length && binaryTree[right] != 0)
            result += solve(W, binaryTree, right, sum);

        return result;
    }

}