import java.util.*;
import java.io.*;

public class RadioContact {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(new File("radio.in"));
        PrintWriter out = new PrintWriter(new File("radio.out"));
        int n = stdin.nextInt();
        int m = stdin.nextInt();
        int[][] jArr = new int[n + 1][2];
        for (int idx = 0; idx < 2; idx++) {
            jArr[0][idx] = stdin.nextInt();
        }

        int[][] bArr = new int[m + 1][2];
        for (int idx = 0; idx < 2; idx++) {
            bArr[0][idx] = stdin.nextInt();
        }
        String jPath = stdin.next();
        String bPath = stdin.next();
        fillArr(jArr, jPath);
        fillArr(bArr, bPath);
        int[][] dp = new int[n + 1][m + 1];
        for (int idx = 1; idx <= n; idx++) {
            dp[idx][0] = dp[idx - 1][0] + findEnergy(jArr[idx], bArr[0]);
        }
        for (int idx = 1; idx <= m; idx++) {
            dp[0][idx] = dp[0][idx - 1] + findEnergy(jArr[0], bArr[idx]);
        }

        for (int idx = 1; idx <= n; idx++) {
            for (int idx2 = 1; idx2 <= m; idx2++) {
                int currEnergy = findEnergy(jArr[idx], bArr[idx2]);
                int totalLost = dp[idx - 1][idx2 - 1] + currEnergy;
                int jLost = dp[idx - 1][idx2] + currEnergy;
                int bLost = dp[idx][idx2 - 1] + currEnergy;
                dp[idx][idx2] = Math.min(Math.min(totalLost, jLost), bLost);
            }
        }

        out.println(dp[n][m]);
        stdin.close();
        out.close();

    }

    public static int findEnergy(int[] a, int[] b) {
        return (a[0] - b[0]) * (a[0] - b[0]) + (a[1] - b[1]) * (a[1] - b[1]);
    }

    public static void fillArr(int[][] arr, String path) {
        for (int idx = 1; idx <= path.length(); idx++) {
            arr[idx][0] = arr[idx - 1][0];
            arr[idx][1] = arr[idx - 1][1];
            char c = path.charAt(idx - 1);
            if (c == 'N') {
                arr[idx][1]++;
            } else if (c == 'E') {
                arr[idx][0]++;
            } else if (c == 'S') {
                arr[idx][1]--;
            } else {
                arr[idx][0]--;
            }
        }
    }
}
