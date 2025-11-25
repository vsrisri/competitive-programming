// Incomplete - TLE 
import java.io.*;
import java.util.*;

public class TWENDS {
    public static int[] a;
    public static int n;
    public static int[][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        int tc = 1;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(line);
            n = Integer.parseInt(st.nextToken());
            if (n == 0) {
                break;
            }

            a = new int[n];
            dp = new int[n][n];
            int sum = 0;
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(st.nextToken());
                sum += a[i];
            }

            for (int i = 0; i < n; i++) {
                dp[i][i] = a[i];
            }

            for (int len = 2; len <= n; len++) {
                for (int l = 0; l + len - 1 < n; l++) {
                    int r = l + len - 1;
                    int takeLeft;
                    int takeRight;
                    if (a[l + 1] >= a[r]) {
                        takeLeft = a[l] + ((l + 2 <= r) ? dp[l + 2][r] : 0);
                    } else {
                        takeLeft = a[l] + ((l + 1 <= r - 1) ? dp[l + 1][r - 1] : 0);
                    }

                    if (a[l] >= a[r - 1]) {
                        takeRight = a[r] + ((l + 1 <= r - 1) ? dp[l + 1][r - 1] : 0);
                    } else {
                        takeRight = a[r] + ((l <= r - 2) ? dp[l][r - 2] : 0);
                    }

                    dp[l][r] = Math.max(takeLeft, takeRight);
                }
            }

            int first = dp[0][n - 1];
            int diff = first - (sum - first);
            sb.append("In game ").append(tc++).append(", the greedy strategy might lose by as many as ").append(diff).append(" points.\n");
        }
        System.out.print(sb.toString());
        br.close();
    }
}

