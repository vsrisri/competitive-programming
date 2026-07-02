// CSacademy Problem
import java.io.*;
import java.util.*;

public class Fashion {
    static int[] head, next, to;
    static long[] cap;
    static int edgeCount;
    static int[] level, it;
    static int V;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] type = new int[N + 1];
        long[] cost = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            type[i] = Integer.parseInt(st.nextToken());
            cost[i] = Long.parseLong(st.nextToken());
        }
        int[] A = new int[M + 1];
        int[] B = new int[M + 1];
        int[] C = new int[M + 1];
        long[] W = new long[M + 1];
        long totalW = 0;
        for (int j = 1; j <= M; j++) {
            st = new StringTokenizer(br.readLine());
            A[j] = Integer.parseInt(st.nextToken());
            B[j] = Integer.parseInt(st.nextToken());
            C[j] = Integer.parseInt(st.nextToken());
            W[j] = Long.parseLong(st.nextToken());
            totalW += W[j];
        }

        V = N + M + 2;
        int source = 0;
        int sink = N + M + 1;
        int maxEdges = 2 * (4 * M + N) + 10;
        head = new int[V];
        for (int i = 0; i < V; i++) {
            head[i] = -1;
        }
        next = new int[maxEdges];
        to = new int[maxEdges];
        cap = new long[maxEdges];
        edgeCount = 0;
        long INF = (long) 4e18;
        for (int j = 1; j <= M; j++) {
            int u = source;
            int v = N + j;
            to[edgeCount] = v;
            cap[edgeCount] = W[j];
            next[edgeCount] = head[u];
            head[u] = edgeCount;
            edgeCount++;
            to[edgeCount] = u;
            cap[edgeCount] = 0;
            next[edgeCount] = head[v];
            head[v] = edgeCount;
            edgeCount++;

            int[] items = new int[] { A[j], B[j], C[j] };
            for (int k = 0; k < 3; k++) {
                int itemNode = items[k];
                to[edgeCount] = itemNode;
                cap[edgeCount] = INF;
                next[edgeCount] = head[v];
                head[v] = edgeCount;
                edgeCount++;
                to[edgeCount] = v;
                cap[edgeCount] = 0;
                next[edgeCount] = head[itemNode];
                head[itemNode] = edgeCount;
                edgeCount++;
            }
        }

        for (int i = 1; i <= N; i++) {
            to[edgeCount] = sink;
            cap[edgeCount] = cost[i];
            next[edgeCount] = head[i];
            head[i] = edgeCount;
            edgeCount++;
            to[edgeCount] = i;
            cap[edgeCount] = 0;
            next[edgeCount] = head[sink];
            head[sink] = edgeCount;
            edgeCount++;
        }

        long flow = 0;
        level = new int[V];
        it = new int[V];
        while (bfs(source, sink)) {
            it = head.clone();
            long f;
            while ((f = dfs(source, sink, INF)) > 0) {
                flow += f;
            }
        }

        long profit = totalW - flow;
        boolean[] visited = new boolean[V];
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        visited[source] = true;
        queue.add(source);
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int e = head[u]; e != -1; e = next[e]) {
                if (cap[e] > 0 && !visited[to[e]]) {
                    visited[to[e]] = true;
                    queue.add(to[e]);
                }
            }
        }

        int k = 0;
        StringBuilder list = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (visited[i]) {
                k++;
                list.append(i).append('\n');
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(profit).append(' ').append(k).append('\n');
        sb.append(list);
        System.out.print(sb);
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
            for (int e = head[u]; e != -1; e = next[e]) {
                if (cap[e] > 0 && level[to[e]] < 0) {
                    level[to[e]] = level[u] + 1;
                    queue.add(to[e]);
                }
            }
        }
        return level[sink] >= 0;
    }

    public static long dfs(int u, int sink, long f) {
        if (u == sink) {
            return f;
        }
        for (; it[u] != -1; it[u] = next[it[u]]) {
            int e = it[u];
            int v = to[e];
            if (cap[e] > 0 && level[v] == level[u] + 1) {
                long d = dfs(v, sink, Math.min(f, cap[e]));
                if (d > 0) {
                    cap[e] -= d;
                    cap[e ^ 1] += d;
                    return d;
                }
            }
        }
        return 0;
    }
}
