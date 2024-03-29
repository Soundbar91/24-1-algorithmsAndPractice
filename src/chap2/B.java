package chap2;

import java.io.*;
import java.util.StringTokenizer;

public class B {
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

            sb.append(solve(M, numArr) ? "true" : "false").append('\n');
        }

        System.out.print(sb);
        br.close();
    }

    /*입력 받은 배열에 저장된 값을 순차적으로 가져와 M에서 뺀다.
    * 만약, 음수로 떨어지면 해당 경우의 수는 x
    * 뺀 M의 값이 0이 되면 입력한 배열에서 숫자를 뽑아 합을 구할 수 있다는 뜻*/
    public static boolean solve(int M, int[] A) {
        if (M < 0) return false;
        else if (M == 0) return true;

        for (int i : A) if (solve(M - i, A)) return true;

        return false;
    }
}