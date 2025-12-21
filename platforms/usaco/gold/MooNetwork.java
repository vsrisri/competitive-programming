import java.io.*;
import java.util.*;

public class MooNetwork {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<int[]> points = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points.add(new int[]{x, y});
        }

        points.sort(Comparator.comparingInt((int[] p) -> p[0]).thenComparingInt(p -> p[1]));
        List<Edge> edges = new ArrayList<>();
        int[][] last = new int[11][2]; 
        for (int[] row : last) {
            Arrays.fill(row, -1);
        }

        for (int i = 0; i < n; i++) {
            int[] point = points.get(i);
            for (int j = 0; j <= 10; j++) {
                if (last[j][0] != -1) {
                    edges.add(new Edge(i, last[j][1], dist(point, new int[]{last[j][0], j})));
                }
            }
            last[point[1]] = new int[]{point[0], i};
        }

        edges.sort(null);
        DSU dsu = new DSU(n);
        long cost = 0;
        int components = n;
        for (Edge edge : edges) {
            if (dsu.unite(edge.a, edge.b)) {
                cost += edge.w;
                components--;
                if (components == 1) {
                    break; 
                }
            }
        }

        System.out.println(cost);
    }

    public static class DSU {
        int[] parent;
        DSU(int n) {
            parent = new int[n];
            Arrays.fill(parent, -1);
        }

        public int get(int x) {
            if (parent[x] < 0) {
                return x;
            }
            return parent[x] = get(parent[x]);
        }

        public boolean unite(int x, int y) {
            x = get(x);
            y = get(y);
            if (x == y) {
                return false;
            }
            if (parent[x] > parent[y]) {
                int temp = x;
                x = y;
                y = temp;
            }
            parent[x] += parent[y];
            parent[y] = x;
            return true;
        }
    }

    public static class Edge implements Comparable<Edge> {
        int a, b;
        long w;

        Edge(int a, int b, long w) {
            this.a = a;
            this.b = b;
            this.w = w;
        }

        public int compareTo(Edge other) {
            return Long.compare(this.w, other.w);
        }
    }

    public static long dist(int[] a, int[] b) {
        long dx = a[0] - b[0];
        long dy = a[1] - b[1];
        return dx * dx + dy * dy;
    }
}

