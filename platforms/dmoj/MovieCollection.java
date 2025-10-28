import java.io.*;
import java.util.*;

public class MovieCollection {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(reader.readLine());

        for (int tc = 0; tc < t; tc++) {
            String[] str = reader.readLine().split(" ");
            int n = Integer.parseInt(str[0]);
            int m = Integer.parseInt(str[1]);
            int[] arr = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            STree sTree = new STree(n + m);
            int[] pos = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                pos[i] = m + i - 1;
                sTree.upd(m + i - 1, 1);
            }

            int currTop = m - 1;
            for (int i = 0; i < m; i++) {
                int movie = arr[i];
                int p = pos[movie];
                int moviesAbove = sTree.query(0, p - 1);
                System.out.print(moviesAbove + " ");
                sTree.upd(p, 0);
                pos[movie] = currTop;
                sTree.upd(currTop, 1);
                currTop--;
            }
            System.out.println();
        }
    }

    static class STree {
        int[] tree;
        int n;

        public STree(int size) {
            n = size;
            tree = new int[4 * n];
        }

        public void upd(int idx, int val, int l, int r, int pos) {
            if (l == r) {
                tree[pos] = val;
                return;
            }

            int mid = (l + r) / 2;

            if (idx <= mid) {
                upd(idx, val, l, mid, 2 * pos + 1);
            } else {
                upd(idx, val, mid + 1, r, 2 * pos + 2);
            }

            tree[pos] = tree[2 * pos + 1] + tree[2 * pos + 2];
        }

        public int query(int ql, int qr, int l, int r, int pos) {
            if (ql > r || qr < l) {
                return 0;
            }

            if (ql <= l && qr >= r) {
                return tree[pos];
            }
            int mid = (l + r) / 2;
            return query(ql, qr, l, mid, 2 * pos + 1) + query(ql, qr, mid + 1, r, 2 * pos + 2);
        }

        public void upd(int idx, int val) {
            upd(idx, val, 0, n - 1, 0);
        }

        public int query(int ql, int qr) {
            return query(ql, qr, 0, n - 1, 0);
        }
    }
}
