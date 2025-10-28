import java.io.*;
import java.util.*;

public class MIXTURES {
    public static int[][] color;
    public static int[][] dp;
    public static int[] arr;
    public static int n;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        while (line != null) {
            line = line.trim();
            if (line.isEmpty()) {
                continue;
            }
            n = Integer.parseInt(line);
            arr = new int[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            dp = new int[n][n];
            color = new int[n][n];
            for (int[] d : dp) {
                Arrays.fill(d, -1);
            }
            for (int i = 0; i < n; i++) {
                color[i][i] = arr[i];
            }
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    color[i][j] = (color[i][j - 1] + arr[j]) % 100;
                }
            }
            System.out.println(helper(0, n - 1));
            line = br.readLine();
        }
    }

    public static int helper(int i, int j) {
        int ans = Integer.MAX_VALUE;
        if (i >= j) {
            return 0;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        for (int k = i; k < j; k++) {
            int left = helper(i, k);
            int right = helper(k + 1, j);
            int smoke = left + right + color[i][k] * color[k + 1][j];
            ans = Math.min(ans, smoke);
        }
        return dp[i][j] = ans;
    }

}
