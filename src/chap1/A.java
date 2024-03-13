package chap1;

import java.io.*;
import java.util.*;

public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            HashMap<Integer, Integer> map = new HashMap<>();
            int N = Integer.parseInt(br.readLine());
            int result = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int value = Integer.parseInt(st.nextToken());
                map.put(value, map.getOrDefault(value, 0) + 1);
            }

            for (int num : map.keySet()) {
                int value = map.get(num);
                if (map.containsKey(num - 1)) result = Math.max(result, value + map.get(num - 1));
                if (map.containsKey(num + 1)) result = Math.max(result, value + map.get(num + 1));
            }

            sb.append(result).append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
