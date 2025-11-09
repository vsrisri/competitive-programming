// Incomplete
import java.io.*;
import java.util.*;

public class FREQUENT {
    public static int n;
    public static int[] arr;
    public static int[] leftVal, rightVal, leftFreq, rightFreq, maxFreq;
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

            int size = 4 * n;
            leftVal = new int[size];
            rightVal = new int[size];
            leftFreq = new int[size];
            rightFreq = new int[size];
            maxFreq = new int[size];
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
        br.close();
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
            leftVal[idx] = arr[l];
            rightVal[idx] = arr[r];
            leftFreq[idx] = 1;
            rightFreq[idx] = 1;
            maxFreq[idx] = 1;
            return;
        }
        build(idx * 2, l, mid);
        build(idx * 2 + 1, mid + 1, r);
        Node merged = merge(
            new Node(leftVal[idx * 2], rightVal[idx * 2], leftFreq[idx * 2], rightFreq[idx * 2], maxFreq[idx * 2]),
            new Node(leftVal[idx * 2 + 1], rightVal[idx * 2 + 1], leftFreq[idx * 2 + 1], rightFreq[idx * 2 + 1], maxFreq[idx * 2 + 1])
        );
        leftVal[idx] = merged.leftVal;
        rightVal[idx] = merged.rightVal;
        leftFreq[idx] = merged.leftFreq;
        rightFreq[idx] = merged.rightFreq;
        maxFreq[idx] = merged.maxFreq;
    }

    public static Node query(int idx, int l, int r, int ql, int qr) {
        int mid = (l + r) / 2;
        if (qr < l || r < ql) {
            return null;
        }
        if (ql <= l && r <= qr) {
            return new Node(leftVal[idx], rightVal[idx], leftFreq[idx], rightFreq[idx], maxFreq[idx]);
        }
        Node left = query(idx * 2, l, mid, ql, qr);
        Node right = query(idx * 2 + 1, mid + 1, r, ql, qr);
        return merge(left, right);
    }
}

