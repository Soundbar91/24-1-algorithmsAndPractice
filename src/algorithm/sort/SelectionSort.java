package algorithm.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SelectionSort {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] numArr = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) numArr[i] = Integer.parseInt(st.nextToken());

            selectionSort(numArr);

            for (int i = 0; i < N; i++) System.out.print(numArr[i] + " ");
            System.out.println();
        }

        br.close();
    }

    public static void selectionSort(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            int minLoc = i;

            for (int j = i + 1; j < n; j++) {
                if (arr[minLoc] > arr[j]) {
                    minLoc = j;
                }
            }

            if (minLoc != i) {
                int temp = arr[minLoc];
                arr[minLoc] = arr[i];
                arr[i] = temp;
            }
        }
    }
}
