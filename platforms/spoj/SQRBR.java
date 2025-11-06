import java.io.*;
import java.util.*;

public class SQRBR {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int d = Integer.parseInt(br.readLine().trim());
        for (int tc = 0; tc < d; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            boolean[] fixed = new boolean[2 * n + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < k; i++) {
                fixed[Integer.parseInt(st.nextToken())] = true;
            }
            long[][] dp = new long[2 * n + 1][n + 1];
            if (fixed[1]) {
                dp[1][1] = 1;
            } else {
                dp[1][1] = 1;
            }
            for (int i = 2; i <= 2 * n; i++) {
                for (int j = 0; j <= n; j++) {
                    if (fixed[i]) {
                        if (j > 0) {
                            dp[i][j] = dp[i - 1][j - 1];
                        }
                    } else {
                        if (j > 0) {
                            dp[i][j] += dp[i - 1][j - 1];
                        }
                        if (j + 1 <= n) {
                            dp[i][j] += dp[i - 1][j + 1];
                        }
                    }
                }
            }
            sb.append(dp[2 * n][0]).append('\n');
        }
        System.out.print(sb);
        br.close();
    }
}

