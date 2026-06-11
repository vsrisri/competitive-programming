import java.io.*;
import java.util.*;

public class DistinctRoutes {
    public static ArrayList<Edge>[] graph;
    public static int n;
    public static int m;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            addEdge(a, b);
        }

        int routes = maxFlow(1, n);
        StringBuilder sb = new StringBuilder();
        sb.append(routes).append('\n');
        for (int i = 0; i < routes; i++) {
            ArrayList<Integer> path = new ArrayList<>();
            dfsPath(1, n, path);
            sb.append(path.size()).append('\n');
            for (int j = 0; j < path.size(); j++) {
                if (j > 0) {
                    sb.append(' ');
                }

                sb.append(path.get(j));
            }

            sb.append('\n');
        }

        System.out.print(sb);
        br.close();
    }

    public static class Edge {
        int to;
        int rev;
        int cap;
        int flow;

        Edge(int to, int rev, int cap) {
            this.to = to;
            this.rev = rev;
            this.cap = cap;
            this.flow = 0;
        }
    }

    public static void addEdge(int u, int v) {
        Edge a = new Edge(v, graph[v].size(), 1);
        Edge b = new Edge(u, graph[u].size(), 0);

        graph[u].add(a);
        graph[v].add(b);
    }

    public static int maxFlow(int source, int sink) {
        int flow = 0;
        while (true) {
            int[] parentNode = new int[n + 1];
            int[] parentEdge = new int[n + 1];
            Arrays.fill(parentNode, -1);
            Queue<Integer> q = new ArrayDeque<>();
            q.add(source);
            parentNode[source] = source;
            while (!q.isEmpty() && parentNode[sink] == -1) {
                int u = q.poll();
                for (int i = 0; i < graph[u].size(); i++) {
                    Edge e = graph[u].get(i);
                    if (parentNode[e.to] == -1 && e.cap > 0) {
                        parentNode[e.to] = u;
                        parentEdge[e.to] = i;
                        q.add(e.to);
                    }
                }
            }

            if (parentNode[sink] == -1) {
                break;
            }

            int cur = sink;
            while (cur != source) {
                int prev = parentNode[cur];
                Edge e = graph[prev].get(parentEdge[cur]);
                e.cap--;
                graph[cur].get(e.rev).cap++;
                e.flow++;
                graph[cur].get(e.rev).flow--;
                cur = prev;
            }

            flow++;
        }

        return flow;
    }

    public static boolean dfsPath(int u, int sink, ArrayList<Integer> path) {
        path.add(u);
        if (u == sink) {
            return true;
        }
        for (Edge e : graph[u]) {
            if (e.flow > 0) {
                e.flow--;
                if (dfsPath(e.to, sink, path)) {
                    return true;
                }

                e.flow++;
            }
        }

        path.remove(path.size() - 1);
        return false;
    }
}
