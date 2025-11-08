import java.io.*;
import java.util.*;

public class Visits {
    static class DSU {
        long[] parent;
        DSU(int n) {
            parent = new long[n];
            Arrays.fill(parent, -1);
        }
        int find(int x) {
            if (parent[x] < 0) return x;
            parent[x] = find((int) parent[x]);
            return (int) parent[x];
        }
        boolean sameSet(int a, int b) {
            return find(a) == find(b);
        }
        long size(int x) {
            return -parent[find(x)];
        }
        boolean unite(int a, int b) {
            a = find(a);
            b = find(b);
            if (a == b) return false;
            if (parent[a] > parent[b]) {
                int tmp = a; a = b; b = tmp;
            }
            parent[a] += parent[b];
            parent[b] = a;
            return true;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[][] edges = new int[n][3];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken());
            edges[i][0] = v;
            edges[i][1] = a;
            edges[i][2] = i;
        }
        Arrays.sort(edges, (x, y) -> Integer.compare(y[0], x[0]));
        DSU dsu = new DSU(n);
        long total = 0;
        for (int[] edge : edges) {
            int v = edge[0], a = edge[1], i = edge[2];
            if (dsu.find(a) != dsu.find(i)) {
                dsu.unite(a, i);
                total += v;
            }
        }
        System.out.println(total);
    }
}

