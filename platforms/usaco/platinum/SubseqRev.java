import java.io.*;
import java.util.*;

public class SubseqRev {
    static int[][][][] dp = new int[50][50][51][51];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("subrev.in"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("subrev.out"));
        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(br.readLine());
        }

        for (int l = n - 1; l >= 0; l--) {
            for (int i = 0; i <= a[l]; i++) {
                for (int j = a[l]; j <= 50; j++) {
                    dp[l][l][i][j] = 1;
                }
            }

            for (int r = l + 1; r < n; r++) {
                for (int lower = 0; lower <= 50; lower++) {
                    for (int upper = lower; upper <= 50; upper++) {
                        dp[l][r][lower][upper] = Math.max(dp[l][r][lower][upper], dp[l + 1][r - 1][lower][upper]);
                        dp[l][r][lower][upper] = Math.max(dp[l][r][lower][upper], dp[l + 1][r][lower][upper]);
                        dp[l][r][lower][upper] = Math.max(dp[l][r][lower][upper], dp[l][r - 1][lower][upper]);
                    }
                }

                for (int upper = a[l]; upper <= 50; upper++) {
                    dp[l][r][a[l]][upper] = Math.max(dp[l][r][a[l]][upper], dp[l + 1][r][a[l]][upper] + 1);
                }

                for (int lower = 0; lower <= a[r]; lower++) {
                    dp[l][r][lower][a[r]] = Math.max(dp[l][r][lower][a[r]], dp[l][r - 1][lower][a[r]] + 1);
                }

                for (int lower = 1; lower <= 50; lower++) {
                    for (int upper = lower; upper <= 50; upper++) {
                        int maxLen = dp[l + 1][r - 1][lower][upper];
                        if (a[l] >= upper) {
                            dp[l][r][lower][a[l]] = Math.max(dp[l][r][lower][a[l]], maxLen + 1);
                        }

                        if (a[r] <= lower) {
                            dp[l][r][a[r]][upper] = Math.max(dp[l][r][a[r]][upper], maxLen + 1);
                        }

                        if (a[r] <= lower && a[l] >= upper) {
                            dp[l][r][a[r]][a[l]] = Math.max(dp[l][r][a[r]][a[l]], maxLen + 2);
                        }
                    }
                }

                for (int i = 50; i >= 1; i--) {
                    for (int j = i; j <= 50; j++) {
                        dp[l][r][i][j] = Math.max(dp[l][r][i][j], dp[l][r][i + 1][j - 1]);
                        dp[l][r][i][j] = Math.max(dp[l][r][i][j], dp[l][r][i + 1][j]);
                        dp[l][r][i][j] = Math.max(dp[l][r][i][j], dp[l][r][i][j - 1]);
                    }
                }
            }
        }
        
        bw.write(String.valueOf(dp[0][n - 1][1][50]));
        bw.newLine();
        bw.close();
    }
}
