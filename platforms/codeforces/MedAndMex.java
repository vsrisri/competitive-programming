import java.io.*;
import java.util.*;

public class Main {
    static final int MOD = 998244353, MAX_N = 100000;
    static int[] fact = new int[MAX_N], inversefact = new int[MAX_N];

    static int modExp(long base, long exp) {
        long ans = 1;
        while (exp > 0) {
            if (exp % 2 == 1) ans = ans * base % MOD;
            base = base * base % MOD;
            exp /= 2;
        }
        return (int) ans;
    }

    static void helper2(int n) {
        StringBuilder ans = new StringBuilder();
        ans.append(0);
        for (int i = 1; i < (n + 1) / 2; i++) {
            long binomial = ((long) fact[n - i - 2] * inversefact[i - 1] % MOD) * inversefact[n - 2 * i - 1] % MOD;
            ans.append(' ').append((binomial * fact[2 * i] % MOD) * fact[n - 2 * i + 1] % MOD);
        }
        for (int i = (n + 1) / 2; i < n; i++) {
            ans.append(" 0");
        }
        System.out.println(ans);
    }

    static void helper() {
        fact[0] = 1;
        for (int i = 1; i < MAX_N; i++) {
            fact[i] = (int) ((long) fact[i - 1] * i % MOD);
        }
        inversefact[MAX_N - 1] = modExp(fact[MAX_N - 1], MOD - 2);
        for (int i = MAX_N - 1; i > 0; i--) {
            inversefact[i - 1] = (int) ((long) inversefact[i] * i % MOD);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        helper();
        int testCases = Integer.parseInt(reader.readLine());
        while (testCases-- > 0) {
            int n = Integer.parseInt(reader.readLine());
            helper2(n);
        }
    }
}

