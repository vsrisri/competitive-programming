import java.io.*;
import java.util.*;

public class PT07Y {
    public static List<Integer>[] g;
    public static boolean[] visited;
    public static boolean ans = false;

    //Recursive Solution 
    public static void dfs(int u, int p) {
        visited[u] = true;
        for (int v : g[u]) {
            if (!visited[v]) {
                dfs(v, u);
            }
            else if (v != p) {
                ans = true;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        g = new ArrayList[n + 1];
        visited = new boolean[n + 1];
        if (m != n - 1) {
            System.out.println("NO");
            System.out.println("hi 1");
            return;
        }

        for (int i = 1; i <= n; i++) {
            g[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            g[u].add(v);
            g[v].add(u);
        }
        dfs(1, -1);
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println(ans ? "NO" : "YES");
        br.close();
    }
}

/*
import java.io.*;
import java.util.*;

public class PT07Y {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        int m = stdin.nextInt();
        ArrayList<List<Integer>> adjList = new ArrayList<List<Integer>>();
        for (int i = 0; i < n; i++) {
            adjList.add(new LinkedList<Integer>());
        }
        int e = m;
        while (e > 0) {
            int e1 = stdin.nextInt();
            int e2 = stdin.nextInt();
            adjList.get(e1 - 1).add(e2 - 1);
            adjList.get(e2 - 1).add(e1 - 1);
            e--;
        }

        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        boolean[] visited = new boolean[n];
        int numNodesVisited = 0;
        while (!stack.isEmpty()) {
            int curr = stack.pop();
            if (!visited[curr]) {
                visited[curr] = true;
                numNodesVisited++;
            }
            
            for (int idx = 0; idx < adjList.get(curr).size(); idx++) {
                if (!visited[adjList.get(curr).get(idx)]) {
                    stack.add(adjList.get(curr).get(idx));
                }
            }
        }
        String ans = "NO";
        if ((numNodesVisited == n && stack.isEmpty()) && n == m + 1|| n == 1) {
            ans = "YES";
        } 
        System.out.println(ans);
        stdin.close();
    }
}

//Disjoint Set Solution 
import java.io.*;
import java.util.*;

public class PT07Y {
    public static int[] parent;
    public static int[] rank;

    public  static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    static boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa == pb) {
            return false;
        }
        if (rank[pa] < rank[pb]) {
            parent[pa] = pb;
        }
        else if (rank[pb] < rank[pa]) {
            parent[pb] = pa;
        }
        else {
            parent[pb] = pa;
            rank[pa]++;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        if (m != n - 1) {
            System.out.println("NO");
            return;
        }

        parent = new int[n + 1];
        rank = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        boolean ans = true;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            if (!union(u, v)) {
                ans = false;
            }
        }
        int root = find(1);
        for (int i = 2; i <= n; i++) {
            if (find(i) != root) {
                ans = false;
            }
        }
        System.out.println(ans ? "YES" : "NO");
        br.close();
    }
}
*/

