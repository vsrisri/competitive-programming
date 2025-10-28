import java.io.*;
import java.util.*;

public class CloseTuplesHard {
    static final int MOD = 1000000007;
    static final int MAXN = 200000;
    static long[] fact = new long[MAXN + 1];
    static long[] invFact = new long[MAXN + 1];

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        helper();
        
        int t = Integer.parseInt(reader.readLine());
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            
            int[] a = new int[n];
            st = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(a);
            
            long ans = 0;
            for (int i = 0; i < n; i++) {
                int j = i;
                while (j < n && a[j] - a[i] <= k) {
                    j++;
                }
                int r = j - i;
                if (r >= m) {
                    ans = (ans + binom(r - 1, m - 1)) % MOD;
                }
            }
            
            sb.append(ans).append("\n");
        }
        
        System.out.println(sb.toString());
    }

    static void helper() {
        fact[0] = 1;
        for (int i = 1; i <= MAXN; i++) {
            fact[i] = fact[i - 1] * i % MOD;
        }
        invFact[MAXN] = modInv(fact[MAXN]);
        for (int i = MAXN - 1; i >= 0; i--) {
            invFact[i] = invFact[i + 1] * (i + 1) % MOD;
        }
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

    static long binom(int n, int k) {
        if (n < 0 || k < 0 || k > n) {
            return 0;
        }
        return fact[n] * invFact[k] % MOD * invFact[n - k] % MOD;
    }
}

