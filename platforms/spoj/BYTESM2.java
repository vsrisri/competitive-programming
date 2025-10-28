import java.util.*;
import java.io.*;
import java.math.*;

public class BYTESM2 {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
        int tc = stdin.nextInt();
        int[][] inArr = new int[105][105];
        int[][] dp = new int[105][105];
        for (int t = 0; t < tc; t++) {
            int curAns = -1;
            int n = stdin.nextInt();
            int m = stdin.nextInt();
            for (int idx = 0; idx < n; idx++) {
                for (int idx2 = 0; idx2 < m; idx2++) {
                    inArr[idx][idx2] = stdin.nextInt();
                }
            }
            for (int idx = 0; idx < 105; idx++) {
                Arrays.fill(dp[idx], -1);
            }
            for (int idx = 0; idx < m; idx++) {
                if (dpHelper(0, idx, n, m, inArr, dp) > curAns) {
                    curAns = dpHelper(0, idx, n, m, inArr, dp);
                }
            }
            System.out.println(curAns);
        }

    }

    public static int dpHelper(int idx, int idx2, int n, int m, int[][] inArr, int[][] dp) {
        if (idx > n || idx2 > m || idx < 0 || idx2 < 0) {
            return 0;
        }

        if (dp[idx][idx2] == -1) {
            dp[idx][idx2] = inArr[idx][idx2] + Math.max(dpHelper(idx + 1, idx2 - 1, n, m, inArr, dp), Math.max(dpHelper(idx + 1, idx2, n, m, inArr, dp), dpHelper(idx + 1, idx2 + 1, n, m, inArr, dp)));
        }
        return dp[idx][idx2];
    }
}

