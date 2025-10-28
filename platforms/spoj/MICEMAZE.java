import java.util.*;
import java.io.*;

public class MICEMAZE {
    static class Edge implements Comparable<Edge> {
        public int weight;
        public int dest;
        public Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }

        public int compareTo(Edge other) {
            return weight - other.weight;
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        int e = stdin.nextInt() - 1;
        int t = stdin.nextInt();
        int m = stdin.nextInt();
        ArrayList<ArrayList<Edge>> inputArr = new ArrayList<ArrayList<Edge>>();
        for (int i = 0; i < n; i++) {
            inputArr.add(new ArrayList<Edge>());
        }

        for (int i = 0; i < m; i++) {
            int a = stdin.nextInt() - 1;
            int b = stdin.nextInt() - 1;
            int w = stdin.nextInt();
            inputArr.get(b).add(new Edge(a, w));
        }
        int[] arr = new int[n];
        System.out.println(dijkstra(arr, e, t, inputArr));
        stdin.close();
    }

    public static int dijkstra(int[] arr, int source, int time, ArrayList<ArrayList<Edge>> inputArr) {
        Arrays.fill(arr, Integer.MAX_VALUE);
        boolean[] visited = new boolean[arr.length];
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
        pq.offer(new Edge(source, 0));
        int count = 0;

        while (pq.size() > 0) {
            Edge top = pq.poll();
            if (visited[top.dest]) {
                continue;
            }

            visited[top.dest] = true;
            arr[top.dest] = top.weight;
            if (arr[top.dest] > time) {
                break;
            }
            count++;

            for (Edge e : inputArr.get(top.dest)) {
                if (!visited[e.dest]) {
                    pq.offer(new Edge(e.dest, e.weight + top.weight));
                }
            }
        }
        return count; 
    }
}
