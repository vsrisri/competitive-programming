import java.util.*;
import java.io.*;
public class FARIDA {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
        int tc = stdin.nextInt();
        for (int t = 1; t <= tc; t++) {
            int n = stdin.nextInt();
            long[] arr = new long[10001];
            for (int idx = 0; idx < n; idx++) {
                arr[idx] = stdin.nextLong();
            }
            long ans = 0;
            if (n > 0) {
                Long[] dp = new Long[10001];
                dp[0] = arr[0];
                dp[1] = Math.max(dp[0], arr[1]);
                for (int idx = 2; idx < n; idx++) {
                    dp[idx] = Math.max(dp[idx - 1], arr[idx] + dp[idx - 2]);
                }
                ans = dp[n - 1];
            }
            System.out.println("Case " + t + ": " + ans);
        }
        stdin.close();
    }
}
