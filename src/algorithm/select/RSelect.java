package algorithm.select;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.concurrent.ThreadLocalRandom;

public class RSelect {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            int[] nums = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());

            System.out.println(RSelect(nums,0, N - 1, k - 1));
        }

        br.close();
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
