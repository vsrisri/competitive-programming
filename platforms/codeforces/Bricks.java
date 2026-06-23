import java.io.*;
import java.util.*;

public class Bricks {
    static int[] head;
    static int[] next;
    static int[] to;
    static int[] cap;
    static int edgeCount;
    static int[] level;
    static int[] it;
    static int N;

    public static void addEdge(int u, int v, int c) {
        to[edgeCount] = v;
        cap[edgeCount] = c;
        next[edgeCount] = head[u];
        head[u] = edgeCount;
        edgeCount++;
        to[edgeCount] = u;
        cap[edgeCount] = 0;
        next[edgeCount] = head[v];
        head[v] = edgeCount;
        edgeCount++;
    }

    public static boolean bfs(int s, int t) {
        level = new int[N];
        Arrays.fill(level, -1);
        level[s] = 0;
        int[] queue = new int[N];
        int qh = 0;
        int qt = 0;
        queue[qt] = s;
        qt++;
        while (qh < qt) {
            int u = queue[qh];
            qh++;
            for (int e = head[u]; e != -1; e = next[e]) {
                if (cap[e] > 0 && level[to[e]] < 0) {
                    level[to[e]] = level[u] + 1;
                    queue[qt] = to[e];
                    qt++;
                }
            }
        }
        return level[t] >= 0;
    }

    public static int dfs(int u, int t, int f) {
        if (u == t) {
            return f;
        }
        for (; it[u] != -1; it[u] = next[it[u]]) {
            int e = it[u];
            int v = to[e];
            if (cap[e] > 0 && level[v] == level[u] + 1) {
                int d = dfs(v, t, Math.min(f, cap[e]));
                if (d > 0) {
                    cap[e] -= d;
                    cap[e ^ 1] += d;
                    return d;
                }
            }
        }
        return 0;
    }

    public static int maxflow(int s, int t) {
        int flow = 0;
        while (bfs(s, t)) {
            it = head.clone();
            int f = dfs(s, t, Integer.MAX_VALUE);
            while (f > 0) {
                flow += f;
                f = dfs(s, t, Integer.MAX_VALUE);
            }
        }
        return flow;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        boolean[][] black = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                black[i][j] = line.charAt(j) == '#';
            }
        }
        int[][] hId = new int[n][m];
        int[][] vId = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(hId[i], -1);
            Arrays.fill(vId[i], -1);
        }
        int hCount = 0;
        int vCount = 0;
        int total = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (black[i][j]) {
                    total++;
                    if (j + 1 < m && black[i][j + 1]) {
                        hId[i][j] = hCount;
                        hCount++;
                    }
                    if (i + 1 < n && black[i + 1][j]) {
                        vId[i][j] = vCount;
                        vCount++;
                    }
                }
            }
        }
        int nodes = hCount + vCount + 2;
        int source = 0;
        int sink = nodes - 1;
        int approxEdges = hCount + vCount + 4 * total;
        N = nodes;
        head = new int[nodes];
        Arrays.fill(head, -1);
        int size = (approxEdges + 5) * 2;
        next = new int[size];
        to = new int[size];
        cap = new int[size];
        edgeCount = 0;
        for (int h = 0; h < hCount; h++) {
            addEdge(source, 1 + h, 1);
        }
        for (int v = 0; v < vCount; v++) {
            addEdge(1 + hCount + v, sink, 1);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (black[i][j]) {
                    int leftH = (j > 0) ? hId[i][j - 1] : -1;
                    int rightH = hId[i][j];
                    int upV = (i > 0) ? vId[i - 1][j] : -1;
                    int downV = vId[i][j];
                    if (leftH != -1 && upV != -1) {
                        addEdge(1 + leftH, 1 + hCount + upV, 1);
                    }
                    if (leftH != -1 && downV != -1) {
                        addEdge(1 + leftH, 1 + hCount + downV, 1);
                    }
                    if (rightH != -1 && upV != -1) {
                        addEdge(1 + rightH, 1 + hCount + upV, 1);
                    }
                    if (rightH != -1 && downV != -1) {
                        addEdge(1 + rightH, 1 + hCount + downV, 1);
                    }
                }
            }
        }
        int matching = maxflow(source, sink);
        int ans = total - hCount - vCount + matching;
        System.out.println(ans);
    }
}
