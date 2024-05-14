package chap10;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class A_Memoization {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());

            int[] nums = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();

            System.out.println(solve(M, N, nums, new HashMap<Integer, Boolean>()));
        }

        br.close();
    }

    public static boolean solve(int M, int N, int[] nums, HashMap<Integer, Boolean> map) {
        if (M < 0) return false;
        if (M == 0) return true;
        if (map.containsKey(M)) return map.get(M);

        for (int i = 0; i < N; i++) {
            if (solve(M - nums[i], N, nums, map)) {
                map.put(M, true);
                return true;
            }
        }

        map.put(M, false);
        return false;
    }
}