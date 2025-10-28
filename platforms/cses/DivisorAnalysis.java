import java.io.*;
import java.util.*;

public class DivisorAnalysis {
    static final int MOD = 1000000007;
    static final int MOD_MINUS_1 = MOD - 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long numOfDiv = 1;
        long sumOfDiv = 1;
        long prodOfDiv = 1;
        long expProdDiv = 1;

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long k = Long.parseLong(st.nextToken()) + 1;  
            long term = modExp(x, k) - 1;
            numOfDiv = numOfDiv * k % MOD;
            if (term < 0) {
                term += MOD;
            }

            long inverse = modInverse(x - 1, MOD);
            sumOfDiv = sumOfDiv * term % MOD * inverse % MOD;
            long exponent = k * (k - 1) / 2 % MOD_MINUS_1;
            prodOfDiv = modExp(prodOfDiv, k) * modExp(x, exponent * expProdDiv % MOD_MINUS_1) % MOD;
            expProdDiv = expProdDiv * k % MOD_MINUS_1;
        }

        System.out.println(numOfDiv + " " + sumOfDiv + " " + prodOfDiv);
    }

    public static long modExp(long base, long exp) {
        long ans = 1;
        while (exp > 0) {
            if ((exp & 1) != 0) {
                ans = ans * base % MOD;
            }
            base = base * base % MOD;
            exp >>= 1;
        }
        return ans;
    }

    public static long modInverse(long a, int mod) {
        return modExp(a, mod - 2);
    }
}

