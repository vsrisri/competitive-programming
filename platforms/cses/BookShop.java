//CSES
import java.util.*;
import java.io.*;

public class BookShop {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        int x = stdin.nextInt();
        int[] prices = new int[n];
        int[] pages = new int[n];

        for (int idx = 0; idx < n; idx++) {
            prices[idx] = stdin.nextInt();
        }

        for (int idx = 0; idx < n; idx++) {
            pages[idx] = stdin.nextInt();
        }

        int[][] dp = new int[n + 1][x + 1];
        Arrays.fill(dp[0], 0);

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= x; j++) {
              dp[i][j] = dp[i - 1][j];
              int amtLeft = j - prices[i-1];
              if (amtLeft >= 0) {
                dp[i][j] = Math.max(dp[i][j], dp[i - 1][amtLeft] + pages[i - 1]);
              }
            }
        }
        System.out.println(dp[n][x]);
    }
}

