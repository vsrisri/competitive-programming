//CSES
import java.util.*;
import java.io.*;

public class MoneySums {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        int[] coins = new int[n];
        for (int idx = 0; idx < n; idx++) {
            coins[idx] = stdin.nextInt();
        }

        boolean[][] dp = new boolean[101][(int)1e5 + 1];
        dp[0][0] = true;

        for (int i = 1; i <= n; i++) {
            for (int sum = 0; sum <= 1e5; sum++) {
                dp[i][sum] = dp[i - 1][sum];
                int prev = sum - coins[i - 1];
                if (prev >= 0 && dp[i - 1][prev]) {
                    dp[i][sum] = true;
                }
            }
        }

        ArrayList<Integer> ans = new ArrayList<Integer>();
        for (int idx = 1; idx < (int)1e5 + 1; idx++) {
            if (dp[n][idx]) {
                ans.add(idx);
            }
        }
        System.out.println(ans.size());
        for (int num : ans) {
            System.out.print(num + " ");
        }
        stdin.close();
    }
}

