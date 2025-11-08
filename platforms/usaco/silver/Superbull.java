import java.util.*;
import java.io.*;

public class Superbull {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("superbull.in"));
        int n = Integer.parseInt(reader.readLine().trim());
        
        int[] inArr = new int[n];
        for (int i = 0; i < n; i++) {
            inArr[i] = Integer.parseInt(reader.readLine().trim());
        }
        reader.close();

        PriorityQueue<Edge> maxHeap = new PriorityQueue<>();
        for (int i = 1; i < n; i++) {
            maxHeap.offer(new Edge(0, i, inArr[0] ^ inArr[i]));
        }
        
        long totalScore = 0;
        boolean[] includedInMST = new boolean[n];
        includedInMST[0] = true;
        int edgesUsed = 0;

        while (edgesUsed < n - 1) {
            Edge currentEdge = maxHeap.poll();
            if (includedInMST[currentEdge.vertex1] && includedInMST[currentEdge.vertex2]) {
                continue;
            }

            int newVertex = includedInMST[currentEdge.vertex1] ? currentEdge.vertex2 : currentEdge.vertex1;
            totalScore += currentEdge.weight;
            includedInMST[newVertex] = true;
            edgesUsed++;

            for (int i = 0; i < n; i++) {
                if (!includedInMST[i]) {
                    maxHeap.offer(new Edge(newVertex, i, inArr[newVertex] ^ inArr[i]));
                }
            }
        }

        PrintWriter writer = new PrintWriter(new FileWriter("superbull.out"));
        writer.println(totalScore);
        writer.close();
    }
}

class Edge implements Comparable<Edge> {
    int vertex1, vertex2, weight;

    public Edge(int vertex1, int vertex2, int weight) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return Integer.compare(other.weight, this.weight);
    }
}

