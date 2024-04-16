package algorithm.bruteForce;

import java.util.ArrayList;
import java.util.List;

public class CombinationB {

    public static List<List<Integer>> combination(int n, int m) {
        List<List<Integer>> ret = new ArrayList<>();
        List<Integer> C = new ArrayList<>();
        combination(ret, n, m, C);
        return ret;
    }

    private static void combination(List<List<Integer>> ret, int n, int m, List<Integer> C) {
        if (m == 0) {
            ret.add(new ArrayList<>(C));
            return;
        }
        int next = C.isEmpty() ? 1 : C.get(C.size() - 1) + 1;
        for (int i = next; i <= n; i++) {
            C.add(i);
            combination(ret, n, m - 1, C);
            C.remove(C.size() - 1);
        }
    }

    public static void main(String[] args) {
        int n = 5;
        int m = 3;
        List<List<Integer>> combinations = combination(n, m);
        for (List<Integer> combination : combinations) {
            for (int num : combination) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}
