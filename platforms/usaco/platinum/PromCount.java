import java.io.*;
import java.util.*;

public class PromCount {
    static int MAXN = 100010;
    static ArrayList<Integer>[] tree = new ArrayList[MAXN];
    static int[] st = new int[MAXN], en = new int[MAXN], prof = new int[MAXN];
    static int[] a = new int[MAXN], seg = new int[4 * MAXN];
    static int timer = 1, n, maxVal = 1;
    static Map<Pair, Integer> res = new HashMap<>();
    static Map<Integer, Integer> comp = new HashMap<>();
    static ArrayList<PairWithProf> arr2 = new ArrayList<>();
    static ArrayList<Integer> w = new ArrayList<>();
    public static class Pair {
        int l, r;
        Pair(int l, int r) { this.l = l; this.r = r; }
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Pair)) {
                return false;
            }
            Pair p = (Pair) o;
            return l == p.l && r == p.r;
        }

        public int hashCode() {
            return Objects.hash(l, r);
        }
    }

    public static class PairWithProf {
        int l, r, p;
        PairWithProf(int l, int r, int p) {
            this.l = l;
            this.r = r;
            this.p = p;
        }
    }

    public static void dfs(int u, int parent) {
        st[u] = timer;
        a[timer++] = u;
        for (int v : tree[u]) {
            if (v != parent)
                dfs(v, u);
        }
        en[u] = timer - 1;
    }

    public static int query(int i, int j, int p, int l, int r) {
        if (i > j) {
            return 0;
        }
        if (l >= i && r <= j) {
            return seg[p];
        }
        int m = (l + r) / 2;
        int left = query(i, Math.min(j, m), 2 * p, l, m);
        int right = query(Math.max(i, m + 1), j, 2 * p + 1, m + 1, r);
        return left + right;
    }

    public static void update(int x, int val, int p, int l, int r) {
        if (l == r) {
            seg[p] += val;
            return;
        }
        int m = (l + r) / 2;
        if (x <= m) {
            update(x, val, 2 * p, l, m);
        }
        else {
            update(x, val, 2 * p + 1, m + 1, r);
        }
        seg[p] = seg[2 * p] + seg[2 * p + 1];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader("promote.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("promote.out")));
        StringTokenizer stz = new StringTokenizer(in.readLine());
        n = Integer.parseInt(stz.nextToken());

        for (int i = 0; i < MAXN; i++) {
            tree[i] = new ArrayList<>();
        }

        stz = new StringTokenizer(in.readLine());
        for (int i = 1; i <= n; i++) {
            prof[i] = Integer.parseInt(stz.nextToken());
            w.add(prof[i]);
        }

        Collections.sort(w);
        w = new ArrayList<>(new LinkedHashSet<>(w));
        for (int val : w) {
            comp.put(val, maxVal++);
        }

        for (int i = 1; i <= n; i++) {
            prof[i] = comp.get(prof[i]);
        }

        for (int i = 2; i <= n; i++) {
            stz = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(stz.nextToken());
            tree[x].add(i);
        }

        dfs(1, -1);
        for (int i = 1; i <= n; i++) {
            arr2.add(new PairWithProf(st[i], en[i], prof[i]));
        }

        arr2.sort(Comparator.comparingInt(a -> a.l));
        int cur = 1;
        for (PairWithProf p : arr2) {
            while (cur < p.l) {
                update(prof[a[cur]], 1, 1, 1, maxVal);
                cur++;
            }
            res.put(new Pair(p.l - 1, p.p), query(p.p + 1, maxVal, 1, 1, maxVal));
        }

        arr2.sort((a, b) -> a.r != b.r ? Integer.compare(a.r, b.r) : Integer.compare(a.l, b.l));
        Arrays.fill(seg, 0);
        cur = 1;
        for (PairWithProf p : arr2) {
            while (cur <= p.r) {
                update(prof[a[cur]], 1, 1, 1, maxVal);
                cur++;
            }
            res.put(new Pair(p.r, p.p), query(p.p + 1, maxVal, 1, 1, maxVal));
        }

        for (int i = 1; i <= n; i++) {
            int ans = res.get(new Pair(en[i], prof[i])) - res.get(new Pair(st[i] - 1, prof[i]));
            out.println(ans);
        }
        out.close();
    }
}

