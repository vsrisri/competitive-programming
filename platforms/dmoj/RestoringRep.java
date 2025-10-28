import java.io.*;
import java.util.*;

public class RestoringRep {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inArr = br.readLine().trim().split(" ");
        int D = Integer.parseInt(inArr[0]);
        int I = Integer.parseInt(inArr[1]);
        int R = Integer.parseInt(inArr[2]);
        R = (D + I < R) ? D + I : R;

        String[] words = br.readLine().trim().split(" ");
        String w1 = words[0];
        String w2 = words[1];
        int[][] dp = new int[w2.length() + 1][w1.length() + 1];
        for (int i = 0; i <= w1.length(); i++) {
            dp[0][i] = i * D;
        }

        for (int i = 0; i <= w2.length(); i++) {
            dp[i][0] = i * I;
        }
        for (int i = 1; i <= w2.length(); i++) {
            for (int j = 1; j <= w1.length(); j++) {
                if (w1.charAt(j - 1) == w2.charAt(i - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(D + dp[i][j - 1], Math.min(I + dp[i - 1][j], R + dp[i - 1][j - 1]));
                }
            }
        }

        System.out.println(dp[w2.length()][w1.length()]);
        br.close();
    }
}

