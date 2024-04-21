package chap3;

import java.io.*;

public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            String S = br.readLine();
            int k = Integer.parseInt(br.readLine());

            System.out.println(solve(S, k, 0, S.length() - 1));
        }

        br.close();
    }

    public static int solve(String S, int k, int lo, int hi) {
        if (lo > hi) return 0;

        int[] freq = new int[26];

        for (int i = lo; i <= hi; i++) freq[S.charAt(i) - 'a']++;

        boolean flag = false;
        int index = 0;

        for (int i = lo; i <= hi; i++) {
            if (freq[S.charAt(i) - 'a']< k) {
                flag = true;
                index = i;
                break;
            }
        }

        if (!flag) return hi - lo + 1;

        int left = solve(S, k, lo, index - 1);
        int right = solve(S, k, index + 1, hi);

        return Math.max(left, right);
    }
}