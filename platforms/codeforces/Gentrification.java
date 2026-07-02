// Facebook Problem
import java.io.*;
import java.util.*;

public class Gentrification {
    static int[] head, next, to;
    static int[] idx, low, comp;
    static boolean[] onStack;
    static int[] stack;
    static int stackTop;
    static int counter;
    static int sccCount;
    static int[] chead, cnext, cto;
    static int cedgeCount;
    static boolean[][] reach;
    static int[] fhead, fnext, fto;
    static long[] fcap;
    static int fedgeCount;
    static int[] level, it;
    static int V;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        StringBuilder out = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int[] EA = new int[M];
            int[] EB = new int[M];
            head = new int[N];
            for (int i = 0; i < N; i++) {
                head[i] = -1;
            }
            next = new int[M];
            to = new int[M];
            int eCount = 0;
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                EA[i] = a;
                EB[i] = b;
                to[eCount] = b;
                next[eCount] = head[a];
                head[a] = eCount;
                eCount++;
            }

            idx = new int[N];
            low = new int[N];
            comp = new int[N];
            onStack = new boolean[N];
            stack = new int[N];
            stackTop = 0;
            counter = 0;
            sccCount = 0;
            for (int i = 0; i < N; i++) {
                idx[i] = -1;
            }
            for (int i = 0; i < N; i++) {
                if (idx[i] == -1) {
                    tarjan(i);
                }
            }

            int K = sccCount;
            long[] sizes = new long[K];
            for (int i = 0; i < N; i++) {
                sizes[comp[i]]++;
            }

            chead = new int[K];
            for (int i = 0; i < K; i++) {
                chead[i] = -1;
            }
            cnext = new int[M];
            cto = new int[M];
            cedgeCount = 0;
            for (int i = 0; i < M; i++) {
                int cu = comp[EA[i]];
                int cv = comp[EB[i]];
                if (cu != cv) {
                    cto[cedgeCount] = cv;
                    cnext[cedgeCount] = chead[cu];
                    chead[cu] = cedgeCount;
                    cedgeCount++;
                }
            }

            reach = new boolean[K][K];
            for (int s = 0; s < K; s++) {
                boolean[] vis = new boolean[K];
                ArrayDeque<Integer> queue = new ArrayDeque<>();
                vis[s] = true;
                queue.add(s);
                while (!queue.isEmpty()) {
                    int u = queue.poll();
                    for (int e = chead[u]; e != -1; e = cnext[e]) {
                        int v = cto[e];
                        if (!vis[v]) {
                            vis[v] = true;
                            queue.add(v);
                        }
                    }
                }
                for (int t = 0; t < K; t++) {
                    if (t != s && vis[t]) {
                        reach[s][t] = true;
                    }
                }
            }

            int pairCount = 0;
            for (int i = 0; i < K; i++) {
                for (int j = 0; j < K; j++) {
                    if (reach[i][j]) {
                        pairCount++;
                    }
                }
            }

            V = 2 * K + 2;
            int source = 2 * K;
            int sink = 2 * K + 1;
            int maxEdges = 2 * (2 * K + pairCount) + 10;
            fhead = new int[V];
            for (int i = 0; i < V; i++) {
                fhead[i] = -1;
            }
            fnext = new int[maxEdges];
            fto = new int[maxEdges];
            fcap = new long[maxEdges];
            fedgeCount = 0;
            long INF = (long) 4e18;

            for (int i = 0; i < K; i++) {
                addEdge(source, i, sizes[i]);
                addEdge(K + i, sink, sizes[i]);
            }
            for (int i = 0; i < K; i++) {
                for (int j = 0; j < K; j++) {
                    if (reach[i][j]) {
                        addEdge(i, K + j, INF);
                    }
                }
            }

            long flow = 0;
            level = new int[V];
            it = new int[V];
            while (bfs(source, sink)) {
                it = fhead.clone();
                long f;
                while ((f = dfs(source, sink, INF)) > 0) {
                    flow += f;
                }
            }

            long answer = N - flow;
            out.append("Case #").append(tc).append(": ").append(answer).append('\n');
        }
        System.out.print(out);
    }

    public static void tarjan(int u) {
        idx[u] = counter;
        low[u] = counter;
        counter++;
        stack[stackTop++] = u;
        onStack[u] = true;
        for (int e = head[u]; e != -1; e = next[e]) {
            int v = to[e];
            if (idx[v] == -1) {
                tarjan(v);
                low[u] = Math.min(low[u], low[v]);
            } else if (onStack[v]) {
                low[u] = Math.min(low[u], idx[v]);
            }
        }
        if (low[u] == idx[u]) {
            while (true) {
                int v = stack[--stackTop];
                onStack[v] = false;
                comp[v] = sccCount;
                if (v == u) {
                    break;
                }
            }
            sccCount++;
        }
    }

    public static void addEdge(int u, int v, long c) {
        fto[fedgeCount] = v;
        fcap[fedgeCount] = c;
        fnext[fedgeCount] = fhead[u];
        fhead[u] = fedgeCount;
        fedgeCount++;
        fto[fedgeCount] = u;
        fcap[fedgeCount] = 0;
        fnext[fedgeCount] = fhead[v];
        fhead[v] = fedgeCount;
        fedgeCount++;
    }

    public static boolean bfs(int source, int sink) {
        for (int i = 0; i < V; i++) {
            level[i] = -1;
        }
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        level[source] = 0;
        queue.add(source);
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int e = fhead[u]; e != -1; e = fnext[e]) {
                if (fcap[e] > 0 && level[fto[e]] < 0) {
                    level[fto[e]] = level[u] + 1;
                    queue.add(fto[e]);
                }
            }
        }
        return level[sink] >= 0;
    }

    public static long dfs(int u, int sink, long f) {
        if (u == sink) {
            return f;
        }
        for (; it[u] != -1; it[u] = fnext[it[u]]) {
            int e = it[u];
            int v = fto[e];
            if (fcap[e] > 0 && level[v] == level[u] + 1) {
                long d = dfs(v, sink, Math.min(f, fcap[e]));
                if (d > 0) {
                    fcap[e] -= d;
                    fcap[e ^ 1] += d;
                    return d;
                }
            }
        }
        return 0;
    }
}
