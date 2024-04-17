package algorithm.divdeAndConquer;

public class MaximumSubarray {
    public static void main(String[] args) {
        int[] nums1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] nums2 = {1};
        int[] nums3 = {5, 4, -1, 7, 8};

        System.out.println(maxSubArray(nums1)); // Output: 6
        System.out.println(maxSubArray(nums2)); // Output: 1
        System.out.println(maxSubArray(nums3)); // Output: 23
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

    private static int maxCrossingSubarray(int[] nums, int left, int mid, int right) {
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

