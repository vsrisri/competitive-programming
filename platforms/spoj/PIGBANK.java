import java.util.*;
import java.io.*;

public class PIGBANK {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
        int tc = stdin.nextInt();
        for (int t = 0; t < tc; t++) {
            int e = stdin.nextInt();
            int f = stdin.nextInt() - e;
            int n = stdin.nextInt();
            int[][] coins = new int[n][2];
            for (int idx = 0; idx < n; idx++) {
                coins[idx][0] = stdin.nextInt();
                coins[idx][1] = stdin.nextInt();
            }
            int[] dp = new int[10000];
            Arrays.fill(dp, -1);
            dp[0] = 0;
            for (int idx = 0; idx <= f; idx++) {
                for (int coin = 0; coin < n; coin++) {
                    if (idx >= coins[coin][1] && dp[idx - coins[coin][1]] != -1 && (dp[idx] == -1 || coins[coin][0] + dp[idx - coins[coin][1]] <= dp[idx])) {
                        dp[idx] = coins[coin][0] + dp[idx - coins[coin][1]];
                    }
                }
            }
            if (dp[f] == -1) {
                System.out.println("This is impossible.");
            } else {
                System.out.println("The minimum amount of money in the piggy-bank is " + dp[f] + ".");
            }
        }
        stdin.close();
    }
}
