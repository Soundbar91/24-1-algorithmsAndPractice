package chap1;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B_Merge {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] numArr = new int[N + 1];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) numArr[i] = Integer.parseInt(st.nextToken());

            mergeSort(1, N, numArr);

            for (int i = 1; i <= N; i++) sb.append(numArr[i]).append(' ');

            sb.append('\n');
        }

        // 배열의 공간 복잡도 O(N), 재귀 함수의 깊이 O(log n) -> 병합 정렬의 공간 복잡도 : O(nlog n)
        System.out.print(sb);
        br.close();
    }

    /*
    * 재귀함수의 깊이(레벨)는 공간 복잡도에 영향을 미친다.
    * 8 -> 4 -> 2 -> 1 크기가 줄어 들 때 마다 각 깊이에서는 2^n번의 함수가 재귀된다.
    * 여기서 n은 레벨이며, 0부터 시작한다. 최대 레벨은 log(n) + 1이다.*/
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
}
