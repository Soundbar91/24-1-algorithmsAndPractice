package algorithm.select;

import java.io.IOException;
import java.util.Arrays;

public class DSelect {
    public static void main(String[] args) throws IOException {
        int[] numArr = new int[]{7, 14, 1, 8, 10, 12, 9};
        System.out.print(DSelect(0, numArr.length - 1, numArr, numArr.length / 2));
    }

    public static int DSelect(int lo, int hi, int[] A, int i) {
        int size = hi - lo + 1;
        if (size == 1) return A[lo];

        int pivotLoc;
        findMedian(lo, hi, A);

        if (size > 5) {
            DSelect(lo, lo + size / 5 - 1, A, lo + size / 10);
            swap(lo, lo + size / 10, A);
        }
        pivotLoc = partition(A, lo, hi);

        if (i == pivotLoc) return A[pivotLoc];
        else if (i < pivotLoc) return DSelect(lo, pivotLoc - 1, A, i);
        else return DSelect(pivotLoc + 1, hi, A, i);
    }

    public static int partition(int[] A, int lo, int hi) {
        int pivotLoc = lo;
        int pivot = A[lo];

        int L = lo + 1;
        int R = hi;

        while(L <= R) {
            while(L <= R && A[L] <= pivot) ++L;
            while(L <= R && A[R] > pivot) --R;
            if (L < R) swap(L++, R--, A);
        }

        swap(pivotLoc, R, A);
        return R;
    }

    public static void findMedian(int left, int right, int[] A) {
        int begin = left, j = 0;

        while (begin <= right) {
            int end = begin + 4;
            if (end > right) end = right;
            int mid = begin + (end - begin) / 2;
            Arrays.sort(A, begin, end);
            swap(left + j, mid, A);
            begin += 5;
            ++j;
        }
    }

    public static void swap(int L, int R, int[] A) {
        int temp = A[L];
        A[L] = A[R];
        A[R] = temp;
    }
}
