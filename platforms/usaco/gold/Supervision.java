import java.util.*;
import java.io.*;

public class Supervision {
    public static final int MOD = 1_000_000_007;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        long d = Long.parseLong(st.nextToken());
        int[] p = new int[n];
        int[] o = new int[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            p[i] = Integer.parseInt(st.nextToken());
            o[i] = Integer.parseInt(st.nextToken());
        }

        int[] prefCount = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefCount[i + 1] = prefCount[i] + (o[i] == 0 ? 1 : 0);
        }

        long inv2base = pow(2, MOD - 2, MOD);
        long[] pow2 = new long[n + 2];
        long[] inv2pow = new long[n + 2];
        pow2[0] = 1; inv2pow[0] = 1;
        for (int i = 1; i <= n + 1; i++) {
            pow2[i] = pow2[i - 1] * 2 % MOD;
            inv2pow[i] = inv2pow[i - 1] * inv2base % MOD;
        }

        int[] ri = new int[n];
        for (int i = 0; i < n; i++) {
            if (o[i] == 1) {
                int lo = i;
                int hi = n - 1;
                int res = i;
                while (lo <= hi) {
                    int mid = (lo + hi) / 2;
                    if ((long) {
                        p[mid] - p[i] <= d) { res = mid; lo = mid + 1; }
                    } else {
                        hi = mid - 1;
                    }
                }
                ri[i] = res;
            }
        }

        long ans = 0;
        long wOver = 0;
        long wNoover = 0;
        ArrayDeque<long[]> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (o[i] == 1) {
                while (!queue.isEmpty() && (int) queue.peekFirst()[0] < i) {
                    long[] front = queue.pollFirst();
                    wOver = (wOver - front[1] + MOD) % MOD;
                    wNoover = (wNoover + front[2]) % MOD;
                }

                int freeI = prefCount[ri[i] + 1] - prefCount[i + 1];
                long dpI = (pow2[freeI] * ((wNoover + 1) % MOD) % MOD + pow2[prefCount[ri[i] + 1]] * wOver % MOD) % MOD;
                ans = (ans + dpI) % MOD;
                long wCont = dpI * inv2pow[prefCount[ri[i] + 1]] % MOD;
                queue.addLast(new long[] {ri[i], wCont, dpI});
                wOver = (wOver + wCont) % MOD;
            }
        }

        System.out.println(ans);
        br.close();
    }

    public static long pow(long base, long exp, long mod) {
        long res = 1; 
        base %= mod;
        while (exp > 0) { 
            if ((exp & 1) == 1) {
                res = res * base % mod; 
                base = base * base % mod; 
                exp >>= 1; 
            }
        return res;
    }

}
