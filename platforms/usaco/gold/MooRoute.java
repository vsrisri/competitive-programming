import java.io.*;
import java.util.*;

public class MooRoute {
    static final int MOD = (int) (1e9) + 7;
    static final int LIMIT = (int) 1e6 + 1;
    static long[] fact = new long[LIMIT];
    static long[] invfact = new long[LIMIT];
    static long powMod(long base, int exponent, int mod) {
        long result = 1;
        long powBase = base;
        while (exponent > 0) {
            if (exponent % 2 == 1) {
                result = (result * powBase) % mod;
            }
            powBase = (powBase * powBase) % mod;
            exponent /= 2;
        }
        return result;
    }

    public static void calcFact() {
        fact[0] = 1;
        for (int i = 1; i < LIMIT; i++) {
            fact[i] = (fact[i - 1] * i) % MOD;
        }
        invfact[LIMIT - 1] = powMod(fact[LIMIT - 1], MOD - 2, MOD);
        for (int i = LIMIT - 2; i >= 0; i--) {
            invfact[i] = (invfact[i + 1] * (i + 1)) % MOD;
        }
    }

    public static long choose(int n, int k) {
        if (k > n || k < 0) {
            return 0;
        }
        long result = (fact[n] * invfact[k]) % MOD;
        return (result * invfact[n - k]) % MOD;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int sCount = Integer.parseInt(reader.readLine());
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int[] crossings = new int[sCount];
        int[] midCrossings = new int[sCount];
        calcFact();

        for (int index = 0; index < sCount; index++) {
            crossings[index] = Integer.parseInt(tokenizer.nextToken());
            midCrossings[index] = crossings[index] / 2;
        }

        long totWays = 1;
        for (int idx = 0; idx < sCount - 1; idx++) {
            if (midCrossings[idx] >= midCrossings[idx + 1]) {
                totWays = (totWays * choose(midCrossings[idx], midCrossings[idx + 1])) % MOD;
            } else {
                totWays = (totWays * choose(midCrossings[idx + 1] - 1, midCrossings[idx] - 1)) % MOD;
            }
        }

        System.out.println(totWays);
    }
}

