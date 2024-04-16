package algorithm;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class RSelect {
    public static void main(String[] args) {
        int[] numArr = new int[]{7, 14, 1, 8, 10, 12, 9};
        System.out.print(RSelect(numArr, 0, numArr.length - 1, (numArr.length + 1) / 2, 0));
        System.out.print(Arrays.toString(numArr));
    }

    public static int RSelect(int[] A, int lo, int hi, int i, int depth) {
        if (lo == hi) return A[lo];

        int pivotLoc = chooseRandomPivot(lo, hi);
        System.out.print("RandomPivotLoc = " + pivotLoc);
        swap(A, lo, pivotLoc);
        pivotLoc = partition(A, lo, hi);

        System.out.print(" afterPivotLoc = " + pivotLoc);
        System.out.print(" depth = " + depth);
        System.out.println(" Arrays = " + Arrays.toString(A));

        if (i == pivotLoc) return A[pivotLoc];
        else if (i < pivotLoc) return RSelect(A, lo, pivotLoc - 1, i, depth + 1);
        else return RSelect(A, pivotLoc + 1, hi, i, depth + 1);
    }

    public static int partition(int[] A, int lo, int hi) {
        int pivot = A[lo];
        int L = lo + 1;
        int R = hi;

        while(L <= R) {
            while (L <= R && A[L] <= pivot) ++L;
            while (L <= R && A[R] > pivot) --R;
            if (L < R) swap(A, L++, R--);
        }

        swap(A, lo, R);
        return R;
    }

    public static int chooseRandomPivot(int lo, int hi) {
        return ThreadLocalRandom.current().nextInt(lo, hi + 1);
    }

    public static void swap(int[] A, int L, int R) {
        int temp = A[L];
        A[L] = A[R];
        A[R] = temp;
    }
}
