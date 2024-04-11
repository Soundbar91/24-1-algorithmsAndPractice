package chap6;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

            System.out.println(solve(N, arr));;
        }

        br.close();
    }

    public static int solve(int N ,int[] arr) {
        int[] S = IntStream.concat(Arrays.stream(new int[N - 1]), Arrays.stream(arr)).toArray();

        for (int i = N - 2, j = S.length - 1; i >= 0; i--, j -=2)
            S[i] = Math.max(S[j], S[j - 1]);

        int max = S[0], ret = Integer.MIN_VALUE, left = 1;

        while (left < S.length) {
            int cur = S[left] == max ? S[left + 1] : S[left];
            int maxLoc = S[left] == max ? left : left + 1;

            if (cur == max) return max;
            ret = Math.max(ret, cur);
            left = maxLoc * 2 + 1;
        }

        return ret;
    }
}