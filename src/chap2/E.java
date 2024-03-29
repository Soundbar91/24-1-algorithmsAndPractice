package chap2;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class E {
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

            List<Integer> result = solve(M, numArr);

            if (result == null) sb.append(-1).append('\n');
            else {
                sb.append(result.size()).append(' ');
                for (int i : result) sb.append(i).append(' ');
                sb.append('\n');
            }
        }

        System.out.print(sb);
        br.close();
    }

    /*
     * 배열에 저장된 값에서 하나씩 뽑아 M에서 차감
     * M이 음수가 되면 해당 경우는 x
     * M이 0이 되면 해당 경우는 성립하므로, 새로운 LinkedList를 반환.
     * 이후, 밑에서 위로 올라가면서 값을 리스트에 추가
     * 재귀로 받은 리스트의 길이가 결과값 리스트보다 길거나, 결과값 리스트가 널인 경우 갱신*/
    public static List<Integer> solve(int M, int[] A) {
        if (M < 0) return null;
        if (M == 0) return new LinkedList<>();

        List<Integer> result = null;
        for (int i : A) {
            List<Integer> ret = solve(M - i, A);

            if (ret != null) {
                if (result == null || ret.size() + 1 < result.size()) {
                    ret.add(i);
                    result = ret;
                }
            }
        }
        return result;
    }
}