import java.io.*;
import java.util.*;

public class ABA12C {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < t; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int[] cost = new int[k];
            int max = 1000000000;
            int[][] dp = new int[n + 1][k + 1];
            int ans = max;
            for (int i = 0; i < k; i++) {
                cost[i] = Integer.parseInt(st.nextToken());
            }
            for (int i = 0; i <= n; i++) {
                Arrays.fill(dp[i], max);
            }
            for (int i = 0; i <= n; i++) {
                dp[i][0] = 0;
            }

            dp[0][0] = 0;
            for (int fIdx = 1; fIdx <= n; fIdx++) {
                for (int wIdx = 1; wIdx <= k; wIdx++) {
                    for (int w = 1; w <= k; w++) {
                        if (cost[w - 1] != -1 && wIdx - w >= 0) {
                            dp[fIdx][wIdx] = Math.min(dp[fIdx][wIdx], dp[fIdx - 1][wIdx - w] + cost[w - 1]);
                        }
                    }
                }
            }
            for (int fIdx = 1; fIdx <= n; fIdx++) {
                ans = Math.min(ans, dp[fIdx][k]);
            }
            System.out.println(ans == max ? -1 : ans);
        }
        br.close();
    }
}

