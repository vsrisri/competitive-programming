import java.io.*;
import java.util.*;

public class GNY07H {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        int t = Integer.parseInt(br.readLine().trim());
        int maxW = 1000;
        int[] dp = new int[maxW + 1];
        dp[0] = 1;
        if (maxW >= 1) {
            dp[1] = 1;
        }
        if (maxW >= 2) {
            dp[2] = 5;
        }
        if (maxW >= 3) {
            dp[3] = 11;
        }

        for (int i = 4; i <= maxW; i++) {
            dp[i] = dp[i - 1] + 5 * dp[i - 2] + dp[i - 3] - dp[i - 4];
        }

        for (int i = 1; i <= t; i++) {
            int w = Integer.parseInt(br.readLine().trim());
            out.append(i).append(" ").append(dp[w]).append('\n');
        }
        System.out.print(out.toString());
        br.close();
    }
}

