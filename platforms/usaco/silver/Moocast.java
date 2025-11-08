import java.io.*;
import java.util.*;

public class Moocast {
    public static boolean[][] isConnected;
    public static boolean[] visited;

    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(new File("moocast.in"));
        PrintWriter pw = new PrintWriter(new File("moocast.out"));
        isConnected = new boolean[200][200];
        int n = stdin.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        int[] p = new int[n];
        visited = new boolean[n];
        for (int idx = 0; idx < n; idx++) {
            x[idx] = stdin.nextInt();
            y[idx] = stdin.nextInt();
            p[idx] = stdin.nextInt();
        }

        for (int i = 0; i < n; i++) {
		    for (int j = 0; j < n; j++) {
                int dist = (x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j]) * (y[i] - y[j]);
                int area = p[i] * p[i];
                if (dist <= area) {
                    isConnected[i][j] = true;
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            visited[n - 1] = false;
            int view = dfs(i, n) + 1;
            ans = Math.max(ans, view);
        }
        pw.println(ans);
        pw.close();
        stdin.close();


    }

    public static int dfs(int v, int n) {
        visited[v] = true;
        int numCows = 0;
        for (int i = 0; i < n; i++) {
            if (visited[i] || !isConnected[v][i]) {
                continue;
            }
            visited[i] = true;
            numCows+= dfs(i, n) + 1;
        }
        return numCows;
    }
}
