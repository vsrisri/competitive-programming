import java.io.*;
import java.util.*;

public class Delegation {
    public static ArrayList<ArrayList<Integer>> graph;
    public static int findPathLength(int node, int parent, int k) {
        Map<Integer, Integer> pathLengths = new HashMap<>();
        for (int neighbor : graph.get(node)) {
            if (neighbor == parent) {
                continue;
            }
            int path = findPathLength(neighbor, node, k);
            if (path < 0) {
                return -1;
            }
            path = (path + 1) % k;
            if (path == 0) {
                continue;
            }
            if (pathLengths.containsKey(k - path)) {
                pathLengths.put(k - path, pathLengths.get(k - path) - 1);
            } else {
                pathLengths.put(path, pathLengths.getOrDefault(path, 0) + 1);
            }
        }
        
        int availPath = 0;
        for (Map.Entry<Integer, Integer> entry : pathLengths.entrySet()) {
            if (entry.getKey() == k || entry.getValue() == 0) {
                continue;
            }
            if (availPath == 0 && entry.getValue() == 1) {
                availPath = entry.getKey();
            } else {
                return -1;
            }
        }
        
        return availPath;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int nodeCount = Integer.parseInt(tokenizer.nextToken());
        graph = new ArrayList<>();
        for (int i = 0; i < nodeCount; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < nodeCount - 1; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int u = Integer.parseInt(tokenizer.nextToken()) - 1;
            int v = Integer.parseInt(tokenizer.nextToken()) - 1;
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        for (int i = 0; i < nodeCount - 1; i++) {
            if ((nodeCount - 1) % (i + 1) != 0) {
                writer.print(0);
                continue;
            }
            writer.print(findPathLength(0, -1, i + 1) % (i + 1) == 0 ? 1 : 0);
        }
        
        writer.flush();
    }
}

