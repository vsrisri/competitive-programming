import java.io.*;
import java.util.*;

public class CowRun {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cowrun.in"));
        PrintWriter pw = new PrintWriter("cowrun.out");
        int numCows = Integer.parseInt(br.readLine());
        int[] pos = new int[numCows];
        for (int i = 0; i < numCows; i++) {
            pos[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(pos);
        br.close();

        int[][][] dp = new int[numCows + 1][numCows + 1][2];
        for (int i = 0; i <= numCows; i++) {
            for (int j = 0; j <= numCows; j++) {
                dp[i][j][0] = Integer.MAX_VALUE;
                dp[i][j][1] = Integer.MAX_VALUE;
            }
        }

        for (int i = numCows - 1; i >= 0; i--) {
            for (int j = i; j < numCows; j++) {
                if (i == j) {
                    dp[i][j][0] = Math.abs(pos[i]) * numCows;
                    dp[i][j][1] = Math.abs(pos[j]) * numCows;
                } else {
                    int remainingCows = numCows - (j - i);

                    dp[i][j][0] = Math.min(
                        dp[i + 1][j][0] + (Math.abs(pos[i + 1] - pos[i]) * remainingCows),
                        dp[i + 1][j][1] + (Math.abs(pos[j] - pos[i]) * remainingCows)
                    );

                    dp[i][j][1] = Math.min(
                        dp[i][j - 1][1] + (Math.abs(pos[j - 1] - pos[j]) * remainingCows),
                        dp[i][j - 1][0] + (Math.abs(pos[j] - pos[i]) * remainingCows)
                    );
                }
            }
        }

        pw.println(Math.min(dp[0][numCows - 1][0], dp[0][numCows - 1][1]));
        pw.close();
    }
}
