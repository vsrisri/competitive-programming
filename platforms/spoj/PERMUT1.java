import java.io.*;
import java.util.*;

public class PERMUT1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int d = Integer.parseInt(br.readLine());
        int[][] dp = new int[13][100];
        dp[0][0] = 1;
        for (int n = 1; n <= 12; n++) {
            for (int k = 0; k <= 98; k++) {
                for (int i = 0; i < n && i <= k; i++) {
                    dp[n][k] += dp[n - 1][k - i];
                }
            }
        }
        for (int tc = 0; tc < d; tc++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            sb.append(dp[n][k]).append("\n");
        }
        System.out.print(sb);
        br.close();
    }
}

