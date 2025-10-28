//CSES
import java.util.*;
import java.io.*;

public class CoinCombinations1 {
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
        //Arrays.fill(dp, 1000001);
        dp[0] = 1;
        for (int i = 0; i <= x; i++) {
            for (int j = 0; j < n; j++) {
                if (i - coins[j] >= 0) {
                    dp[i] += dp[i - coins[j]];
                    dp[i] %= MOD;
                }
            }
        }

        System.out.println(dp[x]);
        stdin.close();
    }
}
