import java.util.*;
import java.io.*;

public class PiggyBack {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(new File("piggyback.in"));
        PrintWriter pw = new PrintWriter(new File("piggyback.out"));
        int b = stdin.nextInt();
        int e = stdin.nextInt();
        int p = stdin.nextInt();
        int n = stdin.nextInt();
        int m = stdin.nextInt();
        ArrayList[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < m; i++) {
            int a = stdin.nextInt();
            int c = stdin.nextInt();
            graph[a - 1].add(c - 1);
            graph[c - 1].add(a - 1);
        }
        int[] bessieDist = bfs(0, n, m, graph);
        int[] elsieDist = bfs(1, n, m, graph);
        int[] bothDist = bfs(n - 1, n, m, graph);
        int ans = b * bessieDist[n - 1] + e * elsieDist[n - 1];
        for (int idx = 0; idx < n - 1; idx++) {
            if (bessieDist[idx] == -1 || elsieDist[idx] == -1 || bothDist[idx] == -1) {
                continue;
            }
            ans = Math.min(ans, b * bessieDist[idx] + e *elsieDist[idx] + p * bothDist[idx]);
        }
        pw.println(ans);
        stdin.close();
        pw.close();
    }

    public static int[] bfs(int num, int n, int m, ArrayList[] graph) {
        int[] dist = new int[n];
        Arrays.fill(dist, -1);
        dist[num] = 0;
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.offer(num);
        while (queue.size() > 0) {
            int top = queue.poll();
            for (Integer curr : (ArrayList<Integer>) graph[top]) {
                if (dist[curr] == -1) {
                    dist[curr] = dist[top] + 1;
                    queue.offer(curr);
                }
            }
        }
        return dist;

    }
}

