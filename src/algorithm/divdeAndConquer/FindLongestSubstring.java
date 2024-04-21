package algorithm.divdeAndConquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FindLongestSubstring {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int[] W = br.readLine().chars().map(c -> c - 'a').toArray();
            int k = Integer.parseInt(br.readLine());
            System.out.println(solve(W, 0, W.length - 1, k));
        }

        br.close();
    }

    public static int solve(int[] W, int lo, int hi, int k) {
        final int len = hi - lo + 1;
        if (len < k) return 0;
        int[] freq = new int[26];

        for (int i = lo; i <= hi; ++i) ++freq[W[i]];

        int splitLoc = lo;
        while (splitLoc <= hi && freq[W[splitLoc]] >= k) ++splitLoc;
        if (splitLoc == hi + 1) return len;

        int left = solve(W, lo, splitLoc - 1, k);
        ++splitLoc;
        while (splitLoc <= hi && freq[W[splitLoc]] < k) ++splitLoc;
        int right = solve(W, splitLoc, hi, k);
        return Math.max(left, right);
    }
}