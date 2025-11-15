import java.io.*;
import java.util.*;

public class ROCK {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());
        StringBuilder out = new StringBuilder();
        for (int tc = 0; tc < t; tc++) {
            int n = Integer.parseInt(br.readLine());
            String s = br.readLine().trim();
            int[] pref = new int[n+1];
            for (int i = 1; i <= n; i++) {
                pref[i] = pref[i-1] + (s.charAt(i-1)=='1' ? 1 : -1);
            }
            int[] dp = new int[n+1];
            for (int i = 1; i <= n; i++) {
                dp[i] = dp[i-1];
                for (int j = 0; j < i; j++) {
                    if (pref[i] - pref[j] > 0) {
                        dp[i] = Math.max(dp[i], dp[j] + (i-j));
                    }
                }
            }
            out.append(dp[n]).append('\n');
        }
        System.out.print(out);
        br.close();
    }
}

