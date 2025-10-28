import java.io.*;
import java.util.*;

public class VictorIdentifiesSoftware  {
    static class Node {
        long sum, pref, suff, ans;
        Node(long v) {
            sum = v;
            pref = suff = ans = v;
        }
        Node() {
            sum = pref = suff = ans = Long.MIN_VALUE / 4;
        }
    }

    static class SegTree {
        int n;
        Node[] t;
        SegTree(long[] arr) {
            n = arr.length;
            t = new Node[4 * n];
            build(1, 0, n - 1, arr);
        }
        Node merge(Node a, Node b) {
            Node res = new Node();
            res.sum = a.sum + b.sum;
            res.pref = Math.max(a.pref, a.sum + b.pref);
            res.suff = Math.max(b.suff, b.sum + a.suff);
            res.ans = Math.max(Math.max(a.ans, b.ans), a.suff + b.pref);
            return res;
        }
        void build(int v, int l, int r, long[] arr) {
            if (l == r) {
                t[v] = new Node(arr[l]);
            } else {
                int m = (l + r) >> 1;
                build(v << 1, l, m, arr);
                build(v << 1 | 1, m + 1, r, arr);
                t[v] = merge(t[v << 1], t[v << 1 | 1]);
            }
        }
        void update(int v, int l, int r, int ql, int qr, long val) {
            if (ql > r || qr < l) return;
            if (l == r) {
                t[v] = new Node(val);
                return;
            }
            int m = (l + r) >> 1;
            update(v << 1, l, m, ql, qr, val);
            update(v << 1 | 1, m + 1, r, ql, qr, val);
            t[v] = merge(t[v << 1], t[v << 1 | 1]);
        }
        Node query(int v, int l, int r, int ql, int qr) {
            if (ql > r || qr < l) return new Node(Long.MIN_VALUE / 4);
            if (ql <= l && r <= qr) return t[v];
            int m = (l + r) >> 1;
            return merge(query(v << 1, l, m, ql, qr), query(v << 1 | 1, m + 1, r, ql, qr));
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        long[] a = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) a[i] = Long.parseLong(st.nextToken());
        boolean[] isGacha = new boolean[N];
        Arrays.fill(isGacha, true);
        long[] gachaArr = new long[N];
        long[] mathArr = new long[N];
        for (int i = 0; i < N; i++) {
            gachaArr[i] = a[i];
            mathArr[i] = Long.MIN_VALUE / 4;
        }
        SegTree gTree = new SegTree(gachaArr);
        SegTree mTree = new SegTree(mathArr);
        StringBuilder sb = new StringBuilder();
        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            if (t == 1 || t == 2) {
                int l = Integer.parseInt(st.nextToken()) - 1;
                int r = Integer.parseInt(st.nextToken()) - 1;
                boolean toGacha = (t == 1);
                for (int i = l; i <= r; i++) {
                    if (isGacha[i] != toGacha) {
                        isGacha[i] = toGacha;
                        if (toGacha) {
                            gTree.update(1, 0, N - 1, i, i, a[i]);
                            mTree.update(1, 0, N - 1, i, i, Long.MIN_VALUE / 4);
                        } else {
                            gTree.update(1, 0, N - 1, i, i, Long.MIN_VALUE / 4);
                            mTree.update(1, 0, N - 1, i, i, a[i]);
                        }
                    }
                }
            } else if (t == 3) {
                int l = Integer.parseInt(st.nextToken()) - 1;
                int r = Integer.parseInt(st.nextToken()) - 1;
                long x = Long.parseLong(st.nextToken());
                for (int i = l; i <= r; i++) {
                    a[i] = x;
                    if (isGacha[i]) gTree.update(1, 0, N - 1, i, i, x);
                    else mTree.update(1, 0, N - 1, i, i, x);
                }
            } else {
                int l = Integer.parseInt(st.nextToken()) - 1;
                int r = Integer.parseInt(st.nextToken()) - 1;
                Node res = (t == 4) ? gTree.query(1, 0, N - 1, l, r) : mTree.query(1, 0, N - 1, l, r);
                if (res.ans < 0) sb.append("breaks galore\n");
                else sb.append(res.ans).append("\n");
            }
        }
        System.out.print(sb);
    }
}

