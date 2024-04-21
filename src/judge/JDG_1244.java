package judge;

import java.io.*;
import java.util.*;

public class JDG_1244 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            HashMap<Integer, Integer> map = new HashMap<>();

            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                int value = Integer.parseInt(st.nextToken());
                map.put(value, map.getOrDefault(value, 0) + 1);
            }

            List<Integer> list = solve(map, k);

            for (int i : list) System.out.print(i + " ");
            System.out.println();
        }
        br.close();
    }

    public static List<Integer> solve(HashMap<Integer, Integer> map, int k) {
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((a, b) -> b.getValue() - a.getValue());

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            res.add(list.get(i).getKey());
        }

        return res;
    }
}