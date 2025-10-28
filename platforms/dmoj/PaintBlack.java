import java.util.*;
import java.io.*;

public class PaintBlack {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
        StringBuilder out = new StringBuilder();
        int n = Integer.parseInt(st.nextToken());
        Integer[] colors = new Integer[n + 1];
        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        for (int idx = 1; idx <= n; idx++)  {
            colors[idx] = Integer.parseInt(reader.readLine());
            graph[idx] = new ArrayList<Integer>();
        }

        for (int idx = 0; idx < n - 1; idx++) {
            st = new StringTokenizer(reader.readLine(), " ");
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            graph[i].add(j);
            graph[j].add(i);
        }

        dfs(1, -1, colors, graph, out);
        if (colors[1] == -1) {
            out.append(graph[1].get(0));
            out.append(" 1 ");
            out.append(graph[1].get(0));
        }

        if (out.charAt(out.length() - 1) == ' ') {
            out.deleteCharAt(out.length() - 1);
        }
        System.out.println(out.toString());

        reader.close();
    }

    public static void dfs(int node, int par, Integer[] colors, ArrayList<Integer>[] graph, StringBuilder out) {
        out.append(node);
        out.append(" ");
        for (int nb : graph[node]) {
            if (nb != par) {
                colors[nb] *= -1;
                dfs(nb, node, colors, graph, out);
            }
        }

        for (int nb : graph[node]) {
            if (nb != par && colors[nb] == -1) {
                out.append(nb);
                out.append(" ");
                out.append(node);
                out.append(" ");
                colors[nb] *= -1;
                colors[node] *= -1;
            }
        }

        if (par != -1) {
            out.append(par);
            out.append(" ");
            colors[par] *= -1;
        }
    }
}
