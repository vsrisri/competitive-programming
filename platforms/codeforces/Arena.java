import java.io.*;
import java.util.*;

public class Arena {
    static final int MOD = 998244353;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int heroes = Integer.parseInt(tokenizer.nextToken());
        int maxHealth = Integer.parseInt(tokenizer.nextToken());
        int[][] dp = new int[501][501];
        int[][] comb = new int[501][501];
        int ans = 0;

        for (int i = 0; i <= heroes; i++) {
            comb[i][0] = comb[i][i] = 1;
            for (int j = 1; j < i; ++j) {
                comb[i][j] = modAdd(comb[i - 1][j], comb[i - 1][j - 1]);
            }
        }

        dp[heroes][0] = 1;
        for (int i = heroes; i > 1; i--) {
            for (int currHealth = 0; currHealth < maxHealth; currHealth++) {
                int pow = 1;
                if (dp[i][currHealth] == 0) {
                    continue;
                }
                int nextHealth = Math.min(maxHealth, currHealth + i - 1);
                for (int remHeroes = i; remHeroes >= 0; remHeroes--) {
                    dp[remHeroes][nextHealth] = modAdd(dp[remHeroes][nextHealth], modMult(dp[i][currHealth], modMult(comb[i][remHeroes], pow)));
                    pow = modMult(pow, nextHealth - currHealth);
                }
            }
        }

        for (int i = 0; i <= maxHealth; i++) {
            ans = modAdd(ans, dp[0][i]);
        }

        System.out.println(ans);
    }

    public static int modMult(int a, int b) {
        return (int) (a * 1L * b % MOD);
    }

    public static int modAdd(int a, int b) {
        a += b;
        return (a >= MOD) ? (a - MOD) : a;
    }
}

