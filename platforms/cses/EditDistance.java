//CSES
import java.util.*;
import java.io.*;

public class EditDistance {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
        String str1 = stdin.nextLine();
        String str2 = stdin.nextLine();

        int[][] dp = new int[str1.length() + 1][str2.length() + 1];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        dp[0][0] = 0;
        for (int idx = 0; idx <= str1.length(); idx++) {
            for (int idx2 = 0; idx2 <= str2.length(); idx2++) {
                if (idx > 0) {
                    dp[idx][idx2] = Math.min(dp[idx][idx2], dp[idx - 1][idx2] + 1);
                }
                if (idx2 > 0) {
                    dp[idx][idx2] = Math.min(dp[idx][idx2], dp[idx][idx2 - 1] + 1);
                }
                if (idx > 0 && idx2 > 0) {
                    int a = ((str1.charAt(idx - 1) == str2.charAt(idx2 - 1)) ? 0 : 1);
                    int possAns = dp[idx - 1][idx2 - 1] + a;
                    dp[idx][idx2] = Math.min(dp[idx][idx2], possAns);
                }
            }
        }

        System.out.println(dp[str1.length()][str2.length()]);
        stdin.close();
    }
}

