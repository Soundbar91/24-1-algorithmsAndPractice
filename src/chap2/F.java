package chap2;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class F {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int[] numArr = new int[10];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int value = Integer.parseInt(st.nextToken());

                if (numArr[value] == 3) continue;
                else numArr[value] += 1;
            }

            List<Integer> result = solve(numArr, 0, "", new LinkedList<>());

            for (int i : result) sb.append(i).append(' ');
            sb.append('\n');
        }

        System.out.print(sb);
        br.close();
    }

    // 빈도수 배열을 사용하여 중복 제거
   public static List<Integer> solve(int[] A, int depth, String str, List<Integer> result) {
        if (depth == 3) {
            result.add(Integer.parseInt(str));
        }
        else {
            for (int i = 0; i < A.length; i++) {
                if (A[i] == 0) continue;
                if (depth == 0 && i == 0) continue;
                if (depth == 2 && i % 2 != 0) continue;

                A[i] -= 1;
                solve(A, depth + 1, str + i, result);
                A[i] += 1;
            }
        }

        return result;
   }
}