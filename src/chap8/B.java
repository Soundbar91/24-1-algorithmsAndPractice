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

        /*
        * 숫자 비교 과정에서 AB, BA 순서로 구성한 숫자의 값을 기준으로 정렬한다.
        * AB가 BA보다 큰 경우에는 A를 B보다 앞에 위치시킨다. */
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