import java.util.*;
import java.io.*;

public class CowSteeplechase {
    public static int[] matchH;
    public static boolean[] visited;
    public static List<Integer>[] adj;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        long[] hx1 = new long[n];
        long[] hx2 = new long[n];
        long[] hy = new long[n];
        long[] vx = new long[n];
        long[] vy1 = new long[n];
        long[] vy2 = new long[n];
        int hCount = 0, vCount = 0;
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long x1 = Long.parseLong(st.nextToken());
            long y1 = Long.parseLong(st.nextToken());
            long x2 = Long.parseLong(st.nextToken());
            long y2 = Long.parseLong(st.nextToken());
            if (y1 == y2) {
                hx1[hCount] = Math.min(x1, x2);
                hx2[hCount] = Math.max(x1, x2);
                hy[hCount] = y1;
                hCount++;
            } else {
                vx[vCount] = x1;
                vy1[vCount] = Math.min(y1, y2);
                vy2[vCount] = Math.max(y1, y2);
                vCount++;
            }
        }

        adj = new List[hCount];
        for (int i = 0; i < hCount; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < hCount; i++) {
            for (int j = 0; j < vCount; j++) {
                if (vx[j] >= hx1[i] && vx[j] <= hx2[i] && hy[i] >= vy1[j] && hy[i] <= vy2[j]) {
                    adj[i].add(j);
                }
            }
        }

        matchH = new int[vCount];
        Arrays.fill(matchH, -1);
        int matching = 0;
        for (int i = 0; i < hCount; i++) {
            visited = new boolean[vCount];
            if (dfs(i)) {
                matching++;
            }
        }

        System.out.println(n - matching);
        br.close();
    }

    public static boolean dfs(int u) {
        for (int v : adj[u]) {
            if (!visited[v]) {
                visited[v] = true;
                if (matchH[v] == -1 || dfs(matchH[v])) {
                    matchH[v] = u;
                    return true;
                }
            }
        }
        return false;
    }
}
