import java.io.*;
import java.util.*;

public class Main {
    static int n, m, q;
    static int[] ans = new int[500001];
    static Pair[] arr = new Pair[500001];
    static Pair[] que = new Pair[500001];

    static class Pair {
        long first;
        int second;

        Pair(long first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    static class SegmentTree {
        static class Node {
            int occ;

            Node(int occ) {
                this.occ = occ;
            }
        }

        static final int MX_SIZE = MXN;
        int size;
        Node[] tree = new Node[MX_SIZE << 1];
        Node noth = new Node(0);

        public SegmentTree(int n) {
            this.size = n;
        }

        void build(int v, int tl, int tr) {
            if (tl == tr) {
                tree[v] = noth;
            } else {
                int tm = (tl + tr) / 2;
                build(v + 1, tl, tm);
                build(v + ((tm - tl + 1) << 1), tm + 1, tr);
                tree[v] = combine(tree[v + 1], tree[v + ((tm - tl + 1) << 1)]);
            }
        }

        void update(int v, int tl, int tr, int pos) {
            if (tl == tr) {
                tree[v] = new Node(1);
            } else {
                int tm = (tl + tr) / 2;
                if (pos <= tm) update(v + 1, tl, tm, pos);
                else update(v + ((tm - tl + 1) << 1), tm + 1, tr, pos);
                tree[v] = combine(tree[v + 1], tree[v + ((tm - tl + 1) << 1)]);
            }
        }

        int query(int v, int tl, int tr, int k) {
            if (k > tree[v].occ) return 0;
            if (tl == tr) return tl;
            int tm = (tl + tr) / 2;
            int res = query(v + 1, tl, tm, k);
            if (res != 0) return res;
            else return query(v + ((tm - tl + 1) << 1), tm + 1, tr, k - tree[v + 1].occ);
        }

        Node combine(Node l, Node r) {
            return new Node(l.occ + r.occ);
        }

        void build() {
            build(1, 1, size);
        }

        void update(int pos) {
            update(1, 1, size, pos);
        }

        int query(int k) {
            return query(1, 1, size, k);
        }
    }

    static void solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= n; i++) {
            int t = Integer.parseInt(br.readLine().trim());
            if (arr[t] == null) arr[t] = new Pair(0, t);
            arr[t].first++;
        }

        for (int i = 1; i <= m; i++) {
            if (arr[i] == null) arr[i] = new Pair(0, i);
        }

        Arrays.sort(arr, 1, m + 1, (a, b) -> Long.compare(a.first, b.first));

        for (int i = 1; i <= q; i++) {
            long t = Long.parseLong(br.readLine().trim());
            que[i] = new Pair(t, i);
        }

        Arrays.sort(que, 1, q + 1, (a, b) -> Long.compare(a.first, b.first));

        SegmentTree sgt = new SegmentTree(m);
        sgt.build();

        int cur_min = (int) arr[1].first;
        int cur_q = 1;
        long ub = n, lb;

        for (int p = 1; p <= m; ) {
            while (p <= m && arr[p].first == cur_min) {
                sgt.update(arr[p].second);
                p++;
            }
            lb = ub + 1;
            ub += p - 1;

            while (cur_q <= q) {
                if (lb <= que[cur_q].first && que[cur_q].first <= ub) {
                    ans[que[cur_q].second] = sgt.query((int) (que[cur_q].first - lb + 1));
                    cur_q++;
                } else break;
            }
            cur_min++;
        }

        while (cur_q <= q) {
            ans[que[cur_q].second] = (int) ((que[cur_q].first - ub - 1) % m + 1);
            cur_q++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= q; i++) {
            sb.append(ans[i]).append("\n");
        }
        System.out.print(sb.toString());
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int tests = Integer.parseInt(br.readLine().trim());
            for (int i = 0; i < tests; i++) {
                solve(br);
            }
        }
    }
}

