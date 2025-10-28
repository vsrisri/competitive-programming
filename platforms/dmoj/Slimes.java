import java.io.*;
import java.util.*;

public class Slimes {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        int[] slimes = new int[n];
        for (int i = 0; i < n; i++) {
            slimes[i] = Integer.parseInt(tokenizer.nextToken());
        }

        if (n == 1) {
            System.out.println(0);
            return;
        }

        long[][] dp = new long[n][n];
        long[] prefixSum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + slimes[i];
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                dp[i][j] = Long.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + prefixSum[j + 1] - prefixSum[i]);
                }
            }
        }

        System.out.println(dp[0][n - 1]);
        br.close();
    }
}
