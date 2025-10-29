import java.io.*;
import java.util.*;

public class ASSIGN {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int c = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < c; tc++) {
            int n = Integer.parseInt(br.readLine());
            int[] pref = new int[n];
            long[] dp = new long[1 << n];
            dp[0] = 1;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    if (st.nextToken().equals("1")) {
                        pref[i] |= (1 << j);
                    }
                }
            }

            for (int mask = 0; mask < (1 << n); mask++) {
                int curr = Integer.bitCount(mask);
                if (curr == n) {
                    continue;
                }

                for (int topic = 0; topic < n; topic++) {
                    if ((pref[curr] & (1 << topic)) != 0 && (mask & (1 << topic)) == 0) {
                        dp[mask | (1 << topic)] += dp[mask];
                    }
                }
            }

            System.out.println(dp[(1 << n) - 1]);
        }
        br.close();
    }
}

