import java.util.*;
import java.io.*;

public class TimeIsMooney {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(new File("time.in"));
        PrintWriter pw = new PrintWriter(new File("time.out"));
        int n = stdin.nextInt();
        int m = stdin.nextInt();
        int c = stdin.nextInt();
        int[] monEarned = new int[n];
        for (int idx = 0; idx < n; idx++) {
            monEarned[idx] = stdin.nextInt();
        }

        ArrayList[] input = new ArrayList[n];
        for (int idx = 0; idx < n; idx++) {
            input[idx] = new ArrayList<Integer>();
        }
        for (int idx = 0; idx < m; idx++) {
            int a = stdin.nextInt() - 1;
            int b = stdin.nextInt() - 1;
            input[b].add(a);
        }

        int[][] dp = new int[1001][n];
        for (int i = 0; i <= 1000; i++) {
            Arrays.fill(dp[i], Integer.MIN_VALUE);
        }

        dp[0][0] = 0;
        int ans = 0;
        for (int time = 1; time <= 1000; time++) {
            for (int v = 0; v < n; v++) {
                for (int curr : (ArrayList<Integer>) input[v]) {
                    if (dp[time - 1][curr] < 0) {
                        continue;
                    }
                    dp[time][v] = Math.max(dp[time][v], dp[time - 1][curr] + monEarned[v]);
                }
            }
        }

        for (int i = 0; i <= 1000; i++) {
            ans = Math.max(ans, dp[i][0] - (c * i * i));
        }

        pw.println(ans);
        stdin.close();
        pw.close();
    }
}
