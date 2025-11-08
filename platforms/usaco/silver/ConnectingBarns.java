import java.io.*;
import java.util.*;

public class ConnectingBarns {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int tc = Integer.parseInt(tokenizer.nextToken());
        for (int t = 0; t < tc; t++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int barns = Integer.parseInt(tokenizer.nextToken());
            int paths = Integer.parseInt(tokenizer.nextToken());
            List<List<Integer>> graph = new ArrayList<>(barns);

            for (int i = 0; i < barns; i++) {
                graph.add(new ArrayList<>());
            }
            
            for (int i = 0; i < paths; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                int u = Integer.parseInt(tokenizer.nextToken());
                int v = Integer.parseInt(tokenizer.nextToken());
                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            int[] component = new int[barns];
            Arrays.setAll(component, i -> i);
            
            for (int i = 0; i < barns; i++) {
                if (component[i] == i) {
                    dfs(graph, component, i, i);
                }
            }

            if (component[0] == component[barns - 1]) {
                System.out.println(0);
                continue;
            }

            List<List<Integer>> componentToBarns = new ArrayList<>(barns);
            for (int i = 0; i < barns; i++) componentToBarns.add(new ArrayList<>());
            for (int i = 0; i < barns; i++) {
                componentToBarns.get(component[i]).add(i);
            }

            int st = 0;
            int end = 0;
            long ans = Long.MAX_VALUE;
            long[] startC = new long[barns];
            long[] endC = new long[barns];
            Arrays.fill(startC, Long.MAX_VALUE);
            Arrays.fill(endC, Long.MAX_VALUE);

            for (int i = 0; i < barns; i++) {
                while (st < componentToBarns.get(component[0]).size()) {
                    startC[component[i]] = Math.min(startC[component[i]], (long) Math.abs(i - componentToBarns.get(component[0]).get(st)));
                    if (componentToBarns.get(component[0]).get(st) < i) {
                        st++;
                    } else {
                        break;
                    }
                }
                if (st > 0) st--;
                while (end < componentToBarns.get(component[barns - 1]).size()) {
                    endC[component[i]] = Math.min(endC[component[i]], (long) Math.abs(i - componentToBarns.get(component[barns - 1]).get(end)));
                    if (componentToBarns.get(component[barns - 1]).get(end) < i) {
                        end++;
                    } else {
                        break;
                    }
                }
                if (end > 0) {
                    end--;
                }
            }

            for (int i = 0; i < barns; i++) {
                ans = Math.min(ans, startC[i] * startC[i] + endC[i] * endC[i]);
            }

            System.out.println(ans);
        }
    }

    public static void dfs(List<List<Integer>> graph, int[] component, int node, int componentId) {
        for (int neighbor : graph.get(node)) {
            if (component[neighbor] != componentId) {
                component[neighbor] = componentId;
                dfs(graph, component, neighbor, componentId);
            }
        }
    }
}

