package chap1;

import java.io.*;
import java.util.*;

public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            Map<Integer, Integer> map = new HashMap<>((int) (1.3 * N));

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int num = Integer.parseInt(st.nextToken());
                map.put(num, map.getOrDefault(num, 0) + 1);
            }

            System.out.println(solve(map));
        }

        br.close();
    }

    public static int solve(Map<Integer, Integer> map) {
        int result = 0;

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int num = entry.getKey();
            int frequency = entry.getValue();
            if (map.containsKey(num - 1))
                result = Math.max(result, frequency + map.get(num - 1));
            if (map.containsKey(num + 1))
                result = Math.max(result, frequency + map.get(num + 1));
        }

        return result;
    }
}
