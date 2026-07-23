import java.io.*;
import java.util.*;

public class STEAD {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        pref = new int[N][B];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < B; j++) {
                pref[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        cap = new int[B];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < B; i++) {
            cap[i] = Integer.parseInt(st.nextToken());
        }

        int ans = B;
        for (int l = 0; l < B; l++) {
            for (int r = l; r < B; r++) {
                if (r - l + 1 >= ans) {
                    break;
                }
                if (helper(l, r)) {
                    ans = r - l + 1;
                    break;
                }
            }
        }

        System.out.println(ans);
        br.close();
    }

    public static class Edge {
        int to, rev, cap;
        Edge(int to, int rev, int cap) {
            this.to = to;
            this.rev = rev;
            this.cap = cap;
        }
    }

    public static class Dinic {
        ArrayList<Edge>[] g;
        int[] level, pointer;
        int n;

        Dinic(int n) {
            this.n = n;
            g = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                g[i] = new ArrayList<>();
            }
            level = new int[n];
            pointer = new int[n];
        }

        void addEdge(int v, int to, int cap) {
            g[v].add(new Edge(to, g[to].size(), cap));
            g[to].add(new Edge(v, g[v].size() - 1, 0));
        }

        boolean bfs(int s, int t) {
            Arrays.fill(level, -1);
            Queue<Integer> q = new ArrayDeque<>();
            q.add(s);
            level[s] = 0;
            while (!q.isEmpty()) {
                int v = q.poll();
                for (Edge e : g[v]) {
                    if (e.cap > 0 && level[e.to] == -1) {
                        level[e.to] = level[v] + 1;
                        q.add(e.to);
                    }
                }
            }
            return level[t] != -1;
        }

        int dfs(int v, int t, int curr) {
            if (curr == 0) {
                return 0;
            }
            if (v == t) {
                return curr;
            }
            while (pointer[v] < g[v].size()) {
                Edge e = g[v].get(pointer[v]);
                if (e.cap > 0 && level[e.to] == level[v] + 1) {
                    int tr = dfs(e.to, t, Math.min(curr, e.cap));
                    if (tr > 0) {
                        e.cap -= tr;
                        g[e.to].get(e.rev).cap += tr;
                        return tr;
                    }
                }
                pointer[v]++;
            }
            return 0;
        }

        int maxFlow(int s, int t) {
            int flow = 0;
            while (bfs(s, t)) {
                Arrays.fill(pointer, 0);
                while (true) {
                    int curr = dfs(s, t, Integer.MAX_VALUE);
                    if (curr == 0) {
                        break;
                    }
                    flow += curr;
                }
            }
            return flow;
        }
    }

    public static int N, B;
    public static int[][] pref;
    public static int[] cap;
    public static boolean helper(int l, int r) {
        int S = 0;
        int cowStart = 1;
        int bStart = cowStart + N;
        int T = bStart + B;
        Dinic d = new Dinic(T + 1);
        for (int i = 0; i < N; i++) {
            d.addEdge(S, cowStart + i, 1);
            for (int k = l; k <= r; k++) {
                int b = pref[i][k] - 1;
                d.addEdge(cowStart + i, bStart + b, 1);
            }
        }

        for (int i = 0; i < B; i++) {
            d.addEdge(bStart + i, T, cap[i]);
        }

        return d.maxFlow(S, T) == N;
    }

}
