package algorithm.bruteForce;

public class CountSumUnique {
    public static void main(String[] args) {
        int[] A = {2, 5, 2, 1, 2};
        int target = 5;

        int result = countUniqueSum(target, A, 0);
        System.out.println("중복 제거된 조합의 수: " + result);
    }

    public static int countUniqueSum(int M, int[] A, int start) {
        if (M < 0) return 0;
        if (M == 0) return 1;

        int count = 0;

        for (int i = start; i < A.length; i++) {
            boolean flag = true;
            for (int j = i + 1; j < A.length; j++) {
                if (A[i] == A[j]) {
                    flag = false;
                    break;
                }
            }
            if (!flag) continue;
            count += countUniqueSum(M - A[i], A, i + 1);

        }
        return count;
    }
}

