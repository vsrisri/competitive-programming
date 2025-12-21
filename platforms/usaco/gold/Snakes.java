import java.util.*;
import java.io.*;

public class Snakes {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(new File("snakes.in"));
        int n = stdin.nextInt();
        int k = stdin.nextInt();
        int[] grid = new int[n];
        for (int i = 0; i < n; i++) {
            grid[i] = stdin.nextInt();
        }

        int[][] dp = new int[k + 1][n];
        dp[0][0] = 0;

        int max = grid[0];
        int sum = grid[0];
        for (int i = 0; i < n; i++) {
            sum+= grid[i];
            max = Math.max(max, grid[i]);
            dp[0][i] = (i + 1) * max - sum;
        }

        for (int i = 1; i <= k; i++) {
            for (int j = i + 1 ; j < n; j++) {
                int count = 1;
                dp[i][j] = dp[i - 1][j- 1];
                max = grid[j];
                sum = grid[j];
                for (int l = j - 1; l >= i; l--) {
                    sum += grid[l];
                    max = Math.max(max, grid[l]);
                    count++;
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][l - 1] + count * max - sum);
                }
            }
        }

        PrintWriter out = new PrintWriter(new File("snakes.out"));
        out.println(dp[k][n - 1]);
        stdin.close();
        out.close();
    }
}
