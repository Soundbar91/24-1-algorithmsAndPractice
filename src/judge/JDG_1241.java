package judge;

import java.util.Scanner;

public class JDG_1241 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int T = scanner.nextInt(); // 테스트케이스 수
        scanner.nextLine(); // 개행문자 처리

        for (int t = 0; t < T; t++) {
            String S = scanner.nextLine(); // 문자열 입력
            int goodSubstringCount = countGoodSubstrings(S);
            System.out.println(goodSubstringCount);
        }
    }

    static int countGoodSubstrings(String S) {
        int count = 0;
        int len = S.length();

        for (int i = 0; i <= len - 3; i++) {
            String substring = S.substring(i, i + 3);
            if (isGoodSubstring(substring)) {
                count++;
            }
        }

        return count;
    }

    static boolean isGoodSubstring(String substring) {
        return substring.charAt(0) != substring.charAt(1)
                && substring.charAt(1) != substring.charAt(2)
                && substring.charAt(0) != substring.charAt(2);
    }
}
