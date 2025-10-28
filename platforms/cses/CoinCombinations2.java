//CSES
import java.util.*;
import java.io.*;

public class CoinCombinations2 {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        int x = stdin.nextInt();
        final int MOD = (int)(1e9 + 7);
        int[] coins = new int[n];
        for (int idx = 0; idx < n; idx++) {
            coins[idx] = stdin.nextInt();
        }

        int[] dp = new int[1000001];
        dp[0] = 1;

        for (int idx = 1; idx <= n; idx++) {
            for (int total = 0; total <= x; total++) {
                if (total - coins[idx - 1] >= 0) {
                    dp[total] += dp[total - coins[idx - 1]];
                    dp[total] %= MOD;
                }


            }
        }
        System.out.println(dp[x]);
        stdin.close();



    }
}

