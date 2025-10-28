import java.io.*;
import java.util.*;

public class BitFlips {
    public static int[][] bitArr;
    public static int[][][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        bitArr = new int[n][30];
        dp = new int[n][n][30];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int k = 0; k < 30; k++) {
            for (int i = 0; i < n; i++) {
                bitArr[i][k] = (i > 0 ? bitArr[i - 1][k] : 0) + ((arr[i] & (1 << k)) > 0 ? 1 : 0);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                dp[i][j][0] = Math.min(findSet(i, j, 0), findUnset(i, j, 0));
                for (int k = i; k < j; k++) {
                    dp[i][j][0] = Math.min(dp[i][j][0], findSet(i, k, 0) + findUnset(k + 1, j, 0));
                }
            }
        }

        helper(n);
        System.out.println(dp[0][n - 1][29]);
    }

    public static void helper(int n) {
        for (int k = 0; k < 29; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = i; j < n; j++) {
                    dp[i][j][k + 1] = dp[i][j][k] + Math.min(findSet(i, j, k + 1), findUnset(i, j, k + 1));
                    for (int mid = i; mid < j; mid++) {
                        dp[i][j][k + 1] = Math.min(dp[i][j][k + 1], dp[i][mid][k] + dp[mid + 1][j][k] + findSet(i, mid, k + 1) + findUnset(mid + 1, j, k + 1));
                    }
                }
            }
        }
    }

    public static int findSet(int i, int j, int k) {
        return bitArr[j][k] - (i > 0 ? bitArr[i - 1][k] : 0);
    }

    public static int findUnset(int i, int j, int k) {
        return (j - i + 1) - findSet(i, j, k);
    }
}
