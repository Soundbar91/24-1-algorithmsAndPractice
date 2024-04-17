package judge;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JDG_1005 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] numArr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) numArr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(numArr);
        System.out.print(solve(N, numArr));
    }

    public static int solve(int N, int[] A) {
        int result = 0;

        int prev1 = A[0] - 1;
        int prev2;
        int j, k;

        for (int i = 0; i < N - 2; i++) {
            if (A[i] == prev1) continue;
            prev1 = A[i];

            j = i + 1;
            k = N - 1;
            prev2 = A[j] - 1;

            while (j < k) {
                if (A[i] + A[j] + A[k] > 0) k--;
                else if (A[i] + A[j] + A[k] < 0) j++;
                else {
                    if (prev2 != A[j]) {
                        result++;
                        prev2 = A[j];
                    }
                    j++;
                    k--;
                }
            }
        }

        return result;
    }
}