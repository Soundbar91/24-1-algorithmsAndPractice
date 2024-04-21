package algorithm.divdeAndConquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MaximumSubarray {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] nums = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());

            System.out.println(maxSubArray(nums));
        }
    }

    public static int maxSubArray(int[] nums) {
        return divideAndConquer(nums, 0, nums.length - 1);
    }

    private static int divideAndConquer(int[] nums, int left, int right) {
        if (left == right) return nums[left];

        int mid = left + (right - left) / 2;
        int leftSum = divideAndConquer(nums, left, mid);
        int rightSum = divideAndConquer(nums, mid + 1, right);
        int crossingSum = maxCrossingSubarray(nums, left, mid, right);

        return Math.max(Math.max(leftSum, rightSum), crossingSum);
    }

    static int maxCrossingSubarray(int[] nums, int left, int mid, int right) {
        int leftSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = mid; i >= left; i--) {
            sum += nums[i];
            leftSum = Math.max(leftSum, sum);
        }

        int rightSum = Integer.MIN_VALUE;
        sum = 0;
        for (int i = mid + 1; i <= right; i++) {
            sum += nums[i];
            rightSum = Math.max(rightSum, sum);
        }

        return leftSum + rightSum;
    }
}
