import java.util.*;
import java.io.*;

public class DistinctValsQueries {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        st = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        SegmentTree segTree = new SegmentTree(arr);
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(reader.readLine());
            int l = Integer.parseInt(st.nextToken()) - 1;
            int r = Integer.parseInt(st.nextToken()) - 1;
            TreeSet<Integer> ans = segTree.query(l, r);
            System.out.println(ans.size());
        }
        reader.close();
    }

    static class SegmentTree {
        public TreeSet<Integer>[] tree;
        public int n;

        public SegmentTree(int[] arr) {
            n = arr.length;
            tree = new TreeSet[4 * n];
            cHelper(arr, 0, 0, n - 1);
        }

        public void cHelper(int[] arr, int node, int start, int end) {
            if (start == end) {
                tree[node] = new TreeSet<>();
                tree[node].add(arr[start]);
            } else {
                int mid = (start + end) / 2;
                int lc = 2 * node + 1;
                int rc = 2 * node + 2;
                cHelper(arr, lc, start, mid);
                cHelper(arr, rc, mid + 1, end);
                tree[node] = new TreeSet<>(tree[lc]);
                tree[node].addAll(tree[rc]);
            }
        }

        public TreeSet<Integer> query(int l, int r) {
            return query(0, 0, n - 1, l, r);
        }

        public TreeSet<Integer> query(int node, int start, int end, int l, int r) {
            if (r < start || end < l) {
                return new TreeSet<>();
            }
            if (l <= start && end <= r) {
                return tree[node];
            }

            int mid = (start + end) / 2;
            TreeSet<Integer> lAns = query(2 * node + 1, start, mid, l, r);
            TreeSet<Integer> rAns = query(2 * node + 2, mid + 1, end, l, r);
            TreeSet<Integer> ans = new TreeSet<>(lAns);
            ans.addAll(rAns);
            return ans;
        }
    }
}
