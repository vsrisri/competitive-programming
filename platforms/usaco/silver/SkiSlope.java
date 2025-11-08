import java.io.*;
import java.util.*;

public class SkiSlope {
    public static class Info {
        int src, dest, d, e;
        
        Info(int src, int dest, int d, int e) {
            this.src = src;
            this.dest = dest;
            this.d = d;
            this.e = e;
        }
    }
    
    public static List<List<Info>> tree;
    public static long[] ps, lvl;
    public static Multiset<Integer>[] obstacles;
    public static long[][] amnt;
    public static List<Triple> qvals;

    public static class Triple {
        int first, second, third;
        
        Triple(int first, int second, int third) {
            this.first = first;
            this.second = second;
            this.third = third;
        }
    }

    public static void dfs(int node) {
        for (Info nxt : tree.get(node)) {
            int nw = nxt.dest;
            ps[nw] = ps[node] + nxt.e;
            lvl[nw] = lvl[node] + 1;
            dfs(nw);
        }
    }

    public static void dfs2(int node, int[] minvals) {
        int[] minvals2 = minvals.clone();
        
        for (int i = 0; i <= 10; i++) {
            int L = 0, R = qvals.size() - 1, ans = qvals.size();
            while (L <= R) {
                int mid = (L + R) / 2;
                if (qvals.get(mid).first >= minvals[i]) {
                    ans = mid;
                    R = mid - 1;
                } else {
                    L = mid + 1;
                }
            }
            if (ans != qvals.size()) {
                amnt[i][ans] = Math.max(amnt[i][ans], ps[node]);
            }
        }

        for (int i = 0; i < tree.get(node).size(); i++) {
            int nxt = tree.get(node).get(i).dest;
            for (int pos = 0; pos <= 10; pos++) {
                obstacles[pos].add(tree.get(node).get(i).d);
            }
            int[] removals = new int[11];
            Arrays.fill(removals, -1);
            for (int pos = 0; pos <= 10; pos++) {
                while (obstacles[pos].size() > pos) {
                    removals[pos] = obstacles[pos].first();
                    obstacles[pos].remove(removals[pos]);
                }
                minvals[pos] = Math.max(minvals[pos], removals[pos]);
            }
            dfs2(nxt, minvals);
            for (int pos = 0; pos <= 10; pos++) {
                if (removals[pos] != -1) {
                    obstacles[pos].add(removals[pos]);
                }
            }
            minvals = minvals2.clone();
            for (int pos = 0; pos <= 10; pos++) {
                if (obstacles[pos].contains(tree.get(node).get(i).d)) {
                    obstacles[pos].remove(tree.get(node).get(i).d);
                }
            }
        }
    }

    public static void solve() throws IOException {
        int n = Integer.parseInt(br.readLine());
        
        int[] parent = new int[n + 1];
        List<Info> data = new ArrayList<>();
        tree = new ArrayList<>(n + 1);
        ps = new long[n + 1];
        lvl = new long[n + 1];
        
        for (int i = 0; i <= n; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 2; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int src = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            data.add(new Info(src, i, d, e));
            parent[i] = src;
            tree.get(src).add(new Info(src, i, d, e));
        }

        dfs(1);

        int q = Integer.parseInt(br.readLine());
        qvals = new ArrayList<>();
        for (int i = 0; i < q; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            qvals.add(new Triple(a, b, i));
        }

        qvals.sort(Comparator.comparingInt(a -> a.first));

        long[] ans = new long[q];
        int[] mx = new int[11];
        amnt = new long[11][q + 1];
        
        obstacles = new TreeSet[11];
        for (int i = 0; i <= 10; i++) {
            obstacles[i] = new TreeSet<>();
        }

        dfs2(1, mx);

        for (int j = 0; j < q; j++) {
            if (j > 0) {
                for (int i = 0; i <= 10; i++) {
                    amnt[i][j] = Math.max(amnt[i][j], amnt[i][j - 1]);
                }
            }
            ans[qvals.get(j).third] = amnt[qvals.get(j).second][j];
        }

        StringBuilder sb = new StringBuilder();
        for (long val : ans) {
            sb.append(val).append("\n");
        }
        System.out.print(sb);
    }

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        solve();
    }
}

