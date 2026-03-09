import java.io.*;
import java.util.*;

public class Rotations {
    public static class Obj {
        int minD, numLeft;
        public Obj (int minD, int numLeft) {
            this.minD = minD;
            this.numLeft = numLeft;
        }
    }

    public static void helper(int N, int[] A, int[] ans) {
        int[] seen = new int[N + 1];
        Arrays.fill(seen, -1);
        int[] jump = new int[N];
        Arrays.fill(jump, -1);

        int right = 0;
        for (int i = 0; i < 2 * N; i++) {
            int v = A[i % N];
            if (seen[v] == -1) {
                right = Math.max(right, i);
            } else if (seen[v] < N) {
                jump[seen[v]] = i;
            }
            seen[v] = i;
        }

        for (int left = 0; left < N; left++) {
            int len = right - left;
            ans[left] = Math.min(ans[left], len);
            ans[right % N] = Math.min(ans[right % N], len);
            right = Math.max(right, jump[left]);
        }
    }

    public static void spread(int N, int[] ans) {
        for (int t = 0; t < 2; t++) {
            for (int i = 0; i < N; i++) {
                int to = (i + 1) % N;
                ans[to] = Math.min(ans[to], ans[i] + 1);
            }
            for (int i = N - 1; i >= 0; i--) {
                int from = (i + 1) % N;
                ans[i] = Math.min(ans[i], ans[from] + 1);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());
        int[] A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        HashMap<Integer, ArrayList<Integer>> pos = new HashMap<>();
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            pos.computeIfAbsent(A[i], k -> new ArrayList<>()).add(i);
        }

        int[] ans = new int[N];
        Arrays.fill(ans, N);
        helper(N, A, ans);
        spread(N, ans);
        for (int i = 0; i < N; i++) {
            if (i > 0) {
                out.append(" ");
            }
            out.append(ans[i]);
        }

        System.out.println(out);
        br.close();
    }
}

