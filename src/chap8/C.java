package chap8;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int[] P = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            System.out.println(solve(M, P));
        }

        br.close();
    }

    public static int solve(int M, int[] P) {
        Arrays.sort(P);
        if (P[0] > M) return 0;

        int result = 0;

        for (int pay : P) {
            if (pay > M) break;
            else {
                result ++;
                M -=pay;
            }
        }

        return result;
    }
}