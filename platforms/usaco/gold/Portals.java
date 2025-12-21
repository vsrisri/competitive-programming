import java.io.*;
import java.util.*;

public class Portals {
    public static int[] parent;
    public static int find(int u) {
        if (parent[u] != u) {
            parent[u] = find(parent[u]);  
        }
        return parent[u];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());  
        int totalCost = 0;
        parent = new int[2 * n + 1];  
        for (int i = 1; i <= 2 * n; i++) {
            parent[i] = i;
        }

        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer tokenizer = new StringTokenizer(in.readLine());
            int cost = Integer.parseInt(tokenizer.nextToken());
            int[] portals = new int[4];
            for (int j = 0; j < 4; j++) {
                portals[j] = Integer.parseInt(tokenizer.nextToken());
            }
            edges.add(new Edge(portals[0], portals[1], 0));  // No cost
            edges.add(new Edge(portals[2], portals[3], 0));  // No cost 
            edges.add(new Edge(portals[3], portals[0], cost));  // Cost 
        }

        edges.sort(Comparator.comparingInt(edge -> edge.cost));
        for (Edge edge : edges) {
            int rootA = find(edge.a);
            int rootB = find(edge.b);
            if (rootA != rootB) {
                totalCost += edge.cost;
                parent[rootA] = rootB;  // Union two sets
            }
        }

        System.out.println(totalCost);
    }

    public static class Edge {
        int a, b, cost;

        Edge(int a, int b, int cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }
    }
}

