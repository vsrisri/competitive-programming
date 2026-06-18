import java.io.*;
import java.util.*;

public class CardGame {
    static int[] head;
    static int[] next;
    static int[] to;
    static long[] cap;
    static int edgeCount;
    static int[] level;
    static int[] it;
    static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] p = new int[n];
        int[] c = new int[n];
        int[] l = new int[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            p[i] = Integer.parseInt(st.nextToken());
            c[i] = Integer.parseInt(st.nextToken());
            l[i] = Integer.parseInt(st.nextToken());
        }
        int maxC = 200001;
        boolean[] comp = new boolean[maxC + 1];
        comp[0] = true;
        comp[1] = true;
        for (int i = 2; (long) i * i <= maxC; i++) {
            if (!comp[i]) {
                for (int j = i * i; j <= maxC; j += i) {
                    comp[j] = true;
                }
            }
        }
        int ans = -1;
        for (int L = 1; L <= n; L++) {
            ArrayList<Integer> oddIdx = new ArrayList<Integer>();
            ArrayList<Integer> evenIdx = new ArrayList<Integer>();
            int best = -1;
            long total = 0;
            for (int i = 0; i < n; i++) {
                if (l[i] <= L) {
                    if (c[i] == 1) {
                        if (best == -1 || p[i] > p[best]) {
                            best = i;
                        }
                    } else if (c[i] % 2 == 1) {
                        oddIdx.add(i);
                        total += p[i];
                    } else {
                        evenIdx.add(i);
                        total += p[i];
                    }
                }
            }
            if (best != -1) {
                oddIdx.add(best);
                total += p[best];
            }
            int oc = oddIdx.size();
            int ec = evenIdx.size();
            int nodes = oc + ec + 2;
            int source = 0;
            int sink = nodes - 1;
            int aArr = oc + ec + oc * ec;
            fix(nodes, aArr);
            for (int i = 0; i < oc; i++) {
                addEdge(source, i + 1, p[oddIdx.get(i)]);
            }
            for (int j = 0; j < ec; j++) {
                addEdge(oc + 1 + j, sink, p[evenIdx.get(j)]);
            }
            long max = 1000000000L;
            for (int i = 0; i < oc; i++) {
                int ci = c[oddIdx.get(i)];
                for (int j = 0; j < ec; j++) {
                    int cj = c[evenIdx.get(j)];
                    int sum = ci + cj;
                    if (sum <= maxC && !comp[sum]) {
                        addEdge(i + 1, oc + 1 + j, max);
                    }
                }
            }
            long mincut = maxflow(source, sink);
            long poss = total - mincut;
            if (poss >= k) {
                ans = L;
                break;
            }
        }
        System.out.println(ans);
    }

    public static void fix(int n, int aArr) {
        N = n;
        head = new int[n];
        Arrays.fill(head, -1);
        int size = (aArr + 5) * 2;
        next = new int[size];
        to = new int[size];
        cap = new long[size];
        edgeCount = 0;
    }

    public static void addEdge(int u, int v, long c) {
        to[edgeCount] = v;
        cap[edgeCount] = c;
        next[edgeCount] = head[u];
        head[u] = edgeCount;
        edgeCount++;
        to[edgeCount] = u;
        cap[edgeCount] = 0;
        next[edgeCount] = head[v];
        head[v] = edgeCount;
        edgeCount++;
    }

    public static boolean bfs(int s, int t) {
        level = new int[N];
        Arrays.fill(level, -1);
        level[s] = 0;
        int[] queue = new int[N];
        int qh = 0;
        int qt = 0;
        queue[qt] = s;
        qt++;
        while (qh < qt) {
            int u = queue[qh];
            qh++;
            for (int e = head[u]; e != -1; e = next[e]) {
                if (cap[e] > 0 && level[to[e]] < 0) {
                    level[to[e]] = level[u] + 1;
                    queue[qt] = to[e];
                    qt++;
                }
            }
        }
        return level[t] >= 0;
    }

    public static long dfs(int u, int t, long f) {
        if (u == t) {
            return f;
        }
        for (; it[u] != -1; it[u] = next[it[u]]) {
            int e = it[u];
            int v = to[e];
            if (cap[e] > 0 && level[v] == level[u] + 1) {
                long d = dfs(v, t, Math.min(f, cap[e]));
                if (d > 0) {
                    cap[e] -= d;
                    cap[e ^ 1] += d;
                    return d;
                }
            }
        }
        return 0;
    }

    public static long maxflow(int s, int t) {
        long flow = 0;
        while (bfs(s, t)) {
            it = head.clone();
            long f = dfs(s, t, Long.MAX_VALUE);
            while (f > 0) {
                flow += f;
                f = dfs(s, t, Long.MAX_VALUE);
            }
        }
        return flow;
    }
}
