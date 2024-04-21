package algorithm.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BubbleSort {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] numArr = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) numArr[i] = Integer.parseInt(st.nextToken());

            bubbleSort(numArr);

            for (int i = 0; i < N; i++) System.out.print(numArr[i] + " ");
            System.out.println();
        }

        br.close();
    }

    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean flag;

        for (int i = 0; i < n - 1; i++) {
            flag = false;

            for (int j = n - 1; j > i; j--) {
                if (arr[j - 1] > arr[j]) {
                    int temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                    flag = true;
                }
            }

            if (!flag) break;
        }
    }
}
