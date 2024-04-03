package chap4;

import java.io.*;
import java.util.StringTokenizer;

/*B문제: 확률적 알고리즘으로 해결해야 합니다. 또한 알고리즘의 정확성과 성능을 분석하여 간단히 주석에 포함하여 주세요
- 확률적 알고리즘: 전체 구간에서 한 요소를 랜덤하게 선택하였을 때, 이 요소가 절반이 넘는 수가 될 확률은 50%입니다.
- 따라서 랜덤하게 선택하였을 때 답을 찾을 확률의 기대값은 2입니다.
- 이를 활용한 알고리즘을 구현해보고 실제 답을 찾을 수 있는지(정확성), 찾을 수 있다면 이 알고리즘의 성능은 어떻게 되는지 간단히 분석해 보세요.
- Rselect를 이용하여 중간값을 찾는 것은 아닙니다.*/
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        }

    }

    public static void solve() {

    }
}