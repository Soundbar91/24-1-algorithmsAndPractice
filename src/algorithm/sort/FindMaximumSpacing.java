package algorithm.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class FindMaximumSpacing {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            int[] arr = new int[N];

            for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

            if (N == 0) System.out.println(0);
            else System.out.println(solve(arr));
        }
        br.close();
    }

    public static int solve(int[] nums) {
        int lo = getMin(nums);
        int hi = getMax(nums);
        if (nums.length <= 2 || lo == hi) return hi - lo;

        final int bucketSize = (int) Math.ceil((double)(hi - lo) / (nums.length - 1));
        int[] minBucket = new int[nums.length];
        int[] maxBucket = new int[nums.length];
        Arrays.fill(minBucket, Integer.MAX_VALUE);

        for (var n : nums) {
            int idx = (n - lo) / bucketSize;
            minBucket[idx] = Math.min(minBucket[idx], n);
            maxBucket[idx] = Math.max(maxBucket[idx], n);
        }
        int max = maxBucket[0];
        int ret = bucketSize;

        for (int i = 1; i < nums.length; i++) {
            if (minBucket[i] == Integer.MAX_VALUE) continue;
            ret = Math.max(ret, minBucket[i] - max);
            max = maxBucket[i];
        }

        return ret;
    }

    public static int getMin(int[] A) {
        int ret = A[0];
        for (int i = 1; i < A.length; i++) ret = Math.min(ret, A[i]);
        return ret;
    }

    public static int getMax(int[] A) {
        int ret = A[0];
        for (int i = 1; i < A.length; i++) ret = Math.max(ret, A[i]);
        return ret;
    }
}
