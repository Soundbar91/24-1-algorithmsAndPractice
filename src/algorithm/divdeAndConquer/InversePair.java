package algorithm.divdeAndConquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class InversePair {
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

        // 왼쪽, 오른족 분할
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
                /*
                * 왼쪽 값이 더 큰 경우 역쌍
                * 왼쪽 배열에 있는 왼쪽 값의 기준으로 위에 있는 값들은 오른쪽 값보다 큼
                * 정렬된 배열이기 때문
                * 왼쪽 배열의 길이에서 왼쪽 값 인덱스를 빼게 되면 오른쪽 값보다 큰 값의 개수를 구할 수 있다.*/
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