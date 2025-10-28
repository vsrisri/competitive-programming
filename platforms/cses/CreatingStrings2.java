import java.io.*;
import java.util.*;

public class CreatingStrings2 {
    static final int MOD = 1000000007;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        int n = s.length();
        long[] fact = new long[n + 1];
        fact[0] = 1;
        for (int i = 1; i <= n; i++) {
            fact[i] = fact[i - 1] * i % MOD;
        }
        long res = fact[n];
        for (int f : freq) {
            if (f > 1) {
                res = res * modInverse(fact[f]) % MOD;
            }
        }
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

