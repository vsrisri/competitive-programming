import java.util.*;
import java.io.*;

public class FASTFLOW {
    public static int[] head, next, to, level, iter;
    public static long[] cap;
    public static int count;
    public static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        fix(N, M);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            if (a != b) {
                addEdge(a, b, c);
            }
        }
        System.out.println(maxflow(1, N));
    }

    public static void fix(int n, int m) {
        head = new int[n + 1];
        Arrays.fill(head, -1);
        next = new int[m * 2];
        to = new int[m * 2];
        cap = new long[m * 2];
        level = new int[n + 1];
        iter = new int[n + 1];
        count = 0;
    }

    public static void addEdge(int u, int v, long c) {
        to[count] = v;
        cap[count] = c;
        next[count] = head[u];
        head[u] = count++;
        to[count] = u;
        cap[count] = c;
        next[count] = head[v];
        head[v] = count++;
    }

    public static boolean bfs(int s, int t) {
        Arrays.fill(level, -1);
        Queue<Integer> q = new ArrayDeque<>();
        level[s] = 0;
        q.add(s);
        while (!q.isEmpty()) {
            int v = q.poll();
            for (int e = head[v]; e != -1; e = next[e]) {
                if (cap[e] > 0 && level[to[e]] < 0) {
                    level[to[e]] = level[v] + 1;
                    q.add(to[e]);
                }
            }
        }
        return level[t] >= 0;
    }

    public static long dfs(int v, int t, long f) {
        if (v == t) {
            return f;
        }
        for (; iter[v] != -1; iter[v] = next[iter[v]]) {
            int e = iter[v];
            int u = to[e];
            if (cap[e] > 0 && level[v] < level[u]) {
                long d = dfs(u, t, Math.min(f, cap[e]));
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
            System.arraycopy(head, 0, iter, 0, N + 1);
            long d;
            while ((d = dfs(s, t, Long.MAX_VALUE)) > 0) {
                flow += d;
            }
        }
        return flow;
    }
}
