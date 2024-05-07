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

    /*
    * 사탕 가격을 오름차순 정렬 후 낮은 가격의 사탕부터 구매한다. */
    public static int solve(int M, int[] P) {
        Arrays.sort(P);

        int result = 0;

        for (int pay : P) {
            if (pay > M) break;
            else {
                result++;
                M -=pay;
            }
        }

        return result;
    }
}