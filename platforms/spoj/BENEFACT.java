import java.io.*;
import java.util.*;

public class BENEFACT {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < t; tc++) {
            int n = Integer.parseInt(br.readLine().trim());
            ArrayList<Edge>[] g = new ArrayList[n+1];
            for (int i = 1; i <= n; i++) {
                g[i] = new ArrayList<>();
            }

            for (int i = 0; i < n-1; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                g[a].add(new Edge(b,w));
                g[b].add(new Edge(a,w));
            }

            int start = (int) bfs(1, g, n)[0];
            long[] r = bfs(start, g, n);
            sb.append(r[1]).append('\n');
        }
        System.out.print(sb.toString());
        br.close();
    }

    public static long[] bfs(int s, ArrayList<Edge>[] g, int n) {
        long[] dist = new long[n+1];
        int[] q = new int[n];
        int h = 0, t = 0;
        Arrays.fill(dist,-1);
        dist[s] = 0;
        q[t++] = s;
        int ans = s;
        while (h < t) {
            int u = q[h++];
            if (dist[u] > dist[ans]) {
                ans = u;
            }
            for (Edge e : g[u]) {
                if (dist[e.v] == -1) {
                    dist[e.v] = dist[u] + e.w;
                    q[t++] = e.v;
                }
            }
        }
        return new long[]{ans, dist[ans]};
    }

    public static class Edge { 
        int v, w; 
        Edge(int v,int w){
            this.v = v;
            this.w = w`;
        } 
    }
}

