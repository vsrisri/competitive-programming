import java.io.*;
import java.util.*;

public class MULTQ3 {
    public static int N, Q;
    public static int[][] tree;
    public static int[] lazy;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        tree = new int[4 * N][3];
        lazy = new int[4 * N];
        build(1, 0, N - 1);
        StringBuilder out = new StringBuilder();
        for (int q = 0; q < Q; q++) {
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            if (op == 0) {
                update(1, 0, N - 1, A, B);
            }
            else {
                out.append(query(1, 0, N - 1, A, B)).append('\n');
            }
        }

        System.out.print(out);
        br.close();
    }

    public static void rotate(int node, int times) {
        times %= 3;
        if (times == 1) {
            int t0 = tree[node][0];
            tree[node][0] = tree[node][2];
            tree[node][2] = tree[node][1];
            tree[node][1] = t0;
        } else if (times == 2) {
            int t0 = tree[node][0];
            tree[node][0] = tree[node][1];
            tree[node][1] = tree[node][2];
            tree[node][2] = t0;
        }
    }

    public static void pushDown(int node) {
        if (lazy[node] != 0) {
            int l = node << 1, r = l | 1;
            lazy[l] = (lazy[l] + lazy[node]) % 3;
            lazy[r] = (lazy[r] + lazy[node]) % 3;
            rotate(l, lazy[node]);
            rotate(r, lazy[node]);
            lazy[node] = 0;
        }
    }

    public static void build(int node, int start, int end) {
        int mid = (start + end) >> 1;
        if (start == end) {
            tree[node][0] = 1;
            return;
        }

        build(node << 1, start, mid);
        build(node << 1 | 1, mid + 1, end);
        tree[node][0] = tree[node << 1][0] + tree[node << 1 | 1][0];
    }

    public static void update(int node, int start, int end, int l, int r) {
        if (r < start || end < l) {
            return;
        }
        if (l <= start && end <= r) {
            rotate(node, 1);
            lazy[node] = (lazy[node] + 1) % 3;
            return;
        }

        pushDown(node);
        int mid = (start + end) >> 1;
        update(node << 1, start, mid, l, r);
        update(node << 1 | 1, mid + 1, end, l, r);
        tree[node][0] = tree[node << 1][0] + tree[node << 1 | 1][0];
        tree[node][1] = tree[node << 1][1] + tree[node << 1 | 1][1];
        tree[node][2] = tree[node << 1][2] + tree[node << 1 | 1][2];
    }

    public static int query(int node, int start, int end, int l, int r) {
        if (r < start || end < l) {
            return 0;
        }
        if (l <= start && end <= r) {
            return tree[node][0];
        }
        pushDown(node);
        int mid = (start + end) >> 1;
        return query(node << 1, start, mid, l, r) + query(node << 1 | 1, mid + 1, end, l, r);
    }

}

