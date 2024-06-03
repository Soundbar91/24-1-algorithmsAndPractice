package chap12;

import java.io.*;
import java.util.Arrays;

public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            boolean foundSolution = solve(N, 0, new int[N]);
            if (!foundSolution) System.out.println();
        }

        br.close();
    }

    public static boolean solve(int N, int row, int[] cols) {
        if (row == N) {
            printMap(N, cols);
            return true;
        }
        boolean found = false;
        for (int i = 0; i < N; i++) {
            cols[row] = i;
            if (promising(row, cols)) {
                found = solve(N, row + 1, cols) || found;
            }
        }
        return found;
    }

    public static boolean promising(int row, int[] cols) {
        for (int i = 0; i < row; i++) {
            if (cols[row] == cols[i] || Math.abs(cols[i] - cols[row]) == Math.abs(row - i))
                return false;
        }
        return true;
    }

    public static void printMap(int N, int[] cols) {
        char[][] map = new char[N][N];
        for (int i = 0; i < N; i++) Arrays.fill(map[i], 'X');

        for (int i = 0; i < N; i++) {
            map[i][cols[i]] = 'Q';
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }
}