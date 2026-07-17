import java.io.*;
import java.util.*;

public class POTHOLE {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        in.nextToken();
        int t = (int) in.nval;
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            in.nextToken();
            int n = (int) in.nval;
            int[][] cap = new int[n + 1][n + 1];
            for (int i = 1; i < n; i++) {
                in.nextToken();
                int m = (int) in.nval;
                for (int k = 0; k < m; k++) {
                    in.nextToken();
                    int j = (int) in.nval;
                    int c = (i == 1 || j == n) ? 1 : 1000000;
                    cap[i][j] += c;
                }
            }
            int flow = 0;
            while (true) {
                int[] prev = new int[n + 1];
                Arrays.fill(prev, -1);
                prev[1] = 1;
                ArrayDeque<Integer> q = new ArrayDeque<>();
                q.add(1);
                while (!q.isEmpty() && prev[n] == -1) {
                    int u = q.poll();
                    for (int v = 1; v <= n; v++) {
                        if (prev[v] == -1 && cap[u][v] > 0) {
                            prev[v] = u;
                            q.add(v);
                        }
                    }
                }
                if (prev[n] == -1) {
                    break;
                }
                int temp = Integer.MAX_VALUE;
                for (int v = n; v != 1; v = prev[v]) {
                    temp = Math.min(temp, cap[prev[v]][v]);
                }
                for (int v = n; v != 1; v = prev[v]) {
                    cap[prev[v]][v] -= temp;
                    cap[v][prev[v]] += temp;
                }
                flow += temp;
            }
            sb.append(flow).append('\n');
        }
        System.out.print(sb);
    }
}
