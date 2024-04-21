package algorithm.bruteForce;

import java.util.ArrayList;
import java.util.List;

public class BestSumLongest {
    public static void main(String[] args) {
        int[] A = {3, 4, 5, 7};
        int target = 7;

        List<Integer> result = bestSumLongest(target, A);
        System.out.println("가장 긴 해: " + result);
    }

    public static List<Integer> bestSumLongest(int m, int[] A) {
        if (m < 0) return null;
        if (m == 0) return new ArrayList<>();

        List<Integer> best = null;

        for (int x : A) {
            List<Integer> list = bestSumLongest(m - x, A);
            if (list != null) {
                if (best == null || list.size() > best.size() + 1) {
                    list.add(x);
                    best = list;
                }
            }
        }

        return best;
    }
}
