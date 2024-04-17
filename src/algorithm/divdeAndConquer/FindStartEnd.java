package algorithm.divdeAndConquer;

import java.util.Arrays;

public class FindStartEnd {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(searchRange(new int[]{4, 5, 5, 6, 7, 8, 8, 8, 9, 9, 9, 10}, 9)));
    }

    public static int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1, -1};
        if (nums == null || nums.length == 0) return result;

        int leftIndex = searchLeft(nums, target);
        int rightIndex = searchRight(nums, target);

        result[0] = leftIndex;
        result[1] = rightIndex;

        return result;
    }

    private static int searchLeft(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int index = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
            if (nums[mid] == target) index = mid;
        }

        return index;
    }

    private static int searchRight(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int index = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
            if (nums[mid] == target) index = mid;
        }

        return index;
    }
}
