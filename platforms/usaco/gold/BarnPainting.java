import java.util.*;

public class BarnPainting {
    static final int M = 1000000007;
    static final int MAX_N = 100005;
    static long[][] dp = new long[MAX_N][4];
    static List<Integer>[] adj = new ArrayList[MAX_N];
    static int[] already = new int[MAX_N];
    static void dfs(int cur, int par) {
        for (int next : adj[cur]) {
            if (next != par) {
                dfs(next, cur);
            }
        }
        
        for (int next : adj[cur]) {
            if (next != par) {
                for (int i = 1; i <= 3; i++) {
                    long t = 0;
                    for (int j = 1; j <= 3; j++) {
                        if (i != j && dp[next][j] != 0) {
                            t += dp[next][j];
                        }
                    }
                    dp[cur][i] = (dp[cur][i] * t) % M;
                }
            }
        }

        if (already[cur] != 0) {
            for (int i = 1; i <= 3; i++) {
                if (already[cur] != i) {
                    dp[cur][i] = 0;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < MAX_N; i++) {
            adj[i] = new ArrayList<>();
        }

        int n = sc.nextInt();
        int k = sc.nextInt();
        for (int i = 0; i < n - 1; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            adj[u].add(v);
            adj[v].add(u);
        }

        for (int i = 1; i <= k; i++) {
            int b = sc.nextInt();
            int c = sc.nextInt();
            already[b] = c;
        }

        for (int i = 1; i <= n; i++) {
            Arrays.fill(dp[i], 1);
        }

        dfs(1, -1);
        long ans = (dp[1][1] + dp[1][2] + dp[1][3]) % M;
        System.out.println(ans);
        
        sc.close();
    }
}

