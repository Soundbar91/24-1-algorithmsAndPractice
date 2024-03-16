package chap1;

import java.io.*;
import java.util.*;

public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            HashMap<Integer, Integer> map = new HashMap<>((int)(1.3 * N));

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int value = Integer.parseInt(st.nextToken());
                map.put(value, map.getOrDefault(value, 0) + 1);
            }

            sb.append(solved(map)).append('\n');
        }

        System.out.print(sb);
        br.close();
    }

    public static int solved(HashMap<Integer, Integer> map) {
        int result = 0;

        // 키 값을 기준으로 +- 1에 해당하는 값이 있는지 검사 후 결과값 갱신
        for (int num : map.keySet()) {
            int value = map.get(num);
            if (map.containsKey(num - 1)) result = Math.max(result, value + map.get(num - 1));
            if (map.containsKey(num + 1)) result = Math.max(result, value + map.get(num + 1));
        }

        return result;
    }
}
