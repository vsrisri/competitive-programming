import java.io.*;
import java.util.*;

public class Candy {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(tokenizer.nextToken());
        int f = Integer.parseInt(tokenizer.nextToken());
        long t = Long.parseLong(tokenizer.nextToken());
        long[][] dp = new long[f + 1][((n * (n + 1)) / 2) + 1];
        for (int i = 0; i <= f; i++) {
            for (int j = 0; j < ((n * (n + 1)) / 2) + 1; j++) {
                dp[i][j] = Long.MIN_VALUE;
            }
        }

        dp[0][0] = 0;
        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 1; i <= n; i++) {
            int a = Integer.parseInt(tokenizer.nextToken());
            for (int j = (i * (i + 1)) / 2; j >= i; j--) {
                for (int k = f; k > 0; k--) {
                    if (dp[k - 1][j - i] != Long.MIN_VALUE) {
                        dp[k][j] = Math.max(dp[k][j], dp[k - 1][j - i] + a);
                    }
                }
            }
        }
        
        for (int i = 0; i <= (n * (n + 1)) / 2; i++) {
            if (dp[f][i] >= t) {
                System.out.println(Math.max(0, i - (f * (f + 1) / 2)));
                return;
            }
        }

        System.out.println("NO");
        reader.close();
    }
}

