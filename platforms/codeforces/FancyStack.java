import java.io.*;
import java.util.*;

public class FancyStack {
    static final int N = 5001;
    static final int MOD = 998244353;
    static long[] fact = new long[N], invFact = new long[N];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        preprocess();
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            solve(br);
        }
    }

    static long modExp(long base, long exp) {
        long result = 1;
        base %= MOD;
        while (exp > 0) {
            if ((exp & 1) == 1) result = (result * base) % MOD;
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return result;
    }

    static long inv(long x) {
        return modExp(x, MOD - 2);
    }

    static long binom(int n, int k) {
        if (k > n) return 0;
        return (fact[n] * invFact[k] % MOD) * invFact[n - k] % MOD;
    }

    static void preprocess() {
        fact[0] = invFact[0] = 1;
        for (int i = 1; i < N; i++) {
            fact[i] = fact[i - 1] * i % MOD;
            invFact[i] = inv(fact[i]);
        }
    }

    static void solve(BufferedReader br) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int[] a = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
        reverse(a);

        List<int[]> intervals = compress(a);
        long[][] dp = new long[n + 1][n + 1];
        dp[0][0] = 1;

        int cnt = 0;
        for (int[] interval : intervals) {
            cnt = updateDP(dp, cnt, interval[1]);
        }

        System.out.println(dp[n][n / 2]);
    }

    static void reverse(int[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
    }

    static List<int[]> compress(int[] arr) {
        List<int[]> intervals = new ArrayList<>();
        for (int num : arr) {
            if (!intervals.isEmpty() && intervals.get(intervals.size() - 1)[0] == num) {
                intervals.get(intervals.size() - 1)[1]++;
            } else {
                intervals.add(new int[]{num, 1});
            }
        }
        return intervals;
    }

    static int updateDP(long[][] dp, int cnt, int v) {
        for (int j = cnt; j >= 0; j--) {
            int pos = Math.max(0, j - 1) + (j == cnt / 2 ? 1 : 0) - (cnt - j);
            if (pos < 0) continue;
            for (int z = 0; z <= 1; z++) {
                if (j + z > cnt / 2) continue;
                dp[cnt + v][j + z] += binom(pos, v - z) * dp[cnt][j] % MOD;
                dp[cnt + v][j + z] %= MOD;
            }
        }
        return cnt + v;
    }
}

