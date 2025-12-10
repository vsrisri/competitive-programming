import java.io.*;
import java.util.*;

public class FISHER {
    static final int max = 1_000_000_000;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String line;
        while (true) {
            line = br.readLine();
            if (line == null) {
                break;
            }
            line = line.trim();
            if (line.length() == 0) {
                continue;
            }

            st = new StringTokenizer(line);
            int n = Integer.parseInt(st.nextToken());
            int T = Integer.parseInt(st.nextToken());
            if (n == 0 && T == 0) {
                break;
            }
            int[][] time = new int[n][n];
            int[][] cost = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    time[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            br.readLine();
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    cost[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            br.readLine();
            int[][] dp = new int[n][T + 1];
            for (int i = 0; i < n; i++) {
                Arrays.fill(dp[i], max);
            }

            dp[0][0] = 0;
            int ansC = max, ansT = 0;
            for (int tt = 0; tt <= T; tt++) {
                for (int u = 0; u < n; u++) {
                    if (dp[u][tt] == max) {
                        continue;
                    }
                    for (int v = 0; v < n; v++) {
                        int nt = tt + time[u][v];
                        if (nt <= T) {
                            int nc = dp[u][tt] + cost[u][v];
                            if (nc < dp[v][nt]) {
                                dp[v][nt] = nc;
                            }
                        }
                    }
                }
            }
            for (int tt = 0; tt <= T; tt++) {
                if (dp[n - 1][tt] < ansC) {
                    ansC = dp[n - 1][tt];
                    ansT = tt;
                }
            }
            System.out.println(ansC + " " + ansT);
        }
        br.close();
    }
}

