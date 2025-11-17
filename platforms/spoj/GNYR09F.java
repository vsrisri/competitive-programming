// Incomplete
import java.io.*;
import java.util.*;

public class GNYR09F {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int P = Integer.parseInt(st.nextToken());
        int maxN = 100;
        int maxK = maxN - 1;
        int[][][] dp = new int[2][maxK + 1][maxN + 1];
        dp[0][0][1] = 1;  
        dp[1][0][1] = 1;
        for (int len = 2; len <= maxN; len++) {
            for (int k = 0; k <= maxK; k++) {
                dp[0][k][len] = dp[0][k][len - 1] + dp[1][k][len - 1];
                if (k > 0) {
                    dp[1][k][len] = dp[0][k - 1][len - 1] + dp[1][k][len - 1];
                } else {
                    dp[1][k][len] = dp[0][k][len - 1];
                }
            }
        }
        
        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(reader.readLine());
            int dataset = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            int ans = dp[0][k][n] + dp[1][k][n];
            System.out.println(dataset + " " + ans);
        }
    }
}

