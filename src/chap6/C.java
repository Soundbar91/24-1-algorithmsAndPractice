package chap6;

import java.io.*;
import java.util.StringTokenizer;
import java.util.concurrent.ThreadLocalRandom;

public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringBuilder sb = new StringBuilder();
            int N = Integer.parseInt(br.readLine());
            int[] numArr = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) numArr[i] = Integer.parseInt(st.nextToken());

            solve(numArr);

            sb.append(N).append('\n');
            for (int i : numArr) sb.append(i).append(" ");
            System.out.println(sb);
        }

        br.close();
    }

    public static void solve(int[] A) {
        int mid = (A.length - 1) / 2;
        int[] copy = A.clone();
        RSelect(copy, 0, A.length - 1, mid);
        wiggleSort(A, copy, copy[mid]);
    }

    public static void wiggleSort(int[] nums, int[] copy, int median) {
        int S = ((nums.length & 1) == 1) ? nums.length - 1 : nums.length - 2;
        int L = 1;

        for (var n : copy) {
            if (n < median) {
                nums[S] = n;
                S -= 2;
            }
            else if (n > median) {
                nums[L] = n;
                L += 2;
            }
        }

        for (; L < nums.length; L += 2) nums[L] = median;
        for (; S >= 0; S -= 2) nums[S] = median;
    }

    public static void RSelect(int[] A, int lo, int hi, int i) {
        if (lo == hi) return ;

        int pivotLoc = partition(A, lo, hi);

        if (i == pivotLoc) return;
        else if (i < pivotLoc) RSelect(A, lo, pivotLoc - 1, i);
        else RSelect(A, pivotLoc + 1, hi, i);
    }

    public static int partition(int[] A, int lo, int hi) {
        int randomLoc = ThreadLocalRandom.current().nextInt(lo, hi + 1);
        swap(A, lo, randomLoc);
        int pivotLoc = doPartition(A, lo + 1, hi, A[lo]);
        swap(A, lo, pivotLoc);
        return pivotLoc;
    }

    public static int doPartition(int[] A, int lo, int hi, int pivot) {
        while(lo <= hi) {
            while (lo <= hi && pivot >= A[lo]) ++lo;
            while (lo <= hi && pivot < A[hi]) --hi;
            if (lo < hi) swap(A, lo++, hi--);
        }
        return hi;
    }

    public static void swap(int[] A, int L, int R) {
        if (L == R) return;
        int temp = A[L];
        A[L] = A[R];
        A[R] = temp;
    }
}