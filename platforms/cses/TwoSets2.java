import java.util.*;
import java.io.*;

public class TwoSets2 {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        final double mod = 1e9 + 7;
        int sum = n * (n + 1) / 2;
        if (sum % 2 != 0) {
            System.out.println(0);
        } else {
            sum /= 2;
            int[][] dp = new int[n + 1][sum + 1];
            dp[0][0] = 1;

            for (int idx = 1; idx < n; idx++) {
                for (int idx2 = 0; idx2 <= sum; idx2++) {
                    dp[idx][idx2] = dp[idx - 1][idx2];
                    if (idx2 - idx >= 0) {
                        dp[idx][idx2] = dp[idx - 1][idx2] + dp[idx - 1][idx2 - idx];
                        dp[idx][idx2] %= mod;
                    }
                }
            }
            System.out.println(dp[n - 1][sum]);
        }
    }
}

