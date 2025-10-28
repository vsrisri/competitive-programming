import java.io.*;
import java.util.*;

public class CSTREET {
    public static int[] parent;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < t; tc++) {
            int p = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            Edge[] edges = new Edge[m];
            for (int i = 0; i < m; i++) {
                String line = br.readLine();
                if (line == null) {
                    System.out.println("null");
                }

                st = new StringTokenizer(line);
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                edges[i] = new Edge(u, v, w);
            }

            Arrays.sort(edges);
            parent = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
            }
            long ans = 0;
            int eUsed = 0;
            for (Edge e : edges) {
                if (union(e.u, e.v)) {
                    ans += (long) e.weight * p;
                    eUsed++;
                    if (eUsed == n - 1) {
                        break;
                    }
                }
            }
            System.out.println(ans);
        }
        br.close();
    }

    public static class Edge implements Comparable<Edge> {
        int u, v, weight;

        Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }

        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
    }


    public static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public static boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY) {
            return false;
        }
        parent[rootY] = rootX;
        return true;
    }
}
