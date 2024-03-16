package chap1;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B_Tim {
    final static int SIZE = 8;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] numArr = new int[N + 1];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) numArr[i] = Integer.parseInt(st.nextToken());

            TimSort(numArr, N);

            for (int i = 1; i <= N; i++) sb.append(numArr[i]).append(' ');

            sb.append('\n');
        }

        System.out.print(sb);
        br.close();
    }

    public static void TimSort(int[] A, int n) {
        for (int i = 1; i <= n; i += SIZE) {
            insert(i, Math.min(i + SIZE, n), A);
        }

        // 병합 정렬 구현
    }

    public static void merge(int lo, int mid, int hi, int[] A) {
        int[] left = Arrays.copyOfRange(A, lo, mid + 1);
        int[] right = Arrays.copyOfRange(A, mid + 1, hi + 1);

        int L = 0, R = 0, i = lo;

        while (L < mid - lo + 1 && R < hi - mid) {
            if (left[L] <= right[R]) {
                A[i++] = left[L++];
            } else {
                A[i++] = right[R++];
            }
        }

        while (L < mid - lo + 1) {
            A[i++] = left[L++];
        }

        while (R < hi - mid) {
            A[i++] = right[R++];
        }
    }

    public static void insert(int lo, int hi, int[] A) {
        for (int i = lo + 1; i < hi; i++) {
            int temp = A[i];
            int j = i - 1;

            while (j >= lo && A[j] > temp) {
                A[j + 1] = A[j];
                j--;
            }
            A[j + 1] = temp;
        }
    }
}