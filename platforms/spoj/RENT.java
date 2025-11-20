import java.io.*;
import java.util.*;

public class RENT {
    public static class Ord implements Comparable<Ord> {
        int s, e, p;
        public Ord(int st, int d, int pr) {
            s = st;
            e = st + d;
            p = pr;
        }

        public int compareTo(Ord o) {
            return this.e - o.e;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < t; tc++) {
            int n = Integer.parseInt(br.readLine());
            Ord[] a = new Ord[n];
            int[] ends = new int[n];
            long[] dp = new long[n];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());
                a[i] = new Ord(s, d, p);
            }

            Arrays.sort(a);
            for (int i = 0; i < n; i++) {
                ends[i] = a[i].e;
            }

            dp[0] = a[0].p;
            for (int i = 1; i < n; i++) {
                long take = a[i].p;
                int idx = Arrays.binarySearch(ends, 0, i, a[i].s);
                if (idx < 0) {
                    idx = -idx - 2;
                }
                if (idx >= 0) {
                    take += dp[idx];
                }
                dp[i] = Math.max(dp[i-1], take);
            }
            sb.append(dp[n-1]).append('\n');
        }
        System.out.print(sb);
        br.close();
    }
}

