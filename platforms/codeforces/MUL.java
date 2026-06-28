// Atcoder Problem
import java.io.*;
import java.util.*;

public class MUL {
    static int[] head, next, to;
    static long[] cap;
    static int edgeCount;
    static int[] level, it;
    static int n;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());
        StringTokenizer st = new StringTokenizer(br.readLine());
        long[] a = new long[N + 1];
        for (int i = 1; i <= N; i++) { 
            a[i] = Long.parseLong(st.nextToken()); 
        }

        int source = 0;
        int sink = 1;
        int base = 2;
        int totalNodes = base + N + 1;
        int maxEdges = 2 * (N + 1 + N * 12 + 10);
        n = totalNodes;
        head = new int[totalNodes];
        for (int i = 0; i < totalNodes; i++) { 
            head[i] = -1; 
        }

        next = new int[maxEdges];
        to = new int[maxEdges];
        cap = new long[maxEdges];
        edgeCount = 0;

        long INF = Long.MAX_VALUE / 4;
        long totalPos = 0;
        for (int i = 1; i <= N; i++) {
            int node = base + i;
            if (a[i] <= 0) {
                addEdge(source, node, -a[i]);
            } else {
                addEdge(node, sink, a[i]);
                totalPos += a[i];
            }
            for (int j = 2 * i; j <= N; j += i) {
                addEdge(node, base + j, INF);
            }
        }

        long cut = maxflow(source, sink);
        long ans = totalPos - cut;
        System.out.println(ans);
        br.close();
    }

    public static void addEdge(int u, int v, long c) {
        to[edgeCount] = v; cap[edgeCount] = c; 
        next[edgeCount] = head[u]; head[u] = edgeCount++;
        to[edgeCount] = u; cap[edgeCount] = 0; 
        next[edgeCount] = head[v]; head[v] = edgeCount++;
    }

    public static boolean bfs(int s, int t) {
        level = new int[n];
        for (int i = 0; i < n; i++) { 
            level[i] = -1; 
        }
        ArrayDeque<Integer> q = new ArrayDeque<>();
        level[s] = 0; q.add(s);
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int e = head[u]; e != -1; e = next[e]) {
                if (cap[e] > 0 && level[to[e]] < 0) {
                    level[to[e]] = level[u] + 1;
                    q.add(to[e]);
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
            long f;
            while ((f = dfs(s, t, Long.MAX_VALUE)) > 0) { flow += f; }
        }
        return flow;
    }
}
