package chap6;

import java.io.*;
import java.util.StringTokenizer;
import java.util.concurrent.ThreadLocalRandom;

public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] numArr = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) numArr[i] = Integer.parseInt(st.nextToken());

            solve(numArr);
        }

        br.close();
    }

    public static void solve(int[] A) {
        StringBuilder sb = new StringBuilder();
        int median = RSelect(A, 0, A.length - 1, A.length / 2);

        int[] result = new int[A.length];
        int lo = 0, hi = A.length - 1;

        for (int i : A) {
            if (i < median) result[lo++] = i;
            else if (i > median) result[hi--] = i;
        }

        for (int i = lo; i <= hi; i++) result[i] = median;

        lo = (A.length + 1) / 2;
        hi = A.length;

        sb.append(A.length).append('\n');
        for (int i = 0; i < A.length; i++) sb.append((i % 2 == 0) ? result[--lo] + " " : result[--hi] + " ");

        System.out.println(sb);
    }

    public static int RSelect(int[] A, int lo, int hi, int i) {
        if (lo == hi) return A[lo];

        int pivotLoc = chooseRandomPivot(lo, hi);
        swap(A, lo, pivotLoc);
        pivotLoc = partition(A, lo, hi);

        if (i == pivotLoc) return A[pivotLoc];
        else if (i < pivotLoc) return RSelect(A, lo, pivotLoc - 1, i);
        else return RSelect(A, pivotLoc + 1, hi, i);
    }

    public static int chooseRandomPivot(int lo, int hi) {
        return ThreadLocalRandom.current().nextInt(lo, hi + 1);
    }

    public static int partition(int[] A, int lo, int hi) {
        int pivot = A[lo];
        int L = lo + 1;
        int R = hi;

        while (L <= R) {
            while (L <= R && A[L] <= pivot) ++L;
            while (L <= R && A[R] > pivot) --R;
            if (L < R) swap(A, L++, R--);
        }

        swap(A, lo, R);
        return R;
    }

    public static void swap(int[] A, int L, int R) {
        int temp = A[L];
        A[L] = A[R];
        A[R] = temp;
    }
}