//CSES
import java.util.*;
import java.io.*;

public class RectangleCutting {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
        int a = stdin.nextInt();
        int b = stdin.nextInt();
        int[][] dp = new int[a + 1][b + 1];

        for (int idx = 0; idx <= a; idx++) {
            for (int idx2 = 0; idx2 <= b; idx2++) {
                if (idx == idx2) {
                    dp[idx][idx2] = 0;
                } else {
                    dp[idx][idx2] = 10000000;
                    for (int idx3 = 1; idx3 < idx; idx3++) {
                        dp[idx][idx2] = Math.min(dp[idx][idx2], dp[idx3][idx2] + dp[idx - idx3][idx2] + 1);
                    }
                    for (int idx3 = 1; idx3 < idx2; idx3++) {
                        dp[idx][idx2] = Math.min(dp[idx][idx2], dp[idx][idx3] + dp[idx][idx2 - idx3] + 1);
                    }
                }
            }
        }

        System.out.println(dp[a][b]);
        stdin.close();
    }
}

