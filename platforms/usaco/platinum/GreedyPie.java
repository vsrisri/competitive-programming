import java.io.*;
import java.util.*;

public class GreedyPie {
    public static long n, m;
    public static long[][][] maxArr = new long[300][300][300];
    public static long[][] dp = new long[300][300];
    public static long[] w = new long[300 * (300 + 1) / 2];
    public static long[] l = new long[300 * (300 + 1) / 2];
    public static long[] r = new long[300 * (300 + 1) / 2];
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("pieaters.in"));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("pieaters.out")));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        n = Long.parseLong(st.nextToken());
        m = Long.parseLong(st.nextToken());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(reader.readLine());
            w[i] = Long.parseLong(st.nextToken());
            l[i] = Long.parseLong(st.nextToken()) - 1;
            r[i] = Long.parseLong(st.nextToken()) - 1;
            for (int j = (int) l[i]; j <= r[i]; j++) {
                if (maxArr[j][(int) l[i]][(int) r[i]][0] < w[i]){
                    maxArr[j][(int) l[i]][(int) r[i]][0] = w[i];
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = i; j >= 0; j--) {
                for (int k = i; k < n; k++) {
                    if (j > 0) {
                        if (maxArr[i][j - 1][k][0] < maxArr[i][j][k]) {
                            maxArr[i][j - 1][k][0] = maxArr[i][j][k];
                        }
                    }
                    if (k < n - 1) {
                        if (maxArr[i][j][k + 1][0] < maxArr[i][j][k + 1]) {
                            maxArr[i][j][k + 1][0] = maxArr[i][j][k + 1];
                        }
                    }
                }
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                for (int k = i; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k + 1][j]);
                }
                for (int k = i; k <= j; k++) {
                    dp[i][j] = Math.max(dp[i][j], (k > i ? dp[i][k - 1] : 0) + maxArr[k][i][j] + (k < j ? dp[k + 1][j] : 0));
                }
            }
        }

        writer.println(dp[0][(int) n - 1]);
        reader.close();
        writer.close();
    }
}
