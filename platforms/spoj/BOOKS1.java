// Incomplete
import java.io.*;
import java.util.*;

public class BOOKS1 {
    public static int m, k;
    public static long[] a;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder out = new StringBuilder();
        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            a = new long[m];
            st = new StringTokenizer(br.readLine());
            long sum = 0;
            long lo = 0, hi = 0;
            boolean[] ansArr = new boolean[m];
            for (int i = 0; i < m; i++) {
                a[i] = Long.parseLong(st.nextToken());
                hi += a[i];
            }
            while (lo < hi) {
                long mid = (lo + hi) / 2;
                if (helper(mid)) {
                    hi = mid;
                } else {
                    lo = mid + 1;
                }
            }

            long lim = lo;
            int num = k - 2;
            for (int i = m - 1; i >= 0; i--) {
                if (sum + a[i] > lim || i + 1 < num) {
                    ansArr[i] = true;
                    num--;
                    sum = a[i];
                } else {
                    sum += a[i];
                }
            }

            for (int i = 0; i < m; i++) {
                out.append(a[i]);
                if (i < m - 1) {
                    out.append(" ");
                }
                if (i < m - 1 && ansArr[i]) {
                    out.append("/ ");
                }
            }
            if (tc < T - 1) {
                out.append("\n");
            }
        }
        System.out.print(out.toString());
        br.close();
    }

    public static boolean helper(long lim) {
        int count = 1;
        long sum = 0;
        for (int i = 0; i < m; i++) {
            if (a[i] > lim) {
                return false;
            }
            if (sum + a[i] > lim) {
                count++;
                sum = a[i];
            } else {
                sum += a[i];
            }
        }
        return count <= k;
    }
}

