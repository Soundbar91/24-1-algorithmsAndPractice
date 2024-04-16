package algorithm.bruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Picnic {
    static int N;
    static int K;
    static int F;
    static HashSet<Integer>[] friend;
    static ArrayList<Integer>[] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        F = Integer.parseInt(st.nextToken());

        friend = new HashSet[N + 1];
        map = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            friend[i] = new HashSet<>();
            map[i] = new ArrayList<>();
        }

        for (int i = 0; i < F; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            map[x].add(y);
            map[y].add(x);
            friend[x].add(y);
            friend[y].add(x);
        }

        solve(1, 0, new int[K]);
        System.out.print(-1);
    }

    public static void solve(int start, int cnt, int[] A) {
        if (cnt == K) {
            if (valid(A)) {
                for (int i : A) System.out.println(i);
                System.exit(0);
            }
            return ;
        }

        for (int i = start; i <= N; i++) {
            if (map[i].size() < K - 1) continue;
            A[cnt] = i;
            solve(i + 1, cnt + 1, A);
        }
    }

    public static boolean valid(int[] A) {
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length; j++) {
                if (i == j) continue;
                if (!friend[A[i]].contains(A[j])) return false;
            }
        }
        return true;
    }

}
