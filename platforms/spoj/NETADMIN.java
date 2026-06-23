import java.io.*;
import java.util.*;

public class NETADMIN {
    public static class Edge {
        int to, rev, cap;
        Edge(int to, int rev, int cap) {
            this.to = to;
            this.rev = rev;
            this.cap = cap;
        }
    }

    static ArrayList<Edge>[] graph;
    static int[] level, pointer;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            need = new int[k];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < k; i++) {
                need[i] = Integer.parseInt(st.nextToken());
            }
            from = new int[m];
            to = new int[m];
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                from[i] = Integer.parseInt(st.nextToken());
                to[i] = Integer.parseInt(st.nextToken());
            }

            int left = 1, right = k, ans = k;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (helper(mid)) {
                    ans = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            out.append(ans).append('\n');
        }

        System.out.print(out);
        br.close();
    }

    public static void addEdge(int u, int v, int c) {
        graph[u].add(new Edge(v, graph[v].size(), c));
        graph[v].add(new Edge(u, graph[u].size() - 1, 0));
    }

    public static boolean bfs(int s, int t) {
        Arrays.fill(level, -1);
        Queue<Integer> q = new LinkedList<>();
        level[s] = 0;
        q.add(s);

        while (!q.isEmpty()) {
            int u = q.poll();
            for (Edge e : graph[u]) {
                if (e.cap > 0 && level[e.to] == -1) {
                    level[e.to] = level[u] + 1;
                    q.add(e.to);
                }
            }
        }

        return level[t] != -1;
    }

    public static int dfs(int u, int t, int flow) {
        if (u == t) {
            return flow;
        }

        for (; pointer[u] < graph[u].size(); pointer[u]++) {
            Edge e = graph[u].get(pointer[u]);
            if (e.cap > 0 && level[e.to] == level[u] + 1) {
                int pushed = dfs(e.to, t, Math.min(flow, e.cap));
                if (pushed > 0) {
                    e.cap -= pushed;
                    graph[e.to].get(e.rev).cap += pushed;
                    return pushed;
                }
            }
        }

        return 0;
    }

    public static int maxFlow(int s, int t) {
        int flow = 0;
        while (bfs(s, t)) {
            Arrays.fill(pointer, 0);
            int pushed;
            while ((pushed = dfs(s, t, Integer.MAX_VALUE)) > 0) {
                flow += pushed;
            }
        }

        return flow;
    }

    public static int n, m, k;
    public static int[] from, to, need;
    public static boolean helper(int colors) {
        graph = new ArrayList[n + 2];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            addEdge(from[i], to[i], colors);
            addEdge(to[i], from[i], colors);
        }

        int sink = n + 1;
        for (int x : need) {
            addEdge(x, sink, 1);
        }

        level = new int[n + 2];
        pointer = new int[n + 2];
        return maxFlow(1, sink) == k;
    }
}
