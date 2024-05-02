package chap8;

import java.io.*;
import java.util.*;

public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            Integer[] nums = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .toArray(Integer[]::new);

            System.out.println(solve(nums));
        }

        br.close();
    }

    public static String solve(Integer[] nums) {
        StringBuilder sb = new StringBuilder();
        Arrays.sort(nums, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                String s1 = o1 + String.valueOf(o2);
                String s2 = o2 + String.valueOf(o1);
                return s2.compareTo(s1);
            }
        });

        for (int i : nums) sb.append(i);
        return sb.toString();
    }
}