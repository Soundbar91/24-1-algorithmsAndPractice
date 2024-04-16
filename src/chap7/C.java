package chap7;

import java.io.*;
import java.util.*;

public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            String startWord = st.nextToken();
            String endWord = st.nextToken();
            int dictionarySize = Integer.parseInt(st.nextToken());

            String[] dictionary = new String[dictionarySize];
            st = new StringTokenizer(br.readLine());

            boolean flag = false;
            for (int i = 0; i < dictionarySize; i++) {
                dictionary[i] = st.nextToken();
                if (dictionary[i].equals(endWord)) flag = true;
            }


            if (!flag) System.out.println(-1);
            else System.out.println(solve(startWord, endWord, dictionary));
        }

        br.close();
    }

    public static int solve(String startWord, String endWord, String[] dictionary) {
        Queue<Word> queue = new LinkedList<>();
        queue.add(new Word(startWord, 0));
        boolean[] visited = new boolean[dictionary.length];

        while (!queue.isEmpty()) {
            Word word = queue.poll();

            if (endWord.equals(word.word)) return word.cnt;

            for (int i = 0; i < dictionary.length; i++) {
                if (!visited[i] && noEqualCount(word.word, dictionary[i])) {
                    visited[i] = true;
                    queue.add(new Word(dictionary[i], word.cnt + 1));
                }
            }
        }

        return -1;
    }

    public static boolean noEqualCount(String str1, String str2) {
        if (str1.length() != str2.length()) return false;

        int count = 0;

        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) count++;
            if (count > 1) return false;
        }

        return true;
    }

    public static class Word {
        String word;
        int cnt;

        public Word(String word, int cnt) {
            this.word = word;
            this.cnt = cnt;
        }
    }
}