import java.io.*;
import java.util.*;

public class LITE {
    public static int N, M;
    public static int[] tree;
    public static boolean[] lazy;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        tree = new int[4 * N];
        lazy = new boolean[4 * N];
        StringBuilder out = new StringBuilder();
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            if (op == 0) {
                update(1, 1, N, s, e);
            }
            else {
                out.append(query(1, 1, N, s, e)).append('\n');
            }
        }

        System.out.print(out);
        br.close();
    }
    
    public static void apply(int node, int start, int end) {
        tree[node] = (end - start + 1) - tree[node];
        lazy[node] ^= true;
    }

    public static void pushDown(int node, int start, int end) {
        if (lazy[node]) {
            int mid = (start + end) >> 1;
            apply(node << 1, start, mid);
            apply(node << 1 | 1, mid + 1, end);
            lazy[node] = false;
        }
    }

    public static void update(int node, int start, int end, int l, int r) {
        if (r < start || end < l) {
            return;
        }
        if (l <= start && end <= r) {
            apply(node, start, end);
            return;
        }
        pushDown(node, start, end);
        int mid = (start + end) >> 1;
        update(node << 1, start, mid, l, r);
        update(node << 1 | 1, mid + 1, end, l, r);
        tree[node] = tree[node << 1] + tree[node << 1 | 1];
    }

    public static int query(int node, int start, int end, int l, int r) {
        if (r < start || end < l) {
            return 0;
        }
        if (l <= start && end <= r) {
            return tree[node];
        }
        pushDown(node, start, end);
        int mid = (start + end) >> 1;
        return query(node << 1, start, mid, l, r) + query(node << 1 | 1, mid + 1, end, l, r);
    }
}
