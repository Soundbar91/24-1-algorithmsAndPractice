package algorithm.divdeAndConquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FindLongestSubstring {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            String S = br.readLine();
            int k = Integer.parseInt(br.readLine());

            System.out.println(solve(S, k, 0, S.length() - 1));
        }

        br.close();
    }

    public static int solve(String S, int k, int lo, int hi) {
        if (lo > hi) return 0;

        int[] freq = new int[26];

        for (int i = lo; i <= hi; i++) freq[S.charAt(i) - 'a']++;

        boolean flag = false;
        int index = 0;

        /*
        * 문자열 S에서 k 이하 등장하는 인덱스를 검사
        * 등장하지 않으면 문자열 길이 반환
        * 등장하면 인덱스 저장 후 탈출*/
        for (int i = lo; i <= hi; i++) {
            if (freq[S.charAt(i) - 'a']< k) {
                flag = true;
                index = i;
                break;
            }
        }

        if (!flag) return hi - lo + 1;

        // k 이하 등장하는 문자 기준으로 왼쪽, 오른쪽 분할하여 재귀 호출
        int left = solve(S, k, lo, index - 1);
        int right = solve(S, k, index + 1, hi);

        return Math.max(left, right);
    }
}