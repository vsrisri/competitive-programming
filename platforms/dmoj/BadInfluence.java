import java.io.*;
import java.util.*;

public class BadInfluence {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        int[][] sArr = new int[n][3];
        int maxDesk = 0;
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            sArr[i][0] = Integer.parseInt(st.nextToken());
            sArr[i][1] = Integer.parseInt(st.nextToken());
            sArr[i][2] = Integer.parseInt(st.nextToken());
            maxDesk = Math.max(maxDesk, sArr[i][1] + sArr[i][2]);
        }

        for (int pos = 0; pos < n; pos++) {
            Integer[] order = new Integer[pos + 1];
            for (int i = 0; i <= pos; i++) order[i] = i;
            Arrays.sort(order, (a, b) -> Integer.compare(sArr[b][0], sArr[a][0]));
            SegTree seg = new SegTree(maxDesk + 1);
            int ans = 0;
            for (int idx : order) {
                int desk = sArr[idx][1];
                int inf = seg.query(desk);
                if (inf == Integer.MAX_VALUE) {
                    ans++;
                }

                int left = Math.max(0, desk - sArr[idx][2]);
                int right = Math.min(maxDesk, desk + sArr[idx][2]);
                seg.update(left, right, idx);
            }

            sb.append(ans).append('\n');
        }
        System.out.print(sb);
        br.close();
    }

    public static class SegTree {
        int[] tree;
        int[] lazy;
        int size;
        SegTree(int n) {
            size = 1;
            while (size < n) {
                size *= 2;
            }
            tree = new int[2 * size];
            lazy = new int[2 * size];
            Arrays.fill(tree, Integer.MAX_VALUE);
            Arrays.fill(lazy, Integer.MAX_VALUE);
        }

        public void update(int node, int l, int r, int ql, int qr, int val) {
            int mid = (l + r) / 2;
            push(node, l, r);
            if (ql > r || qr < l) {
                return;
            }
            if (ql <= l && r <= qr) {
                lazy[node] = val;
                push(node, l, r);
                return;
            }
            update(2 * node, l, mid, ql, qr, val);
            update(2 * node + 1, mid + 1, r, ql, qr, val);
            push(2 * node, l, mid);
            push(2 * node + 1, mid + 1, r);
            tree[node] = Math.min(tree[2 * node], tree[2 * node + 1]);
        }

        public int query(int node, int l, int r, int pos) {
            int mid = (l + r) / 2;
            push(node, l, r);
            if (l == r) {
                return tree[node];
            }
            if (pos <= mid) {
                return query(2 * node, l, mid, pos);
            } else {
                return query(2 * node + 1, mid + 1, r, pos);
            }
        }

        public void push(int node, int l, int r) {
            if (lazy[node] != Integer.MAX_VALUE) {
                tree[node] = Math.min(tree[node], lazy[node]);
                if (l != r) {
                    lazy[2 * node] = Math.min(lazy[2 * node], lazy[node]);
                    lazy[2 * node + 1] = Math.min(lazy[2 * node + 1], lazy[node]);
                }
                lazy[node] = Integer.MAX_VALUE;
            }
        }

        public void update(int l, int r, int val) {
            update(1, 0, size - 1, l, r, val);
        }

        public int query(int pos) {
            return query(1, 0, size - 1, pos);
        }
    }

}
