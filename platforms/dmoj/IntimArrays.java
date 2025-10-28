import java.io.*;
import java.util.*;

public class IntimArrays {
    public static class SegmentTree {
        int n;
        int[] tree;
        public SegmentTree(int n) {
            this.n = n;
            this.tree = new int[4 * n];
        }

        public void update(int pos, int val) {
            update(1, 1, n, pos, val);
        }

        public int query(int ql, int qr) {
            return query(1, 1, n, ql, qr);
        }

        public void update(int node, int l, int r, int pos, int val) {
            int mid = (l + r) / 2;
            if (l == r) {
                tree[node] = val;
                return;
            }
            if (pos <= mid) {
                update(node * 2, l, mid, pos, val);
            } else {
                update(node * 2 + 1, mid + 1, r, pos, val);
            }
            tree[node] = tree[node * 2] + tree[node * 2 + 1];
        }

        public int query(int node, int l, int r, int ql, int qr) {
            int mid = (l + r) / 2;
            if (ql > r || qr < l) {
                return 0;
            }
            if (ql <= l && r <= qr) {
                return tree[node];
            }
            return query(node * 2, l, mid, ql, qr) + query(node * 2 + 1, mid + 1, r, ql, qr);
        }
    }

    public static class Query {
        int l, r, idx;
        Query(int l, int r, int idx) {
            this.l = l;
            this.r = r;
            this.idx = idx;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        int[] arr = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Query[] queries = new Query[q];
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            queries[i] = new Query(l, r, i);
        }

        Arrays.sort(queries, (a, b) -> b.l - a.l);
        int[] ans = new int[q];
        SegmentTree seg = new SegmentTree(n);
        Deque<Integer> stack = new ArrayDeque<>();
        int num = 1;
        for (Query query : queries) {
            while (num <= n && (n - num + 1) >= query.l) {
                int idx = n - num + 1;
                int val = arr[idx];
                while (!stack.isEmpty() && arr[stack.peek()] < val) {
                    seg.update(stack.pop(), 0);
                }

                seg.update(idx, 1);
                stack.push(idx);
                num++;
            }
            ans[query.idx] = seg.query(query.l, query.r);
        }

        StringBuilder sb = new StringBuilder();
        for (int x : ans) {
            sb.append(x).append("\n");
        }
        System.out.print(sb.toString());
        br.close();

    }
}

