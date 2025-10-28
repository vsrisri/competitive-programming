import java.io.*;
import java.util.*;

public class RangeUpdateQueries {
    static class STree {
        int size;
        long[] tree;
        public STree (int n) {
            size = 1;
            while (size < n) {
                size *= 2;
            }
            tree = new long[2 * size - 1];
        }

        public void upd(int l, int r, int v) {
            upd(0, 0, size, l, r, v);
        }

        void upd(int i, int li, int ri, int l, int r, int v) {
            if (li >= l && ri <= r) {
                tree[i] += v;
                return;
            }

            if (ri <= l || r <= li) {
                return;
            }
            int mid = (li + ri) / 2;
            upd(2 * i + 1, li, mid, l, r, v);
            upd(2 * i + 2, mid, ri, l, r, v);
        }

        public long get(int k) {
            return helper(0, 0, size, k);
        }

        public long helper(int i, int li, int ri, int k) {
            if (k < li || k >= ri) {
                return 0;
            }
            if (ri - li == 1) {
                return tree[i];
            }
            int m = (li + ri) / 2;
            return tree[i] + helper(2 * i + 1, li, m, k) + helper(2 * i + 2, m, ri, k);
        }
/*
        void print() {
            int ind = 0;
            int rowSize = 1;
            while (ind < size) {
                for (int i = 0; i < rowSize; i++) {
                    System.out.print(tree[ind++] + "\t");
                }
                System.out.println();
                rowSize *= 2;
            }
        }
        */
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        long[] arr = new long[n];
        STree seg = new STree(n);
        st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < n; idx++) {
            arr[idx] = Long.parseLong(st.nextToken());
        }

        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            if (k == 1) {
                int l = Integer.parseInt(st.nextToken()) - 1;
                int r = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                seg.upd(l, r, v);
            } else if (k == 2) {
                int p = Integer.parseInt(st.nextToken()) - 1;
                System.out.println(seg.get(p) + arr[p]);
            }
        }
        br.close();
    }
}

