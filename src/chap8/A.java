package chap8;

import java.io.*;
import java.util.*;

public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            Work[] works = new Work[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            int id = 1;

            int maxDeadline = -1;

            for (int i = 0; i < N; i++) {
                int deadline = Integer.parseInt(st.nextToken());
                int benefit = Integer.parseInt(st.nextToken());

                works[i] = new Work(id++, deadline, benefit);
                maxDeadline = Math.max(maxDeadline, deadline);
            }

            List<Work> result = solve(maxDeadline, works);

            result.forEach(work -> System.out.print(work.id + " "));
            System.out.println();
        }
        br.close();
    }

    public static List<Work> solve(int maxDeadLine, Work[] works) {
        boolean[] visited = new boolean[maxDeadLine + 1];
        List<Work> result = new ArrayList<>();

        Arrays.sort(works, (w1, w2) -> Integer.compare(w2.P, w1.P));

        for (Work work : works) {
            if (valid(work.D, visited)) {
                result.add(work);
            }
        }

        result.sort(Comparator.comparingInt(w -> w.id));

        return result;
    }

    public static boolean valid(int start, boolean[] visited) {
        for (int i = start; i > 0; i--) {
            if (!visited[i]) {
                visited[i] = true;
                return true;
            }
        }
        return false;
    }

    public static class Work{
        int id;
        int D;
        int P;

        public Work(int id, int d, int p) {
            this.id = id;
            D = d;
            P = p;
        }
    }
}