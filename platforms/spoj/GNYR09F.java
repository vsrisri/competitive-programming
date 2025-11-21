import java.io.*;
import java.util.*;

public class GNYR09F {
    public static int[][][] dp = new int[101][101][2];
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int P = Integer.parseInt(reader.readLine());
        helper();
        StringBuilder out = new StringBuilder();
        for (int p = 0; p < P; p++) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            int dataset = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            out.append(dataset).append(" ").append(dp[n][k][0] + dp[n][k][1]).append("\n");
        }
        System.out.print(out);
        reader.close();
    }

    public static void helper() {
        dp[1][0][0] = 1; 
        dp[1][0][1] = 1; 
        for (int len = 2; len <= 100; len++) {
            for (int k = 0; k <= len - 1; k++) {
                dp[len][k][0] = dp[len - 1][k][0] + dp[len - 1][k][1];
                if (k > 0) {
                    dp[len][k][1] = dp[len - 1][k][0] + dp[len - 1][k - 1][1];
                } else {
                    dp[len][k][1] = dp[len - 1][k][0]; 
                }
            }
        }
    }
}

