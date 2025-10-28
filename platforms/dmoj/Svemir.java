emport java.io.*;
import java.util.*;

public class Svemir {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Edge> edges = new ArrayList<>();
        Planet[] planets = new Planet[n];

        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");
            int x = Integer.parseInt(line[0]);
            int y = Integer.parseInt(line[1]);
            int z = Integer.parseInt(line[2]);
            planets[i] = new Planet(x, y, z, i);
        }

        for (int dim = 0; dim < 3; dim++) {
            final int currentDim = dim;
            Arrays.sort(planets, Comparator.comparingInt(p -> p.get(currentDim)));
            for (int i = 1; i < n; i++) {
                int co = planets[i].distanceTo(planets[i - 1]);
                edges.add(new Edge(planets[i - 1].idx, planets[i].idx, co));
            }
        }

        Collections.sort(edges);
        UnionFind uf = new UnionFind(n);
        long totalco = 0;
        int edgesUsed = 0;

        for (Edge edge : edges) {
            if (uf.union(edge.a, edge.b)) {
                totalco += edge.co;
                edgesUsed++;
                if (edgesUsed == n - 1) {
                    break;
                }
            }
        }

        System.out.println(totalco);
    }

    public static class Planet {
        int x, y, z, idx;

        public Planet(int x, int y, int z, int idx) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.idx = idx;
        }

        public int get(int dim) {
            return dim == 0 ? x : dim == 1 ? y : z;
        }

        public int distanceTo(Planet other) {
            return Math.min(Math.abs(this.x - other.x), Math.min(Math.abs(this.y - other.y), Math.abs(this.z - other.z)));
        }
    }

    static class Edge implements Comparable<Edge> {
        int a, b, co;

        public Edge(int a, int b, int co) {
            this.a = a;
            this.b = b;
            this.co = co;
        }

        public int compareTo(Edge other) {
            return Integer.compare(this.co, other.co);
        }
    }

    public static class UnionFind {
        int[] parent, size;

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            Arrays.fill(parent, -1);
            Arrays.fill(size, 1);  
        }

        public int find(int x) {
            if (parent[x] == -1) {
                return x;
            }
            return parent[x] = find(parent[x]); 
        }

        public boolean union(int x, int y) {
            int rootX = find(x), rootY = find(y);
            if (rootX == rootY) {
                return false;  
            }

            if (size[rootX] < size[rootY]) {
                parent[rootX] = rootY;
                size[rootY] += size[rootX];
            } else {
                parent[rootY] = rootX;
                size[rootX] += size[rootY];
            }
            return true;
        }
    }
}

