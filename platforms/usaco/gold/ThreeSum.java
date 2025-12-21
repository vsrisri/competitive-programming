import java.io.*;
import java.util.*;

public class ThreeSum {
    public static final int shift = 1000000;
    public static final int MAX_N = 5000;
    public static long[] values = new long[MAX_N];
    public static long[][] dp = new long[MAX_N][MAX_N];
    public static int[] frequency = new int[2 * shift + 1];
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("threesum.in"));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("threesum.out")));
        int n = Integer.parseInt(tokenizer.nextToken());
        int q = Integer.parseInt(tokenizer.nextToken());
        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            values[i] = Long.parseLong(tokenizer.nextToken());
        }

        lookTriplets(n);
        findPSums(n);
        for (int i = 0; i < q; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(tokenizer.nextToken()) - 1;
            int end = Integer.parseInt(tokenizer.nextToken()) - 1;
            writer.println(dp[start][end]);
        }

        reader.close();
        writer.close();
    }

    public static void lookTriplets(int n) {
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                long target = shift - (values[i] + values[j]);
                if (target >= 0 && target <= 2 * shift) {
                    dp[i][j] += frequency[(int) target];
                }
                frequency[shift + (int) values[j]]++;
            }

            for (int j = i + 1; j < n; j++) {
                frequency[shift + (int) values[j]]--;
            }
        }
    }

    public static void findPSums(int n) {
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                dp[i][j] += (i + 1 < n ? dp[i + 1][j] : 0) + (j - 1 >= 0 ? dp[i][j - 1] : 0) - (i + 1 < n && j - 1 >= 0 ? dp[i + 1][j - 1] : 0);
            }
        }
    }
}

