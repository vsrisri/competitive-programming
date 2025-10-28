import java.io.*;
import java.util.*;

public class LCA {
    public static ArrayList<ArrayList<Integer>> tree;
    public static int[] depth;
    public static int[][] parent;
    public static int l;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(reader.readLine().trim());

        for (int t = 1; t <= tc; t++) {
            int n = Integer.parseInt(reader.readLine());
            tree = new ArrayList<>(n + 1);
            for (int idx = 0; idx <= n; idx++) {
                tree.add(new ArrayList<>());
            }

            for (int idx = 1; idx <= n; idx++) {
                String[] arr = reader.readLine().trim().split("\\s+");
                int m = Integer.parseInt(arr[0]);
                for (int idx2 = 1; idx2 <= m; idx2++) {
                    int child = Integer.parseInt(arr[idx2]);
                    tree.get(idx).add(child);
                }
            }

            boolean[] isChild = new boolean[n + 1];
            for (int idx = 1; idx <= n; idx++) {
                for (int child : tree.get(idx)) {
                    isChild[child] = true;
                }
            }
            int root = 1;
            while (isChild[root]) {
                root++;
            }

            l = (int) Math.ceil(Math.log(n) / Math.log(2));
            depth = new int[n + 1];
            parent = new int[n + 1][l + 1];
            dfs(root, 0);

            for (int j = 1; j <= l; j++) {
                for (int i = 1; i <= n; i++) {
                    if (parent[i][j - 1] != 0) {
                        parent[i][j] = parent[parent[i][j - 1]][j - 1];
                    }
                }
            }

            int q = Integer.parseInt(reader.readLine());
            StringBuilder sb = new StringBuilder();
            sb.append("Case ").append(t).append(":\n");
            for (int idx = 0; idx < q; idx++) {
                String[] arr = reader.readLine().trim().split("\\s+");
                int v = Integer.parseInt(arr[0]);
                int w = Integer.parseInt(arr[1]);
                sb.append(lca(v, w)).append("\n");
            }
            System.out.print(sb);
        }
        reader.close();
    }

    public static void dfs(int node, int par) {
        parent[node][0] = par;
        for (int child : tree.get(node)) {
            if (child != par) {
                depth[child] = depth[node] + 1; 
                dfs(child, node);
            }
        }
    }

    public static int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];
        for (int i = 0; i <= l; i++) {
            if ((diff & (1 << i)) != 0) {
                u = parent[u][i];
            }
        }

        if (u == v) {
            return u;
        }

        for (int i = l; i >= 0; i--) {
            if (parent[u][i] != 0 && parent[u][i] != parent[v][i]) {
                u = parent[u][i];
                v = parent[v][i];
            }
        }

        return parent[u][0];
    }
}/*
// Incomplete
import java.io.*;
import java.util.*;

public class LCA {
    static List<List<Integer>> tree;
    static int[] depth;
    static int[][] parent;
    static int l;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(reader.readLine().trim());

        for (int t = 1; t <= tc; t++) {
            int n = Integer.parseInt(reader.readLine());
            tree = new ArrayList<>();
            for (int idx = 0; idx <= n; idx++) {
                tree.add(new ArrayList<>());
            }

            for (int idx = 1; idx <= n; idx++) {
                String[] arr = reader.readLine().trim().split("\\s+");
                int m = Integer.parseInt(arr[0]);
                for (int idx2 = 1; idx2 <= m; idx2++) {
                    int child = Integer.parseInt(arr[idx2]);
                    tree.get(idx).add(child);
                }
            }

            boolean[] isChild = new boolean[n + 1];
            for (int idx = 1; idx <= n; idx++) {
                for (int child : tree.get(idx)) {
                    isChild[child] = true;
                }
            }
            int root = 1;
            while (isChild[root]) {
                root++;
            }

            l = (int) Math.ceil(Math.log(n) / Math.log(2));
            depth = new int[n + 1];
            parent = new int[n + 1][l + 1];
            dfs(root, 0);

            for (int j = 1; j <= l; j++) {
                for (int i = 1; i <= n; i++) {
                    if (parent[i][j - 1] != 0) {
                        parent[i][j] = parent[parent[i][j - 1]][j - 1];
                    }
                }
            }

            int q = Integer.parseInt(reader.readLine());
            System.out.println("Case " + t + ":");
            for (int idx = 0; idx < q; idx++) {
                String[] arr = reader.readLine().trim().split("\\s+");
                int v = Integer.parseInt(arr[0]);
                int w = Integer.parseInt(arr[1]);
                System.out.println(lca(v, w));
            }
        }
        reader.close();
    }

    static void dfs(int node, int par) {
        parent[node][0] = par;
        for (int child : tree.get(node)) {
            if (child != par) {
                depth[child] = depth[node] + 1;
                dfs(child, node);
            }
        }
    }

    static int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];
        for (int i = 0; i <= l; i++) {
            if ((diff & (1 << i)) != 0) {
                u = parent[u][i];
            }
        }

        if (u == v) {
            return u;
        }

        for (int i = l; i >= 0; i--) {
            if (parent[u][i] != 0 && parent[u][i] != parent[v][i]) {
                u = parent[u][i];
                v = parent[v][i];
            }
        }

        return parent[u][0];
    }
}
/*
import java.io.*;
import java.util.*;

public class LCA {
    static List<List<Integer>> tree;
    static int[] depth;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(reader.readLine().trim());

        for (int t = 1; t <= tc; t++) {
            int n = Integer.parseInt(reader.readLine());
            tree = new ArrayList<>();
            depth = new int[n + 1];
            parent = new int[n + 1];
            for (int idx = 0; idx <= n; idx++) {
                tree.add(new ArrayList<>());
            }

            for (int idx = 1; idx <= n; idx++) {
                String[] arr = reader.readLine().trim().split("\\s+");
                int m = Integer.parseInt(arr[0]);
                for (int idx2 = 1; idx2 <= m; idx2++) {
                    int child = Integer.parseInt(arr[idx2]);
                    tree.get(idx).add(child);
                    parent[child] = idx;
                }
            }

            int root = 1;
            while (parent[root] != 0) {
                root = parent[root];
            }

            dfs(root, 0);
            int q = Integer.parseInt(reader.readLine());
            System.out.println("Case " + t + ":");
            for (int idx = 0; idx < q; idx++) {
                String[] arr = reader.readLine().trim().split("\\s+");
                int v = Integer.parseInt(arr[0]);
                int w = Integer.parseInt(arr[1]);
                System.out.println(lca(v, w));
            }
        }
        reader.close();
    }

    public static void dfs(int node, int d) {
        depth[node] = d;
        for (int child : tree.get(node)) {
            dfs(child, d + 1);
        }
    }

    public static int lca(int v, int w) {
        while (depth[v] > depth[w]) {
            v = parent[v];
        }
        while (depth[w] > depth[v]) {
            w = parent[w];
        }

        while (v != w) {
            v = parent[v];
            w = parent[w];
        }
        return v;
    }
}*/
