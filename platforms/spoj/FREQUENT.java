// Incomplete
import java.io.*;
import java.util.*;

public class FREQUENT {
    public static int[] arr;
    public static Node[] seg;
    public static int n;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String line = br.readLine();
            if (line == null || line.equals("0")) {
                break;
            }
            StringTokenizer st = new StringTokenizer(line);
            n = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            arr = new int[n + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            seg = new Node[4 * n];
            build(1, 1, n);
            for (int i = 0; i < q; i++) {
                st = new StringTokenizer(br.readLine());
                int l = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                Node res = query(1, 1, n, l, r);
                sb.append(res.maxFreq).append('\n');
            }
        }
        System.out.print(sb);
    }

    public static class Node {
        int leftVal, rightVal, leftFreq, rightFreq, maxFreq;
        Node(int lv, int rv, int lf, int rf, int mf) {
            leftVal = lv;
            rightVal = rv;
            leftFreq = lf;
            rightFreq = rf;
            maxFreq = mf;
        }
    }

    public static Node merge(Node a, Node b) {
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }

        int lv = a.leftVal;
        int rv = b.rightVal;
        int lf = a.leftFreq;
        int rf = b.rightFreq;
        int mf = Math.max(a.maxFreq, b.maxFreq);
        if (a.rightVal == b.leftVal) {
            int mid = a.rightFreq + b.leftFreq;
            mf = Math.max(mf, mid);
            if (a.leftVal == a.rightVal) {
                lf = mid;
            }
            if (b.leftVal == b.rightVal) {
                rf = mid;
            }
        }
        return new Node(lv, rv, lf, rf, mf);
    }

    public static void build(int idx, int l, int r) {
        int mid = (l + r) / 2;
        if (l == r) {
            seg[idx] = new Node(arr[l], arr[r], 1, 1, 1);
            return;
        }
        build(idx * 2, l, mid);
        build(idx * 2 + 1, mid + 1, r);
        seg[idx] = merge(seg[idx * 2], seg[idx * 2 + 1]);
    }

    public static Node query(int idx, int l, int r, int ql, int qr) {
        int mid = (l + r) / 2;
        if (qr < l || r < ql) {
            return null;
        }
        if (ql <= l && r <= qr) {
            return seg[idx];
        }
        Node left = query(idx * 2, l, mid, ql, qr);
        Node right = query(idx * 2 + 1, mid + 1, r, ql, qr);
        return merge(left, right);
    }
}
