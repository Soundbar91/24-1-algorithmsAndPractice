package chap1;

import java.io.*;
import java.util.StringTokenizer;

public class B_Tim {
    final static int DIVISION_SIZE = 8;     // 분할의 크기
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] numArr = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) numArr[i] = Integer.parseInt(st.nextToken());

            TimSort(N, numArr);

            for (int i = 0; i < N; i++) sb.append(numArr[i]).append(' ');
            sb.append('\n');
        }

        System.out.print(sb);
        br.close();
    }

    public static void TimSort(int N, int[] A) {
        /* 분할 크기 8씩 끊어서 선택 정렬 진행
        * N = 10인 경우 8씩 끊으면 1 ~ 8, 9 ~ 10이 됨
        * min 함수를 이용하여 9 ~ 16 까지가 아닌 9 ~ 10 까지 선택 정렬을 진행*/
        for (int i = 0; i < N; i += DIVISION_SIZE) {
            insert(i, Math.min(i + DIVISION_SIZE - 1, N - 1), A);
        }

        /* 분할 된 배열에 대해 합병 정렬 진행
        * size : 분할 된 배열을 합병하는 기준 값*/
        for (int size = DIVISION_SIZE; size < N; size *= 2) {
            for (int left = 0; left < N; left += 2 * size) {
                int mid = left + size - 1;
                int right = Math.min((left + 2 * size - 1), N - 1);

                if (mid < right) merge(left, mid, right, A);
            }
        }
    }

    public static void merge(int lo, int mid, int hi, int[] A) {
        int[] left = new int[mid - lo + 1];
        int[] right = new int[hi - mid];

        for (int i = 0; i < left.length; i++) left[i] = A[lo + i];
        for (int i = 0; i < right.length; i++) right[i] = A[mid + 1 + i];

        int L = 0, R = 0, i = lo;

        while (L < left.length && R < right.length) {
            if (left[L] <= right[R]) {
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

    public static void insert(int lo, int hi, int[] A) {
        for (int i = lo + 1; i <= hi; i++) {
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
