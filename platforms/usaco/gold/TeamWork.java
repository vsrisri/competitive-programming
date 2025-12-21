import java.util.*;
import java.io.*;

public class TeamWork {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(new File("teamwork.in"));
        PrintWriter out = new PrintWriter(new File("teamwork.out"));
        int n = stdin.nextInt();
        int k = stdin.nextInt();
        int[] input = new int[n];
        for (int idx = 0; idx < n; idx++) {
            input[idx] = stdin.nextInt();
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;

        for (int idx = 1; idx <= n; idx++) {
            int max = input[idx - 1];
            for (int idx2 = 1; idx2 <= k && idx - idx2 >= 0; idx2++) {
                max = Math.max(max, input[idx - idx2]);
                dp[idx] = Math.max(dp[idx], max * idx2 + dp[idx - idx2]);
            }
        }

        out.println(dp[n]);
        stdin.close();
        out.close();
    }
}

