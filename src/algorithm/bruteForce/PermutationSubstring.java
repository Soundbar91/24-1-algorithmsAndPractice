package algorithm.bruteForce;

import java.util.Arrays;

public class PermutationSubstring {
    public static void main(String[] args) {
        String s1 = "okare";
        String s2 = "koreatech";

        boolean result = isPermutationSubstring(s1, s2);
        System.out.println(result);
    }

    public static boolean isPermutationSubstring(String s1, String s2) {
        if (s1.length() > s2.length()) return false;

        char[] s1Chars = s1.toCharArray();
        Arrays.sort(s1Chars);
        String s1Sorted = new String(s1Chars);

        for (int i = 0; i <= s2.length() - s1.length(); i++) {
            String substr = s2.substring(i, i + s1.length());
            char[] substrChars = substr.toCharArray();
            Arrays.sort(substrChars);
            String substrSorted = new String(substrChars);
            if (s1Sorted.equals(substrSorted)) {
                return true;
            }
        }

        return false;
    }
}

