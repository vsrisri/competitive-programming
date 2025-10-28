import java.util.*;
import java.io.*;

public class SuperPlumber {
    public static int m;
    public static int n;
    public static String[][] grid;
    public static int[][] coinArr;
    public static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            if (m == 0 && n == 0) {
                break;
            }
            grid = new String[m][n];
            coinArr = new int[m][n];
            dp = new int[m][n];
            for (int idx = 0; idx < m; idx++) {
                for (int idx2 = 0; idx2 < n; idx2++) {
                    dp[idx][idx2] = -1;
                }
            }

            for (int idx = 0; idx < m; idx++) {
                st = new StringTokenizer(reader.readLine());
                String s = st.nextToken();
                for (int idx2 = 0; idx2 < n; idx2++) {
                    String curr = String.valueOf(s.charAt(idx2));
                    if (!curr.equals(".") && !curr.equals("*")) {
                        coinArr[idx][idx2] = Integer.parseInt(curr);
                    }
                    grid[idx][idx2] = curr;
                }
            }

            dp[m - 1][0] = coinArr[m - 1][0];
            for (int idx = m - 2 ; idx >= 0; idx--) {
                if (!grid[idx][0].equals("*")) {
                    dp[idx][0] = dp[idx + 1][0] + coinArr[idx][0];
                } else {
                    break;
                }
            }

            System.out.println(helper());
        }
    }

    public static int helper() {
        for (int nIdx = 1 ; nIdx < n; nIdx++) {
            for (int mIdx = 0; mIdx < m; mIdx++) {
                if (!grid[mIdx][nIdx - 1].equals("*") && dp[mIdx][nIdx- 1 ] != -1) {
                    int a = dp[mIdx][nIdx - 1];
                    for (int idx3 = mIdx; idx3 < m; idx3++) {
                        if (!grid[idx3][nIdx].equals("*")) {
                            a += coinArr[idx3][nIdx];
                            if (a > dp[idx3][nIdx]) {
                                dp[idx3][nIdx] = a;
                            }
                        } else {
                            break; 
                        }
                    }
                }
            }

            for (int mIdx = m - 1; mIdx >= 0; mIdx--) {
                if (!grid[mIdx][nIdx - 1].equals("*") && dp[mIdx][nIdx - 1] != -1) {
                    int a = dp[mIdx][nIdx - 1];
                    for (int idx3 = mIdx; idx3 >= 0; idx3--) {
                        if (!grid[idx3][nIdx].equals("*")) {
                            a += coinArr[idx3][nIdx];
                            if (a > dp[idx3][nIdx]) {
                                dp[idx3][nIdx] = a;
                            }
                        } else {
                            break;
                        }
                    }
                }
            }
        }

        return dp[m - 1][n - 1];
    }
}
