import java.io.*;
import java.util.*;

public class PARTY {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int budget = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            if (budget == 0 && n == 0) {
                break;
            }

            int[] cost = new int[n];
            int[] fun = new int[n];
            int[][] dp = new int[n + 1][budget + 1];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                cost[i] = Integer.parseInt(st.nextToken());
                fun[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i <= n; i++) {
                for (int w = 0; w <= budget; w++) {
                    if (cost[i - 1] <= w) {
                        dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - cost[i - 1]] + fun[i - 1]);
                    } else {
                        dp[i][w] = dp[i - 1][w];
                    }
                }
            }

            int maxFun = dp[n][budget];
            int minCost = 0;
            for (int i = 0; i <= budget; i++) {
                if (dp[n][i] == maxFun) {
                    minCost = i;
                    break;
                }
            }

            System.out.println(minCost + " " + maxFun);
            br.readLine();
        }
        br.close();
    }
}

