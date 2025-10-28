import java.io.*;
import java.util.*;

public class AlmostIdentityPerm {
    static final int MOD = 1000000007;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        long[] fact = new long[n + 1];
        fact[0] = 1;
        for (int i = 1; i <= n; i++) {
            fact[i] = fact[i - 1] * i % MOD;
        }

        long result = 0;
        for (int i = 0; i <= k; i++) {
            long comb = binomial(n, i, fact);
            long derangements = derangement(i);
            result = (result + comb * derangements % MOD) % MOD;
        }
        System.out.println(result);
    }

    static long binomial(int n, int k, long[] fact) {
        if (k > n) {
            return 0;
        }
        return fact[n] * modInv(fact[k]) % MOD * modInv(fact[n - k]) % MOD;
    }

    static long derangement(int m) {
        if (m == 0) {
            return 1;
        }
        if (m == 1) {
            return 0;
        }
        long[] der = new long[m + 1];
        der[0] = 1;
        der[1] = 0;
        for (int i = 2; i <= m; i++) {
            der[i] = (i - 1) * (der[i - 1] + der[i - 2]) % MOD;
        }
        return der[m];
    }

    static long modInv(long x) {
        return power(x, MOD - 2);
    }

    static long power(long x, long y) {
        long res = 1;
        while (y > 0) {
            if ((y & 1) == 1) res = res * x % MOD;
            x = x * x % MOD;
            y >>= 1;
        }
        return res;
    }
}

