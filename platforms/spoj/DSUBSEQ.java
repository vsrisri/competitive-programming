import java.io.*;
import java.util.*;

public class DSUBSEQ {
    public static final int mod = 1000000007;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < t; tc++) {
            String s = br.readLine();
            int n = s.length();
            long[] dp = new long[n + 1];
            int[] last = new int[256];
            Arrays.fill(last, -1);
            dp[0] = 1;

            for (int i = 1; i <= n; i++) {
                dp[i] = (2 * dp[i - 1]) % mod;
                char c = s.charAt(i - 1);

                if (last[c] != -1) {
                    dp[i] = (dp[i] - dp[last[c] - 1] + mod) % mod;
                }
                last[c] = i;
            }
            sb.append(dp[n] % mod).append("\n");
        }
        System.out.print(sb.toString());
        br.close();
    }
}

