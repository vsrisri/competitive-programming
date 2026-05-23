import java.util.*;
import java.io.*;

public class MKTHNUM {
    public static int n;
    public static int[][] tree;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);
        StringBuilder sb = new StringBuilder();
        st.nextToken();
        n = (int) st.nval;
        st.nextToken();
        int m = (int) st.nval;
        int[] a = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            st.nextToken();
            a[i] = (int) st.nval;
        }

        int[] sorted = Arrays.copyOfRange(a, 1, n + 1);
        Arrays.sort(sorted);
        tree = new int[4 * (n + 1)][];
        build(a, 1, 1, n);
        for (int q = 0; q < m; q++) {
            st.nextToken();
            int l = (int) st.nval;
            st.nextToken();
            int r = (int) st.nval;
            st.nextToken();
            int k = (int) st.nval;
            sb.append(query(sorted, l, r, k)).append('\n');
        }

        System.out.print(sb);
        br.close();
    }

    public static void build(int[] a, int node, int start, int end) {
        if (start == end) {
            tree[node] = new int[]{a[start]};
        } else {
            int mid = (start + end) / 2;
            build(a, 2 * node, start, mid);
            build(a, 2 * node + 1, mid + 1, end);
            int[] left = tree[2 * node];
            int[] right = tree[2 * node + 1];
            int[] merged = new int[left.length + right.length];
            int i = 0, j = 0, k = 0;
            while (i < left.length && j < right.length) {
                if (left[i] <= right[j]) {
                    merged[k++] = left[i++];
                } else {
                    merged[k++] = right[j++];
                }
            }
            while (i < left.length) {
                merged[k++] = left[i++];
            }
            while (j < right.length) {
                merged[k++] = right[j++];
            }
            tree[node] = merged;
        }
    }

    public static int helper(int node, int start, int end, int l, int r, int val) {
        if (r < start || end < l) {
            return 0;
        }
        if (l <= start && end <= r) {
            int lo = 0, hi = tree[node].length;
            while (lo < hi) {
                int mid = (lo + hi) / 2;
                if (tree[node][mid] <= val) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }
            return lo;
        }
        int mid = (start + end) / 2;
        return helper(2 * node, start, mid, l, r, val) + helper(2 * node + 1, mid + 1, end, l, r, val);
    }

    public static int query(int[] sorted, int l, int r, int k) {
        int lo = 0;
        int hi = sorted.length - 1;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            int count = helper(1, 1, n, l, r, sorted[mid]);
            if (count >= k) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return sorted[lo];
    }

}
