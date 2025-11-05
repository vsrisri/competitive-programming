// Incomplete 
import java.io.*;
import java.util.*;

public class SQRBR {
    public static boolean[] fixed;
    public static long[][] dp;
    public static int n;

    public static long helper(int pos, int open) {
        long ans = 0;
        if (open < 0) {
            return 0;
        }
        if (pos == 2 * n) {
            return open == 0 ? 1 : 0;
        }
        if (dp[pos][open] != -1) {
            return dp[pos][open];
        }
        if (fixed[pos]) {
            ans = helper(pos + 1, open + 1);
        } else {
            ans += helper(pos + 1, open + 1);
            if (open > 0) {
                ans += helper(pos + 1, open - 1);
            }
        }
        return dp[pos][open] = ans;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int D = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < D; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            fixed = new boolean[2 * n];
            Arrays.fill(fixed, false);
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < k; i++) {
                fixed[Integer.parseInt(st.nextToken()) - 1] = true;
            }
            dp = new long[2 * n + 1][n + 1];
            for (long[] row : dp) {
                Arrays.fill(row, -1);
            }
            sb.append(helper(0, 0)).append('\n');
        }
        System.out.print(sb);
        br.close();
    }
}

