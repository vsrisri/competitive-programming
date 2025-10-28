import java.io.*;
import java.util.*;

public class HORRIBLE {
    public static int n;
    public static long[] bit1, bit2;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < t; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            bit1 = new long[n + 2];
            bit2 = new long[n + 2];
            while (c-- > 0) {
                st = new StringTokenizer(br.readLine());
                int type = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());
                int q = Integer.parseInt(st.nextToken());
                if (type == 0) {
                    long v = Long.parseLong(st.nextToken());
                    rUpd(p, q, v);
                } else {
                    sb.append(rSum(p, q)).append('\n');
                }
            }
        }
        System.out.print(sb);
        br.close();
    }

    public static void upd(long[] bit, int idx, long val) {
        while (idx <= n) {
            bit[idx] += val;
            idx += idx & -idx;
        }
    }

    public static void rUpd(int l, int r, long val) {
        upd(bit1, l, val);
        upd(bit1, r + 1, -val);
        upd(bit2, l, val * (l - 1));
        upd(bit2, r + 1, -val * r);
    }

    public static long query(long[] bit, int idx) {
        long res = 0;
        while (idx > 0) {
            res += bit[idx];
            idx -= idx & -idx;
        }
        return res;
    }

    public static long pSum(int idx) {
        return idx * query(bit1, idx) - query(bit2, idx);
    }

    public static long rSum(int l, int r) {
        return pSum(r) - pSum(l - 1);
    }
}

