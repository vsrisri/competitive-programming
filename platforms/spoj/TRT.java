import java.io.*;
import java.util.*;

public class TRT {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] v = new int[n];
        for (int i = 0; i < n; i++) {
            v[i] = Integer.parseInt(br.readLine());
        }
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = v[i] * n;
        }
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                int age = n - (j - i);
                dp[i][j] = Math.max(v[i] * age + dp[i + 1][j], v[j] * age + dp[i][j - 1]);
            }
        }
        System.out.println(dp[0][n - 1]);
        br.close();
    }
}

