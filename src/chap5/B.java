package chap5;

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            HashMap<Integer, Integer> map = new HashMap<>();
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int value = Integer.parseInt(st.nextToken());
                arr[i] = value;
                map.put(value, map.getOrDefault(value, 0) + 1);
            }

            System.out.println(solve(N, arr, map));
        }

        br.close();
    }

    /* 정확성
    * 항상 절반 이상이 특정 수로 구성수로 구성된다 -> 길이가 N인 배열의 N/2 이상은 특정 수로 구성되어 있다.
    * 배열의 길이 범위 내에서 랜덤 숫자를 생성하여 해당 값을 확인하면 50% 확률로 절반이 넘는 수이다.
    * 이는 확실하게 값을 보장한다.
    * 성능
    * 50% 확률이기 때문에 최선의 경우에는 배열의 절반 정도 확인했을 때 값을 찾을 수 있다. O(n/2) = O(n)
    * 최악의 경우에는 그 50% 확률을 피해가서 무한 루프에 빠질 수 있다.*/
    public static int solve(int N, int[] A, HashMap<Integer, Integer> map) {
        while (true) {
            int index = (int) (Math.random() * (N));
            if (map.get(A[index]) >= N / 2 + 1) return A[index];
        }
    }
}