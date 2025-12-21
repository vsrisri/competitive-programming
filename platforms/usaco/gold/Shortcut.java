import java.io.*;
import java.util.*;

public class Shortcut {
    static class Edge {
        int to, weight;
        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static class Node implements Comparable<Node> {
        long dist;
        int field, parent;
        Node(long dist, int field, int parent) {
            this.dist = dist;
            this.field = field;
            this.parent = parent;
        }

        public int compareTo(Node other) {
            return Long.compare(this.dist, other.dist);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("shortcut.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("shortcut.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int shortcutTime = Integer.parseInt(st.nextToken());
        long[] cows = new long[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            cows[i] = Long.parseLong(st.nextToken());
        }

        List<Edge>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            graph[a].add(new Edge(b, t));
            graph[b].add(new Edge(a, t));
        }

        long[] dist = new long[n + 1];
        int[] parent = new int[n + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        Arrays.fill(parent, Integer.MAX_VALUE);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[1] = 0;
        parent[1] = 0;
        pq.add(new Node(0, 1, 0));
        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            int u = curr.field;
            long d = curr.dist;
            int p = curr.parent;
            if (parent[u] != p) {
                continue;
            }

            for (Edge e : graph[u]) {
                int v = e.to, w = e.weight;
                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    parent[v] = u;
                    pq.add(new Node(dist[v], v, u));
                } else if (dist[u] + w == dist[v] && u < parent[v]) {
                    parent[v] = u;
                    pq.add(new Node(dist[v], v, u));
                }
            }
        }

        long[] flow = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            int curr = i;
            while (curr != Integer.MAX_VALUE) {
                flow[curr] += cows[i];
                curr = parent[curr];
            }
        }

        long maxSave = 0;
        for (int i = 2; i <= n; i++) {
            long timeSaved = flow[i] * (dist[i] - shortcutTime);
            if (timeSaved > maxSave) {
                maxSave = timeSaved;
            }
        }

        pw.println(maxSave);
        pw.close();
    }
}

