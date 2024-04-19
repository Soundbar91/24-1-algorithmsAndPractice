package algorithm.select;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FindMaxMinSameTime {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

            int[] result = solve(N, arr);

            System.out.println(result[0] + " " + result[1]);
        }

        br.close();
    }

    public static int[] solve(int N, int[] arr) {
        int[] value = setValue(N, arr);
        int max = value[0], min = value[1], i = value[2];

        for (; i < N; i += 2) {
            if (arr[i] < arr[i + 1]) {
                if (arr[i] < min) min = arr[i];
                if (arr[i + 1] > max) max = arr[i + 1];
            }
            else {
                if (arr[i + 1] < min) min = arr[i + 1];
                if (arr[i] > max) max = arr[i];
            }
        }

        return new int[]{max, min};
    }

    public static int[] setValue(int N, int[] arr) {
        int[] value = new int[3];

        // value[0] = max, value[1] = min, value[2] = i
        if (N % 2 == 0) {
            if (arr[0] < arr[1]) {
                value[1] = arr[0];
                value[0] = arr[1];
            }
            else {
                value[1] = arr[1];
                value[0] = arr[0];
            }
            value[2] = 2;
        }
        else {
            value[1] = arr[0];
            value[0] = arr[0];
            value[2] = 1;
        }

        return value;
    }
}