import java.io.*;
import java.util.*;

public class PoliceChase {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Dinic dinic = new Dinic(n);
        int[][] edges = new int[m][2];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            edges[i][0] = a;
            edges[i][1] = b;
            dinic.addEdge(a, b, 1);
            dinic.addEdge(b, a, 1);
        }

        dinic.maxFlow(0, n - 1);
        boolean[] visited = new boolean[n];
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(0);
        visited[0] = true;
        while (!q.isEmpty()) {
            int v = q.poll();
            for (Edge e : dinic.graph[v]) {
                if (e.cap > 0 && !visited[e.to]) {
                    visited[e.to] = true;
                    q.add(e.to);
                }
            }
        }

        ArrayList<int[]> cut = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int a = edges[i][0];
            int b = edges[i][1];
            if (visited[a] != visited[b]) {
                cut.add(new int[] {a + 1, b + 1});
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(cut.size()).append('\n');
        for (int[] e : cut) {
            sb.append(e[0]).append(' ').append(e[1]).append('\n');
        }

        System.out.print(sb);
    }

    public static class Edge {
        int to;
        int rev;
        long cap;

        Edge(int to, int rev, long cap) {
            this.to = to;
            this.rev = rev;
            this.cap = cap;
        }
    }

    public static class Dinic {
        ArrayList<Edge>[] graph;
        int[] level;
        int[] point;
        public Dinic(int n) {
            graph = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                graph[i] = new ArrayList<>();
            }
            level = new int[n];
            point = new int[n];
        }

        public void addEdge(int v, int to, long cap) {
            Edge a = new Edge(to, graph[to].size(), cap);
            Edge b = new Edge(v, graph[v].size(), 0);
            graph[v].add(a);
            graph[to].add(b);
        }

        public boolean bfs(int s, int t) {
            Arrays.fill(level, -1);
            ArrayDeque<Integer> q = new ArrayDeque<>();
            q.add(s);
            level[s] = 0;
            while (!q.isEmpty()) {
                int v = q.poll();
                for (Edge e : graph[v]) {
                    if (e.cap > 0 && level[e.to] == -1) {
                        level[e.to] = level[v] + 1;
                        q.add(e.to);
                    }
                }
            }

            return level[t] != -1;
        }

        public long dfs(int v, int t, long pushed) {
            if (pushed == 0) {
                return 0;
            }

            if (v == t) {
                return pushed;
            }

            while (point[v] < graph[v].size()) {
                Edge e = graph[v].get(point[v]);
                if (e.cap > 0 && level[e.to] == level[v] + 1) {
                    long tr = dfs(e.to, t, Math.min(pushed, e.cap));
                    if (tr > 0) {
                        e.cap -= tr;
                        graph[e.to].get(e.rev).cap += tr;
                        return tr;
                    }
                }

                point[v]++;
            }

            return 0;
        }

        public long maxFlow(int s, int t) {
            long flow = 0;
            while (bfs(s, t)) {
                Arrays.fill(point, 0);
                while (true) {
                    long pushed = dfs(s, t, Long.MAX_VALUE);
                    if (pushed == 0) {
                        break;
                    }

                    flow += pushed;
                }
            }

            return flow;
        }
    }
}
