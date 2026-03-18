import java.util.*;
import java.io.*;

public class MilkBuckets {
    public static final long lim = (long) 2e18;
    public static long[] a;
    public static int N;
    public static TreeSet<Integer> steps = new TreeSet<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine().trim());
        a = new long[N + 2];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            a[i] = Long.parseLong(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            long prev = (i == 1) ? 1L : a[i - 1];
            if (a[i] > prev) {
                steps.add(i);
            }
        }

        int Q = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();
        for (int q = 0; q < Q; q++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            long v = Long.parseLong(st.nextToken());
            long t = Long.parseLong(st.nextToken());
            a[idx] = v;
            helper(idx);
            if (idx + 1 <= N) {
                helper(idx + 1);
            }

            sb.append(solve(t)).append('\n');
        }

        System.out.print(sb);
        br.close();
    }

    public static void helper(int i) {
        long prev = (i == 1) ? 1L : a[i - 1];
        if (a[i] > prev) {
            steps.add(i); 
        } else {
            steps.remove(i);
        }
    }

    public static long solve(long t) {
        long period = a[1] + 1;
        long first = period;
        if (period > lim) {
            return 0;
        }

        int prevIdx = 1;
        for (int i : steps) {
            if (i == 1) {
                continue;
            }
            long gaps = (i - 1) - prevIdx;
            if (first > lim - gaps) {
                return 0;
            }

            first += gaps;
            long c = (a[i] + a[i - 1] - 1) / a[i - 1];
            long add = (c - 1);
            if (add > 0 && period > lim / add) {
                return 0;
            }
            add *= period;
            if (first > lim - add - 1) {
                return 0;
            }
            first += add + 1;
            if (period > lim / c) {
                return 0;
            }
            period *= c;
            prevIdx = i;
        }

        long gaps = N - prevIdx;
        if (first > lim - gaps) {
            return 0;
        }
        first += gaps;

        if (first > t) {
            return 0;
        }
        return ((t - first) / period + 1) * a[N];
    }
}
