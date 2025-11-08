import java.io.*;
import java.util.*;

public class PotionFarming {
    public int[] leafCounts;
    public int[] potPerRoom;
    public List<List<Integer>> graph;

    public static void main(String[] args) throws IOException {
        new PotionFarming().helper();
    }

    public void helper() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int roomCount = Integer.parseInt(reader.readLine());
        int[] potionLocations = getPotIdxs(reader.readLine(), roomCount);
        buildGraph(reader, roomCount);

        leafCounts = new int[roomCount];
        int totalLeafNodes = findLeafCounts(0, -1);

        potPerRoom = distributePots(potionLocations, totalLeafNodes);
        System.out.println(findMaxPot(0, -1));
    }

    public int[] getPotIdxs(String line, int roomCount) {
        StringTokenizer tokenizer = new StringTokenizer(line);
        int[] locations = new int[roomCount];
        for (int i = 0; i < roomCount; i++) {
            locations[i] = Integer.parseInt(tokenizer.nextToken()) - 1;
        }
        return locations;
    }

    public void buildGraph(BufferedReader reader, int roomCount) throws IOException {
        graph = new ArrayList<>();
        for (int i = 0; i < roomCount; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < roomCount - 1; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int u = Integer.parseInt(tokenizer.nextToken()) - 1;
            int v = Integer.parseInt(tokenizer.nextToken()) - 1;
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
    }

    public int findLeafCounts(int current, int parent) {
        if (isLeaf(current, parent)) {
            leafCounts[current] = 1;
            return 1;
        }

        int totalLeaves = 0;
        for (int neighbor : graph.get(current)) {
            if (neighbor != parent) {
                totalLeaves += findLeafCounts(neighbor, current);
            }
        }
        leafCounts[current] = totalLeaves;
        return totalLeaves;
    }

    public boolean isLeaf(int node, int parent) {
        return graph.get(node).size() == 1 && graph.get(node).get(0) == parent;
    }

    public int[] distributePots(int[] potionLocations, int totalLeafNodes) {
        int[] assignments = new int[graph.size()];
        for (int i = 0; i < totalLeafNodes; i++) {
            assignments[potionLocations[i]]++;
        }
        return assignments;
    }

    public int findMaxPot(int current, int parent) {
        int totalPots = potPerRoom[current];
        for (int neighbor : graph.get(current)) {
            if (neighbor != parent) {
                totalPots += findMaxPot(neighbor, current);
            }
        }
        return Math.min(totalPots, leafCounts[current]);
    }
}

