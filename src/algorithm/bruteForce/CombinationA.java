package algorithm.bruteForce;

import java.util.ArrayList;
import java.util.List;

public class CombinationA {
    public static List<int[]> combination(int n, int m) {
        List<int[]> ret = new ArrayList<>();
        int[] C = new int[m];
        combination(ret, n, m, 0, 1, C);
        return ret;
    }

    private static void combination(List<int[]> ret, int n, int m, int size, int i, int[] C) {
        if (size == m) {
            ret.add(C.clone());
            return;
        }
        if (i >= n) return;

        C[size] = i;
        combination(ret, n, m, size + 1, i + 1, C); // i 포함
        combination(ret, n, m, size, i + 1, C);     // i 미포함
    }

    public static void main(String[] args) {
        int n = 5;
        int m = 3;
        List<int[]> combinations = combination(n, m);
        for (int[] combination : combinations) {
            for (int num : combination) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}