package algorithm.sort;

public class ArithmeticTriplets {
    public static int arithmeticTriplets(int[] A, int d) {
        boolean[] N = new boolean[201];

        for (int n : A) N[n] = true;

        int max = A[A.length - 1];
        int count = 0;

        for (int i = 0; i <= A.length - 2; i++) {
            if (A[i] + 2 * d <= max && N[A[i] + d] && N[A[i] + 2 * d]) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[] A = {0, 1, 4, 6, 7, 10};
        int d = 3;

        int result = arithmeticTriplets(A, d);
        System.out.println("산술적 3쌍 개수: " + result);
    }
}
