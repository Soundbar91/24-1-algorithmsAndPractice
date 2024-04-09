package chap4;

import java.io.*;
import java.util.*;

public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            int[] arr = new int[N];
            int max = Integer.MIN_VALUE;

            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                max = Math.max(max, arr[i]);
            }

            if (N == 0) System.out.println(0);
            else System.out.println(getResult(radixSort(arr, max)));
        }
        br.close();
    }

    // 기수 정렬 메소드
    public static int[] radixSort(int[] A, int max){
        int radix = 1, arrSize = A.length;

        // 각 자리수에 해당하는 버킷
        ArrayList<Queue<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < 10; i++)buckets.add(new LinkedList<>());

        // 자리수가 최대값보다 커질 때까지 반복
        while (radix <= max) {
            // 자리수에 해당하는 버킷에 숫자 삽입
            for (int idx = 0; idx < arrSize; idx++) buckets.get((A[idx] / radix) % 10).add(A[idx]);

            int arrIndex = 0;

            // 버킷에 저장된 숫자 꺼내기
            for (int bucketIndex = 0; bucketIndex < 10; bucketIndex++){
                while (!buckets.get(bucketIndex).isEmpty()){
                    A[arrIndex++] = buckets.get(bucketIndex).poll();
                }
            }

            // 자리수 증가
            radix *= 10;
        }

        return A;
    }

    public static int getResult(int[] A) {
        int result = 0;
        int prev = A[0];

        for (int i = 1; i < A.length; i++) {
            result = Math.max(result, A[i] - prev);
            prev = A[i];
        }

        return result;
    }
}