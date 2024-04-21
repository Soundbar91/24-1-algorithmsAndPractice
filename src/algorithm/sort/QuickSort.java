package algorithm.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class QuickSort {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringBuilder sb = new StringBuilder();
            int N = Integer.parseInt(br.readLine());

            int[] arr = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

            quickSort(arr, 0, N - 1);

            for (int i : arr) sb.append(i).append(' ');
            System.out.println(sb);
        }

        br.close();
    }

    public static void quickSort(int[] A, int lo, int hi) {
        if(lo < hi) {
            int pivot = partition(A, lo, hi);
            quickSort(A, lo, pivot - 1);
            quickSort(A, pivot + 1, hi);
        }
    }

    public static int partition(int[] A, int lo, int hi) {
        int index = (int) (Math.random() * (hi - lo + 1)) + lo;
        int pivot = A[index];

        int L = lo + 1;
        int R = hi;

        swap(A, lo, index);

        while(L <= R) {
            while(L <= R && A[L] <= pivot) ++L;
            while(L <= R && A[R] > pivot) --R;
            if (L < R) swap(A, L++, R--);
        }

        swap(A, lo, R);
        return R;
    }

    // 스왑
    public static void swap(int[] A, int L, int R) {
        int temp = A[L];
        A[L] = A[R];
        A[R] = temp;
    }
}
