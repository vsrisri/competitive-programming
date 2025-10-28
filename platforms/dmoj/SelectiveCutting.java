import java.util.*;
import java.io.*;

class SegmentTree {
    public int[] tree;
    public int n;

    public SegmentTree(int n) {
        this.n = n;
        this.tree = new int[4 * n];
    }

    public void upd(int idx, int value) {
        upd(0, 0, n - 1, idx, value);
    }

    public void upd(int node, int start, int end, int idx, int value) {
        int mid = (start + end) / 2;
        if (start == end) {
            tree[node] += value;
        } else {
            if (start <= idx && idx <= mid) {
                upd(2 * node + 1, start, mid, idx, value);
            } else {
                upd(2 * node + 2, mid + 1, end, idx, value);
            }
            tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
        }
    }

    public int query(int l, int r) {
        return query(0, 0, n - 1, l, r);
    }

    public int query(int node, int start, int end, int l, int r) {
        int mid = (start + end) / 2;
        if (start > r || end < l) {
            return 0; 
        }
        if (start >= l && end <= r) {
            return tree[node];
        }
        return query(2 * node + 1, start, mid, l, r) + query(2 * node + 2, mid + 1, end, l, r);
    }
}

public class SelectiveCutting {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        int[] massArr = new int[N];
        String[] massInputs = reader.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            massArr[i] = Integer.parseInt(massInputs[i]);
        }

        int Q = Integer.parseInt(reader.readLine());
        Query[] queries = new Query[Q];
        for (int i = 0; i < Q; i++) {
            String[] queryInputs = reader.readLine().split(" ");
            int a = Integer.parseInt(queryInputs[0]);
            int b = Integer.parseInt(queryInputs[1]);
            int q = Integer.parseInt(queryInputs[2]);
            queries[i] = new Query(a, b, q, i);
        }

        Arrays.sort(queries, (x, y) -> Integer.compare(y.q, x.q));
        Integer[] idxArr = new Integer[N];
        for (int i = 0; i < N; i++) {
            idxArr[i] = i;
        }

        Arrays.sort(idxArr, (x, y) -> Integer.compare(massArr[y], massArr[x]));
        SegmentTree sTree = new SegmentTree(N);
        int[] outs = new int[Q];
        int j = 0;

        for (Query query : queries) {
            while (j < N && massArr[idxArr[j]] >= query.q) {
                sTree.upd(idxArr[j], massArr[idxArr[j]]);
                j++;
            }
            outs[query.index] = sTree.query(query.a, query.b);
        }

        for (int out : outs) {
            System.out.println(out);
        }

        reader.close();
    }

    public static class Query {
        int a, b, q, index;

        Query(int a, int b, int q, int index) {
            this.a = a;
            this.b = b;
            this.q = q;
            this.index = index;
        }
    }
}
