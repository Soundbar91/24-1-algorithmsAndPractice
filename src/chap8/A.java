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

            solve(maxDeadline, works);
            System.out.println();
        }
        br.close();
    }

    public static void solve(int maxDeadLine, Work[] works) {
        boolean[] visited = new boolean[maxDeadLine + 1];
        List<Work> result = new ArrayList<>();

        Arrays.sort(works, null);

        // 스케줄이 가능한 경우 리스트에 추가한다.
        for (Work work : works) {
            if (valid(work.D, visited)) {
                result.add(work);
            }
        }

        result.sort(Comparator.comparingInt(w -> w.id));
        result.forEach(work -> System.out.print(work.id + " "));
    }

    // 마감시간부터 내려가 스케줄이 가능한 시간대를 찾는다.
    public static boolean valid(int end, boolean[] visited) {
        for (int i = end; i > 0; i--) {
            if (!visited[i]) {
                visited[i] = true;
                return true;
            }
        }
        return false;
    }

    public static class Work implements Comparable<Work> {
        int id;
        int D;
        int P;

        public Work(int id, int d, int p) {
            this.id = id;
            D = d;
            P = p;
        }

        @Override
        public int compareTo(Work o) {
            return o.P - P;
        }
    }
}