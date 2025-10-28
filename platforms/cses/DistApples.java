import java.io.*;
import java.util.*;

public class DistApples {
    static final int MOD = 1000000007;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        long[] fact = new long[n + m];
        fact[0] = 1;
        for (int i = 1; i <= n + m - 1; i++) fact[i] = fact[i - 1] * i % MOD;
        long res = fact[n + m - 1] * modInverse(fact[m]) % MOD * modInverse(fact[n - 1]) % MOD;
        System.out.println(res);
        reader.close();
    }

    public static long modInverse(long x) {
        return power(x, MOD - 2);
    }

    public static long power(long x, long y) {
        long res = 1;
        while (y > 0) {
            if ((y & 1) == 1) res = res * x % MOD;
            x = x * x % MOD;
            y >>= 1;
        }
        return res;
    }
}

