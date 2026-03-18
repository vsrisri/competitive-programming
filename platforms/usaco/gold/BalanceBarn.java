import java.util.*;
import java.io.*;

public class BalanceBarn {
    public static int n;
    public static long[] hay, feed;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            long k = Long.parseLong(st.nextToken());
            hay = new long[n];
            feed = new long[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                hay[i] = Long.parseLong(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                feed[i] = Long.parseLong(st.nextToken());
            }

            long lo = -k - 2_000_000_000L;
            long hi = 2_000_000_000L;
            while (lo < hi) {
                long d = lo + (hi - lo) / 2;
                if (minhelper(d) <= k) {
                    hi = d;
                } else {
                    lo = d + 1;
                }
            }
            sb.append(lo).append('\n');
        }
        System.out.print(sb);
        br.close();
    }

    public static long helper(long d, long x) {
        long sum = 0;
        for (int i = 0; i < n; i++) {
            long opt1 = x - feed[i];
            long opt2 = hay[i] - (x + d);
            sum += Math.max(0L, Math.max(opt1, opt2));
            if (sum < 0) {
                return Long.MAX_VALUE / 2;
            }
        }
        return sum;
    }

    public static long minhelper(long d) {
        long lo = -2_000_000_000L - Math.max(0, -d);
        long hi = 2_000_000_000L + Math.max(0, -d);
        while (lo < hi) {
            long x = lo + (hi - lo) / 2;
            if (helper(d, x) <= helper(d, x + 1)) {
                hi = x;
            } else {
                lo = x + 1;
            }
        }
        return helper(d, lo);
    }

}
