import java.io.*;
import java.util.*;

public class DQUERY {
    public static int[] inArr;
    public static int[] ans;
    public static int[] lastO;
    public static int[] tree;
    public static int n, q;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        String[] t = br.readLine().split(" ");
        inArr = new int[n + 1];
        lastO = new int[1000001];
        Arrays.fill(lastO, -1);
        ans = new int[n + 1];
        tree = new int[4 * n];
        ArrayList<RQ> qArr = new ArrayList<>();

        for (int idx = 1; idx <= n; idx++) {
            inArr[idx] = Integer.parseInt(t[idx - 1]);
        }

        q = Integer.parseInt(br.readLine());
        for (int idx = 0; idx < q; idx++) {
            t = br.readLine().split(" ");
            int l = Integer.parseInt(t[0]);
            int r = Integer.parseInt(t[1]);
            qArr.add(new RQ(l, r, idx));
        }

        Collections.sort(qArr, Comparator.comparingInt(a -> a.r));
        int currR = 0;
        for (RQ query : qArr) {
            while (currR < query.r) {
                currR++;
                if (lastO[inArr[currR]] != -1) {
                    upd(1, 1, n, lastO[inArr[currR]], -1);
                }
                upd(1, 1, n, currR, 1);
                lastO[inArr[currR]] = currR;
            }
            ans[query.idx] = query(1, 1, n, query.l, query.r);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            sb.append(ans[i]).append("\n");
        }
        System.out.print(sb);
        br.close();
    }

    static void upd(int node, int start, int end, int idx, int val) {
        if (start == end) {
            tree[node] += val;
        } else {
            int mid = (start + end) / 2;
            if (start <= idx && idx <= mid) {
                upd(2 * node, start, mid, idx, val);
            } else {
                upd(2 * node + 1, mid + 1, end, idx, val);
            }

            tree[node] = tree[2 * node] + tree[2 * node + 1];
        }
    }

    static int query(int node, int start, int end, int l, int r) {
        if (r < start || end < l) {
            return 0;
        }
        if (l <= start && end <= r) {
            return tree[node];
        }
        int mid = (start + end) / 2;
        int p1 = query(2 * node, start, mid, l, r);
        int p2 = query(2 * node + 1, mid + 1, end, l, r);
        return p1 + p2;
    }

    static class RQ {
        int l;
        int r;
        int idx;

        RQ(int l, int r, int idx) {
            this.l = l;
            this.r = r;
            this.idx = idx;
        }
    }
}

/*import java.util.*;
import java.io.*;

public class DQUERY {
    static Integer[] inArr;
    static Integer ans;
    static HashMap<Integer, Integer> cMap;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] t = br.readLine().split(" ");
        inArr = new Integer[n + 1];
        ans = 0;
        cMap = new HashMap<Integer, Integer>();
        ArrayList<RQ> qArr = new ArrayList<RQ>();

        for (int idx = 1; idx <= n; idx++) {
            inArr[idx] = Integer.parseInt(t[idx - 1]);
        }

        Integer q = Integer.parseInt(br.readLine());
        for (int idx = 0; idx < q; idx++) {
            t = br.readLine().split(" ");
            Integer l = Integer.parseInt(t[0]);
            Integer r = Integer.parseInt(t[1]);
            qArr.add(new RQ(l, r));
        }

        int currL = 1;
        int currR = 1;
        for (RQ query : qArr) {
            int l = query.l;
            int r = query.r;

            while (l < currL) {
                currL--;
                upd(currL);
            }

            while (l > currL) {
                remove(l);
                l--;
            }

            while (r < currR) {
                upd(r);
                r++;
            }

            while (r > currR) {
                remove(r);
                r--;
            }

            System.out.println(ans);
            currL = query.l;
            currR = query.r;
        }
    }

    public static class RQ {
        public Integer l;
        public Integer r;

        public RQ(Integer l, Integer r) {
            this.l = l;
            this.r = r;
        }
    }

    public static void upd(int pos) {
        Integer curr = cMap.get(inArr[pos]);
        if (curr == null) {
            cMap.put(inArr[pos], 1);
            ans++;
            return;
        }
        if (curr == 0) {
            ans++;
            curr = 0;
        }
        cMap.put(inArr[pos], curr + 1);
    }

    public static void remove(int pos) {
        Integer curr = cMap.get(inArr[pos]);
        if (curr == null) {
            return;
        }
        if (curr == 1) {
            ans--;
        }
        cMap.put(inArr[pos], curr - 1);
    }
}*/
