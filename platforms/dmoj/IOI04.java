import java.util.*;
import java.io.*;

public class IOI04 {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
        int w = stdin.nextInt();
        int h = stdin.nextInt();
        int n = stdin.nextInt();
        int[][] arr = new int[603][603];
        int[][] dp = new int[603][603];
        for (int idx = 1; idx <= n; idx++) {
            int x = stdin.nextInt();
            int y = stdin.nextInt();
            arr[x][y] = -1;
        }

        for (int idx = 1; idx <= w; idx++) {
            for (int idx2 = 1; idx2 <= h; idx2++) {
                if (arr[idx][idx2] == -1) {
                    continue;
                }
                dp[idx][idx2] = idx * idx2;
                int a = idx / 2;
                for (int i = 1; i <= a; i++) {
                    dp[idx][idx2] = Math.min(dp[idx][idx2], dp[i][idx2] + dp[idx - i][idx2]);
                }
                int b = idx2 / 2;
                for (int i = 1; i <= b; i++) {
                    dp[idx][idx2] = Math.min(dp[idx][idx2], dp[idx][i] + dp[idx][idx2 - i]);
                }
            }
        }

        System.out.println(dp[w][h]);
        stdin.close();
    }

}
