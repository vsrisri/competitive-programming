import java.io.*;
import java.util.*;

public class TAXI {
    public static ArrayList<Integer>[] graph;
    public static int[] match;
    public static boolean[] vis;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        int k = Integer.parseInt(br.readLine());
        while (k-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int[][] pArr = new int[p][2];
            int[][] taxis = new int[t][2];
            for (int i = 0; i < p; i++) {
                st = new StringTokenizer(br.readLine());
                pArr[i][0] = Integer.parseInt(st.nextToken());
                pArr[i][1] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < t; i++) {
                st = new StringTokenizer(br.readLine());
                taxis[i][0] = Integer.parseInt(st.nextToken());
                taxis[i][1] = Integer.parseInt(st.nextToken());
            }

            graph = new ArrayList[t];
            for (int i = 0; i < t; i++) {
                graph[i] = new ArrayList<>();
                for (int j = 0; j < p; j++) {
                    int dist = Math.abs(taxis[i][0] - pArr[j][0]) + Math.abs(taxis[i][1] - pArr[j][1]);
                    int meters = dist * 200;
                    if ((long) meters <= (long) s * c) {
                        graph[i].add(j);
                    }
                }
            }

            match = new int[p];
            Arrays.fill(match, -1);
            int ans = 0;
            for (int i = 0; i < t; i++) {
                vis = new boolean[p];
                if (dfs(i)) {
                    ans++;
                }
            }

            out.append(ans).append('\n');
        }
        System.out.print(out);
        br.close();
    }

    public static boolean dfs(int u) {
        for (int v : graph[u]) {
            if (vis[v]) {
                continue;
            }

            vis[v] = true;
            if (match[v] == -1 || dfs(match[v])) {
                match[v] = u;
                return true;
            }
        }

        return false;
    }

}
