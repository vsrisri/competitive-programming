import java.io.*;
import java.util.*;

public class GSS1 {
    public static class Node {
        public int sum, pref, suff, best;
        public Node(int v) {
            sum = v;
            pref = v;
            suff = v;
            best = v;
        }

        public Node() {}
    }

    public static Node merge(Node a, Node b) {
        Node r = new Node();
        r.sum = a.sum + b.sum;
        r.pref = Math.max(a.pref, a.sum + b.pref);
        r.suff = Math.max(b.suff, b.sum + a.suff);
        r.best = Math.max(Math.max(a.best, b.best), a.suff + b.pref);
        return r;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine().trim());
        st = new StringTokenizer(br.readLine());
        int N = 1;
        while (N < n) {
            N <<= 1;
        }
        Node[] tree = new Node[2 * N];
        for (int i = 0; i < 2 * N; i++) {
            tree[i] = new Node();
        }
        for (int i = 0; i < n; i++) {
            tree[N + i] = new Node(Integer.parseInt(st.nextToken()));
        }

        for (int i = N - 1; i > 0; i--) {
            tree[i] = merge(tree[2 * i], tree[2 * i + 1]);
        }

        int m = Integer.parseInt(br.readLine().trim());
        StringBuilder out = new StringBuilder();
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken()) - 1 + N;
            int r = Integer.parseInt(st.nextToken()) + N;
            Node L = null, R = null;
            while (l < r) {
                if ((l & 1) == 1) {
                    L = (L == null) ? tree[l] : merge(L, tree[l]);
                    l++;
                }
                if ((r & 1) == 1) {
                    r--;
                    R = (R == null) ? tree[r] : merge(tree[r], R);
                }
                l >>= 1;
                r >>= 1;
            }
            Node ans = (L == null) ? R : (R == null ? L : merge(L, R));
            out.append(ans.best).append('\n');
        }
        System.out.print(out);
        br.close();
    }
}

