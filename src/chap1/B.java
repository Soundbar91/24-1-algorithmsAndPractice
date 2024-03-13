package chap1;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            StringBuilder sb = new StringBuilder();
            int[] numArr = new int[N + 1];  // 입력 받은 값을 저장할 배열, N의 길이 만큼 선언

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) numArr[i] = Integer.parseInt(st.nextToken());

            mergeSort(0, N - 1, numArr);

            for (int i = 1; i <= N; i++) sb.append(i).append(' ');

            bw.write(sb.toString());
            bw.flush();
        }

        bw.close();
        br.close();
    }

    public static void mergeSort(int lo, int hi, int[] A) {
        if (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            mergeSort(lo, mid, A);
            mergeSort(mid + 1, hi, A);
            merge(lo, mid, hi, A);
        }
    }

    public static void merge(int lo, int mid, int hi, int[] A) {
        int[] left = Arrays.copyOfRange(A, lo, mid + 1);
        int[] right = Arrays.copyOfRange(A, mid + 1, hi + 1);

        int L = 0, R = 0, i = lo;

        while (L < left.length && R < right.length) {
            if (left[L] < right[R]) {
                A[i++] = left[L++];
            } else {
                A[i++] = right[R++];
            }
        }

        while (L < left.length) {
            A[i++] = left[L++];
        }

        while (R < right.length) {
            A[i++] = right[R++];
        }
    }
}
