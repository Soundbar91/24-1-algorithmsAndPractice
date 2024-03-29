package chap3;

import java.io.*;
import java.util.Arrays;

public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            char[] S = br.readLine().toCharArray();
            int k = Integer.parseInt(br.readLine());

            int[] freq = new int[27];
            for (char c : S) freq[c - 'a']++;

            System.out.println(solve(k, 0, S.length - 1, freq, S));
        }

        br.close();
    }

    public static int solve(int k, int lo, int hi, int[] freq, char[] S) {
        if (lo >= hi) return 0;

        boolean flag = false;
        int result = 0;

        for (int i = 0; i < S.length; i++) {
            if (freq[S[i] - 'a'] < k) {
                char[] right = Arrays.copyOfRange(S, i + 1, hi + 1);
                int rightLen = solve(k, i + 1, right.length - 1, freq, right);

                result = Math.max(i, rightLen);
                flag = true;
                break;
            }
        }

        return flag? result : S.length;
    }
}