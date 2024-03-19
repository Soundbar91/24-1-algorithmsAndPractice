package chap2;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class C {
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

    public static List<Integer> solve(int M, int[] A) {
       if (M < 0) return null;
       if (M == 0) return new LinkedList<>();

       for (int i : A) {
           List<Integer> ret = solve(M - i, A);

           if (ret != null) {
                ret.add(i);
                return ret;
           }
       }
       return null;
    }
}