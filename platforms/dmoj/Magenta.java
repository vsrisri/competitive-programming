import java.io.*;
import java.util.*;

public class Magenta {
    public static List<int[]>[] graph;
    public static int[][] dist;
    public static int n;
    public static int len;
    public static int paula, marin;
    public static int player;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        paula = Integer.parseInt(st.nextToken());
        marin = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n + 1];
        dist = new int[n + 1][2];
        boolean[] bArr = new boolean[2];
        int idx = 0;
        while (idx <= n) {
            graph[idx] = new ArrayList<>();
            Arrays.fill(dist[idx], -1);
            idx++;
        }

        int edgeCount = 0;
        while (edgeCount < n - 1) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            String colorStr = st.nextToken();
            int color = mapColor(colorStr);
            graph[u].add(new int[]{v, color});
            graph[v].add(new int[]{u, color});
            boolean paulaConn = (u == paula || v == paula);
            boolean marinConn = (u == marin || v == marin);
            boolean notToMarin = !((u == paula && v == marin) || (v == paula && u == marin));
            if (paulaConn && color != 1 && notToMarin) {
                bArr[0] = true;
            }
            if (marinConn && color != 0) {
                bArr[1] = true;
            }

            edgeCount++;
        }

        boolean paulaBlock = !bArr[0];
        boolean marinBlock = !bArr[1];
        if (paulaBlock) {
            System.out.println("Marin");
            return;
        }
        if (marinBlock) {
            System.out.println("Paula");
            return;
        }

        dist[paula][0] = 0;
        dfs(paula, 0, -1);
        dist[marin][1] = 0;
        dfs(marin, 1, -1);
        player = (len % 2 == 0) ? 1 : 0;
        int playerStart = (player == 0) ? paula : marin;
        boolean isFound = helper(playerStart, -1);
        String ans = isFound ? "Magenta" : (player == 0 ? "Marin" : "Paula");
        System.out.println(ans);
        br.close();
    }

    public static int mapColor(String color) {
        if (color.equals("plava")) {
            return 0;
        } else if (color.equals("crvena")) {
            return 1;
        }
        return 2;
    }

    public static void dfs(int node, int playerRole, int parent) {
        int currDist = dist[node][playerRole];
        int other = playerRole ^ 1;
        int target = (playerRole == 0) ? marin : paula;
        for (int i = 0; i < graph[node].size(); i++) {
            int[] conn = graph[node].get(i);
            int neighbor = conn[0];
            int color = conn[1];
            if (neighbor != parent) {
                if (neighbor == target) {
                    len = currDist + 1;
                }

                boolean canTraverse = (color != other);
                if (canTraverse) {
                    dist[neighbor][playerRole] = currDist + 1;
                    dfs(neighbor, playerRole, node);
                }
            }
        }
    }
    
    public static boolean isValidMove(int next, int prev, int col) {
        if (next == prev) {
            return false;
        }

        int h = player ^ 1;
        if (col == h) {
            return false;
        }

        boolean reachable = (dist[next][h] != -1);
        if (reachable) {
            int hDist = dist[next][h];
            int vDist = dist[next][player];
            if (vDist >= hDist) {
                return false;
            }
        }

        return true;
    }

    public static boolean isSafe(int cur, int next) {
        int h = player ^ 1;
        boolean curSafe = (dist[cur][h] == -1);
        boolean nextSafe = (dist[next][h] == -1);
        return curSafe && nextSafe;
    }

    public static boolean helper(int cur, int prev) {
        for (int[] edge : graph[cur]) {
            int next = edge[0];
            int col = edge[1];

            if (!isValidMove(next, prev, col)) {
                continue;
            }

            if (isSafe(cur, next) || helper(next, cur)) {
                return true;
            }
        }

        return false;
    }
}
