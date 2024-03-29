package chap3;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

            System.out.println(mergeSort(0, N - 1, arr));
        }

        br.close();
    }

    public static int mergeSort(int lo, int hi, int[] A) {
        int result = 0;

        if (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            result += mergeSort(lo, mid, A);
            result += mergeSort(mid + 1, hi, A);
            result += merge(lo, mid, hi, A);
        }

        return result;
    }

    public static int merge(int lo, int mid, int hi, int[] A) {
        int[] left = Arrays.copyOfRange(A, lo, mid + 1);
        int[] right = Arrays.copyOfRange(A, mid + 1, hi + 1);

        int L = 0, R = 0, i = lo, result = 0;

        while (L < left.length && R < right.length) {
            if (left[L] <= right[R]) {
                A[i++] = left[L++];
            }
            else {
                A[i++] = right[R++];
                result += left.length - L;
            }
        }

        while (L < left.length) {
            A[i++] = left[L++];
        }

        while (R < right.length) {
            A[i++] = right[R++];
        }

        return result;
    }
}