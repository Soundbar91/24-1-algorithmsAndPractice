package algorithm.bruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CountSum {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int[] numArr = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) numArr[i] = Integer.parseInt(st.nextToken());

            sb.append(solve(M, numArr)).append('\n');
        }

        System.out.print(sb);
        br.close();
    }

    /*
     * 배열에 저장된 값에서 하나씩 뽑아 M에서 차감
     * M이 음수가 되면 해당 경우는 x -> 0를 반환
     * M이 0이 되면 해당 경우는 성립하므로 1를 반환
     * 재귀로부터 반환받은 값을 count 변수에 더하고 반환*/
    public static int solve(int M, int[] A) {
        if (M < 0) return 0;
        if (M == 0) return 1;

        int count = 0;
        for (int i : A) {
            count += solve(M - i, A);
        }

        return count;
    }
}