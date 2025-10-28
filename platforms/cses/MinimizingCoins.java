import java.util.*;
import java.util.*;

public class MinimizingCoins {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        int x = stdin.nextInt();
        int[] coins = new int[n];
        for (int idx = 0; idx < n; idx++) {
            coins[idx] = stdin.nextInt();
        }

        int[] dp = new int[1000001];
        Arrays.fill(dp, 1000001);
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= x; j++) {
                if (j - coins[i] >= 0) {
                    dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                }
            }
        }

        int ans = dp[x];
        if (ans == 1000001) {
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }
        stdin.close();
    }

}
