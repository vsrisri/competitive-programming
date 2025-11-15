import java.io.*;
import java.util.*;

public class CHICAGO {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        StringBuilder out = new StringBuilder();
        while ((line = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(line);
            int n = Integer.parseInt(st.nextToken());
            if (n == 0) {
                break;
            }

            int m = Integer.parseInt(st.nextToken());
            double[] ans = new double[n + 1];
            boolean[] vis = new boolean[n + 1];
            List<List<Edge>> g = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                g.add(new ArrayList<>());
            }
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                double p = Integer.parseInt(st.nextToken()) / 100.0;
                g.get(a).add(new Edge(b, p));
                g.get(b).add(new Edge(a, p));
            }

            Arrays.fill(ans, 0);
            ans[1] = 1.0;
            PriorityQueue<Node> pq = new PriorityQueue<>((x,y)->Double.compare(y.pr, x.pr));
            pq.add(new Node(1, 1.0));
            while (!pq.isEmpty()) {
                int u = pq.poll().v;
                if (vis[u]) {
                    continue;
                }
                vis[u] = true;
                if (u == n) {
                    break;
                }
                for (Edge e : g.get(u)) {
                    if (ans[u] * e.p > ans[e.to]) {
                        ans[e.to] = ans[u] * e.p;
                        pq.add(new Node(e.to, ans[e.to]));
                    }
                }
            }
            out.append(String.format("%.6f percent%n", ans[n] * 100.0));
        }
        System.out.print(out);
        br.close();
    }

    public static class Edge {
        int to;
        double p;
        Edge(int t, double p) {
            to = t;
            this.p = p;
        }
    }

    public static class Node {
        int v;
        double pr;
        Node(int v, double pr) {
            this.v = v;
            this.pr = pr;
        }
    }
}
