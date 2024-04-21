package judge;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class JDG_1191 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            Map<Integer, Integer> map = new HashMap<>();

            StringTokenizer st = new StringTokenizer(br.readLine());
            int cnt = st.countTokens();

            for (int i = 0; i < cnt; i++) {
                int value = Integer.parseInt(st.nextToken());
                map.put(value, map.getOrDefault(value, 0) + 1);
            }

            int maxKey = -1;
            int maxValue = -1;

            for (Map.Entry<Integer, Integer> pair : map.entrySet()) {
                if (maxValue < pair.getValue()) {
                    maxValue = pair.getValue();
                    maxKey = pair.getKey();
                }
            }

            System.out.println(maxKey);
        }

        br.close();
    }
}