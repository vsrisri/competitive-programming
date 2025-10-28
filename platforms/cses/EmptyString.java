import java.util.*;
import java.io.*;

public class EmptyString {
    static final int MOD = 1000000007;
    static long[][] dp = new long[502][502];
    static long[][] binom = new long[502][502];
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int n = s.length();
        
        for (int i = 0; i <= n; i++) {
            binom[i][0] = 1;
        }
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                binom[i][j] = (binom[i - 1][j - 1] + binom[i - 1][j]) % MOD;
            }
        }
        
        for (int idx = n; idx >= 1; idx--) {
            dp[idx][idx - 1] = 1;
            dp[idx + 1][idx] = 1;
            for (int idx2 = idx + 1; idx2 <= n; idx2++) {
                for (int idx3 = idx + 1; idx3 <= idx2; idx3++) {
                    dp[idx][idx2] = (s.charAt(idx - 1) == s.charAt(idx3 - 1)) ? 
                            (dp[idx][idx2] + (dp[idx + 1][idx3 - 1] * dp[idx3 + 1][idx2]) % MOD * binom[(idx2 - idx + 1) / 2][(idx3 - idx + 1) / 2]) % MOD : dp[idx][idx2];
                }
            }
        }
        
        System.out.println(dp[1][n]);
        sc.close();
    }
}

