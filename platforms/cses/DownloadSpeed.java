import java.util.*;
import java.io.*;

public class DownloadSpeed {
    public static int n, m;
    public static int[] head, nxt, to;
    public static long[] cap;
    public static int edgeCount;
    public static int[] level;
    public static int[] iter;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        head = new int[n + 1];
        Arrays.fill(head, -1);
        nxt = new int[2 * m];
        to = new int[2 * m];
        cap = new long[2 * m];
        edgeCount = 0;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            addEdge(a, b, c);
        }
        long result = maxflow(1, n);
        System.out.println(result);
    }

    public static void addEdge(int a, int b, long c) {
        to[edgeCount] = b;
        cap[edgeCount] = c;
        nxt[edgeCount] = head[a];
        head[a] = edgeCount;
        edgeCount++;
        to[edgeCount] = a;
        cap[edgeCount] = 0;
        nxt[edgeCount] = head[b];
        head[b] = edgeCount;
        edgeCount++;
    }

    public static boolean bfs(int s, int t) {
        level = new int[n + 1];
        Arrays.fill(level, -1);
        level[s] = 0;
        int[] queue = new int[n + 1];
        int qh = 0, qt = 0;
        queue[qt++] = s;
        while (qh < qt) {
            int u = queue[qh++];
            for (int e = head[u]; e != -1; e = nxt[e]) {
                if (cap[e] > 0 && level[to[e]] == -1) {
                    level[to[e]] = level[u] + 1;
                    queue[qt++] = to[e];
                }
            }
        }
        return level[t] != -1;
    }

    public static long dfs(int u, int t, long f) {
        if (u == t) {
            return f;
        }
        for (; iter[u] != -1; iter[u] = nxt[iter[u]]) {
            int e = iter[u];
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
            iter = head.clone();
            long f;
            while ((f = dfs(s, t, Long.MAX_VALUE)) > 0) {
                flow += f;
            }
        }
        return flow;
    }
}
