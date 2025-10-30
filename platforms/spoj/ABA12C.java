// Incomplete
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
            int[] price = new int[k + 1];
            int[][] dp = new int[k + 1][n + 1];
            int ans = Integer.MAX_VALUE;
            dp[0][0] = 0;
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= k; i++) {
                price[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i <= k; i++) {
                Arrays.fill(dp[i], Integer.MAX_VALUE / 2);
            }

            for (int i = 1; i <= k; i++) {
                for (int j = 1; j <= n; j++) {
                    for (int p = 1; p <= k; p++) {
                        if (price[p] != -1 && i - p >= 0) {
                            dp[i][j] = Math.min(dp[i][j], dp[i - p][j - 1] + price[p]);
                        }
                    }
                }
            }
            for (int j = 1; j <= n; j++) {
                ans = Math.min(ans, dp[k][j]);
            }

            System.out.println(ans >= Integer.MAX_VALUE / 2 ? -1 : ans);
        }
        br.close();
    }
}

