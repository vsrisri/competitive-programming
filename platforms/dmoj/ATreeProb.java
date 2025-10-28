import java.util.*;
import java.io.*;

public class ATreeProb  {
    static ArrayList<List<Edge>> graph = new ArrayList<>();
    static boolean[] visited;
    static boolean isTreeBad;

    public static class Edge {
        int to, color;
        Edge(int to, int color) {
            this.to = to;
            this.color = color;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        visited = new boolean[n + 1];
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Edge(b, c));
            graph.get(b).add(new Edge(a, c));
        }

        int[] isBadArr = new int[n + 1]; 
        for (int i = 1; i <= n; i++) {
            Map<Integer, List<Integer>> colorMap = new HashMap<>();
            for (Edge edge : graph.get(i)) {
                if (!colorMap.containsKey(edge.color)) {
                    colorMap.put(edge.color, new ArrayList<>());
                }
                colorMap.get(edge.color).add(edge.to);
            }

            for (List<Integer> sameColorNodes : colorMap.values()) {
                if (sameColorNodes.size() > 1) {
                    for (Integer node : sameColorNodes) {
                        if (isBadArr[node] == 0) {
                            isBadArr[node] = i; 
                        } else if (isBadArr[node] != i) {
                            isTreeBad = true;
                        }
                    }
                }
            }
            if (isTreeBad) {
                break;
            }
        }

        for (int i = 1; i <= n && !isTreeBad; i++) {
            if (isBadArr[i] != 0 && !visited[i]) {
                dfs(i, isBadArr[i], isBadArr);
            }
        }

        if (isTreeBad) {
            System.out.println(0);
        } else {
            List<Integer> goodNodes = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                if (isBadArr[i] == 0) goodNodes.add(i);
            }
            System.out.println(goodNodes.size());
            for (int node : goodNodes) {
                System.out.println(node);
            }
        }
    }

    public static void dfs(int node, int parent, int[] isBadArr) {
        visited[node] = true;
        if (isBadArr[node] == 0) {
            isBadArr[node] = parent;
        } else if (isBadArr[node] != parent) {
            isTreeBad = true;
            return;
        }

        for (Edge edge : graph.get(node)) {
            if (edge.to != parent && !visited[edge.to]) {
                dfs(edge.to, node, isBadArr);
                if (isTreeBad) {
                    return;
                }
            }
        }
    }
}

