import java.util.*;
import java.io.*;

public class CowChecklist {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(new File("checklist.in"));
        PrintWriter pw = new PrintWriter(new File("checklist.out"));
        int h = stdin.nextInt();
        int g = stdin.nextInt();
        int[][] hArr = new int[h][2];
        int[][] gArr = new int[g][2];
        for (int idx = 0; idx < h; idx++) {
            hArr[idx][0] = stdin.nextInt(); 
            hArr[idx][1] = stdin.nextInt(); 
        }
        for (int idx = 0; idx < g; idx++) {
            gArr[idx][0] = stdin.nextInt(); 
            gArr[idx][1] = stdin.nextInt(); 
        }
        int[][][] dp = new int[h + 1][g + 1][2];
        for (int idx = 0; idx <= h; idx++) {
            for (int idx2 = 0; idx2 <= g; idx2++) {
                Arrays.fill(dp[idx][idx2], 1000000000);
            }
        }
        dp[0][0][0] = 0;
        dp[1][0][0] = 0;
        for (int i = 1; i <= h; i++) {
            for (int j = 0; j <= g; j++) {
                if (i == 1 && j == 0) {
                    continue;
                }
                if (i > 1) {
                    dp[i][j][0] = Math.min(dp[i][j][0], dp[i - 1][j][0] + distHelper(hArr[i - 2], hArr[i - 1]));
                }
                if (i > 0 && j > 0) {
                    dp[i][j][0] = Math.min(dp[i][j][0], dp[i - 1][j][1] + distHelper(gArr[j - 1], hArr[i - 1]));
                }
                if (j > 1) {
                    dp[i][j][1] = Math.min(dp[i][j][1], dp[i][j - 1][1] + distHelper(gArr[j - 2], gArr[j - 1]));
                }
                if (i > 0 && j > 0) {
                    dp[i][j][1] = Math.min(dp[i][j][1], dp[i][j - 1][0] + distHelper(hArr[i - 1], gArr[j - 1]));
                }
            }
        }
        if (h > 1) {
            pw.println(dp[h][g][0]);
        } else {
            int ans = distHelper(hArr[0], gArr[0]);
            for (int idx = 1; idx < g; idx++) {
                ans+= distHelper(gArr[idx - 1], gArr[idx]);
            }
            System.out.println(ans);

        }
        stdin.close();
        pw.close();
    }

    public static int distHelper(int[] arr1, int[] arr2) {
        return (arr1[0] - arr2[0]) * (arr1[0] - arr2[0]) + (arr1[1] - arr2[1]) * (arr1[1] - arr2[1]);
    }
}
