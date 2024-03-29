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
//            빈도수 배열을 사용하여 3 초과가 되지 않도록 한다.
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

    /*
    * depth : 자리수
    * 자리수에 맞지 않는 값이 왔을 경우를 제외한 경우에 대해 함수 재귀 호출
    * 재귀가 끝났다면 차감시켰던 값을 증감 */
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