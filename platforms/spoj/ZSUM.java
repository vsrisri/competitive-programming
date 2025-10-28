import java.util.*;
import java.io.*;

public class ZSUM {
    static final long MOD = 10000007;
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            if (n == k && n == 0) {
                break;
            } else {
                long a = (2 * helper(n - 1, n - 1)) % MOD;
                long b = (2 * helper(n - 1, k)) % MOD;
                long ans = (a + b + helper(n, k) + helper(n, n)) % MOD;
                System.out.println(ans);
            }
        }
        reader.close();
    }

    public static long helper (long a, long b) {
        long ans = 1;
        while (b > 0) {
            if (b % 2 == 1) {
                ans = (ans * a) % MOD;
            }
            a = (a * a) % MOD;
            b >>= 1;
        }
        return ans;
    }
}

