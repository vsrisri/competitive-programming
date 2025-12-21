import java.util.*;
import java.io.*;

public class TamingTheHerd {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(new File("taming.in"));
        PrintWriter out = new PrintWriter(new File("taming.out"));
        int n = stdin.nextInt();
        int[] arr = new int[n];
        for (int idx = 0; idx < n; idx++) {
            arr[idx] = stdin.nextInt();
        }
        int[][] bad = new int[n][n];
        for (int idx = 0; idx < n; idx++) {
            int total = 0;
            for (int idx2 = 0; idx2 < n; idx2++) {
                if (idx2 - idx != arr[idx2]) {
                    total++;
                }
                bad[idx][idx2] = total;
            }
        }

        int[][] dp = new int[n][n];
        dp[0] = bad[0];
        for (int idx = 1; idx < n; idx++) {
            dp[idx][idx] = dp[idx - 1][idx - 1] + bad[idx][idx];
            for (int idx2 = idx + 1; idx2 < n; idx2++) {
                dp[idx][idx2] = dp[idx - 1][idx2 - 1] + bad[idx2][idx2];
                for (int idx3 = idx - 1; idx3 < idx2; idx3++) {
                    dp[idx][idx2] = Math.min(dp[idx][idx2], dp[idx - 1][idx3] + bad[idx3 + 1][idx2]);
                }
            }
        }

        for (int idx = 0; idx < n; idx++) {
            System.out.println(dp[idx][n - 1]);
        }


    }
}

