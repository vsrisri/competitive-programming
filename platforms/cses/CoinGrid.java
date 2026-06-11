import java.util.*;
import java.io.*;

public class CoinGrid {
    public static int n;
    public static char[][] grid;
    public static int[] matchL, matchR;
    public static boolean[] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine().trim());
        grid = new char[n][n];
        for (int i = 0; i < n; i++) {
            grid[i] = br.readLine().toCharArray();
        }

        matchL = new int[n];
        matchR = new int[n];
        Arrays.fill(matchL, -1);
        Arrays.fill(matchR, -1);

        for (int u = 0; u < n; u++) {
            visited = new boolean[n];
            dfs(u);
        }

        boolean[] canReachL = new boolean[n];
        boolean[] canReachR = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        for (int u = 0; u < n; u++) {
            if (matchL[u] == -1) {
                canReachL[u] = true;
                queue.add(u);
            }
        }

        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v = 0; v < n; v++) {
                if (grid[u][v] == 'o' && !canReachR[v]) {
                    canReachR[v] = true;
                    if (matchR[v] != -1 && !canReachL[matchR[v]]) {
                        canReachL[matchR[v]] = true;
                        queue.add(matchR[v]);
                    }
                }
            }
        }

        List<int[]> moves = new ArrayList<>();
        for (int u = 0; u < n; u++) {
            if (!canReachL[u]) {
                moves.add(new int[]{1, u + 1});
            }
        }
        for (int v = 0; v < n; v++) {
            if (canReachR[v]) {
                moves.add(new int[]{2, v + 1});
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(moves.size()).append('\n');
        for (int[] move : moves) {
            sb.append(move[0]).append(' ').append(move[1]).append('\n');
        }
        System.out.print(sb);
        br.close();
    }

    public static boolean dfs(int u) {
        for (int v = 0; v < n; v++) {
            if (grid[u][v] == 'o' && !visited[v]) {
                visited[v] = true;
                if (matchR[v] == -1 || dfs(matchR[v])) {
                    matchL[u] = v;
                    matchR[v] = u;
                    return true;
                }
            }
        }
        return false;
    }

}
