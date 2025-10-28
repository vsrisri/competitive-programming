import java.util.*;
import java.io.*;

public class Poutine {
    public static final int MOD = 998244353;
    public static long[] a;
    public static long[] b;
    public static int n;
    public static long t;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
        n = Integer.parseInt(st.nextToken()); 
        t = Long.parseLong(st.nextToken());
        a = new long[n];
        b = new long[n];

        for (int idx = 0; idx < n; idx++) {
            st = new StringTokenizer(reader.readLine(), " ");
            long ai = Long.parseLong(st.nextToken());
            long bi = Long.parseLong(st.nextToken());
            a[idx] = ai;
            b[idx] = bi;
        }

        long low = 0;
        long high = (long) 1e18;
        long curr = 0;
        long ans = 0;
        while (low < high) {
            long mid = (low + high) / 2;
            if (helper(mid)) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        for (int idx = 0; idx < n; idx++) {
            if (a[idx] < low) {
                continue;
            }
            long numTimes = (a[idx] - low) / b[idx] + 1;
            curr += numTimes;
            numTimes %= MOD;
            ans += (((2 * a[idx] % MOD - (numTimes - 1) * (b[idx] % MOD) % MOD) % MOD) * numTimes % MOD) * ((MOD + 1) / 2) % MOD;
            ans %= MOD;
        }

        ans += ((low - 1) % MOD * ((t - curr) % MOD)) % MOD;
        ans = ((ans % MOD) + MOD) % MOD;
        System.out.println(ans);
        reader.close();
    }

    public static boolean helper(long mid) {
        if (mid == 0) {
            return true;
        }
        long ans = 0;
        for (int idx = 0; idx < n; idx++) {
            if (a[idx] >= mid) {
                ans += (a[idx] - mid) / b[idx] + 1;
            }
            if (ans >= t) {
                return true;
            }
        }
        return false;
    }
}
