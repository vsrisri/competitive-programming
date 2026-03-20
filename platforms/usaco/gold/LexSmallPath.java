import java.io.*;
import java.util.*;

public class LexSmallPath {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < t; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            if (n == 1) {
                out.append(0).append("\n");
                continue;
            }

            ArrayList<int[]>[] adj = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                adj[i] = new ArrayList<>();
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken()) - 1;
                int v = Integer.parseInt(st.nextToken()) - 1;
                char c = st.nextToken().charAt(0);
                adj[u].add(new int[]{v, c});
                adj[v].add(new int[]{u, c});
            }

            int[] ans = new int[n];
            Arrays.fill(ans, -1);
            boolean[] vis = new boolean[n];
            ArrayDeque<int[]> q = new ArrayDeque<>();
            q.add(new int[]{0, 1, 0});
            int curLen = 1;
            int minCur = Integer.MAX_VALUE;
            int minNxt = Integer.MAX_VALUE;
            while (!q.isEmpty()) {
                int[] top = q.peek();

                if (top[1] > curLen) {
                    minCur = minNxt;
                    minNxt = Integer.MAX_VALUE;
                    curLen++;
                }

                top = q.poll();
                if (vis[top[0]] || minCur < top[2]) {
                    continue;
                }

                vis[top[0]] = true;
                ans[top[0]] = curLen - 1;
                for (int[] nx : adj[top[0]]) {
                    minNxt = Math.min(minNxt, nx[1]);
                    if (vis[nx[0]]) {
                        continue;
                    }
                    q.add(new int[]{nx[0], top[1] + 1, nx[1]});
                }
            }

            for (int i = 0; i < n; i++) {
                out.append(ans[i]);
                if (i + 1 < n) {
                    out.append(" ");
                }
            }
            out.append("\n");
        }

        System.out.print(out);
        br.close();
    }
}
