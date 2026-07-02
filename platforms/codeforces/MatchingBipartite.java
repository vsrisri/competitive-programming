// YoSupo Problem
import java.io.*;
import java.util.*;

public class MatchingBipartite {
    static int[] head, next, to;
    static int[] matchL, matchR;
    static int[] dist;
    static int L, R, M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        head = new int[L];
        for (int i = 0; i < L; i++) {
            head[i] = -1;
        }
        next = new int[M];
        to = new int[M];
        int edgeCount = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            to[edgeCount] = b;
            next[edgeCount] = head[a];
            head[a] = edgeCount;
            edgeCount++;
        }
        matchL = new int[L];
        matchR = new int[R];
        for (int i = 0; i < L; i++) {
            matchL[i] = -1;
        }
        for (int i = 0; i < R; i++) {
            matchR[i] = -1;
        }
        dist = new int[L];
        int ans = 0;
        while (bfs()) {
            for (int u = 0; u < L; u++) {
                if (matchL[u] == -1 && dfs(u)) {
                    ans++;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(ans).append('\n');
        for (int u = 0; u < L; u++) {
            if (matchL[u] != -1) {
                sb.append(u).append(' ').append(matchL[u]).append('\n');
            }
        }
        System.out.print(sb);
        br.close();
    }

    public static boolean bfs() {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int u = 0; u < L; u++) {
            if (matchL[u] == -1) {
                dist[u] = 0;
                queue.add(u);
            } else {
                dist[u] = -1;
            }
        }
        boolean found = false;
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int e = head[u]; e != -1; e = next[e]) {
                int v = to[e];
                int w = matchR[v];
                if (w == -1) {
                    found = true;
                } else if (dist[w] == -1) {
                    dist[w] = dist[u] + 1;
                    queue.add(w);
                }
            }
        }
        return found;
    }

    public static boolean dfs(int u) {
        for (int e = head[u]; e != -1; e = next[e]) {
            int v = to[e];
            int w = matchR[v];
            if (w == -1 || (dist[w] == dist[u] + 1 && dfs(w))) {
                matchL[u] = v;
                matchR[v] = u;
                return true;
            }
        }
        dist[u] = -1;
        return false;
    }
}
