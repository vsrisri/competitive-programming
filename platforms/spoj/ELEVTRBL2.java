import java.util.*;
import java.io.*;

public class ELEVTRBL2 {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
        int f = stdin.nextInt();
        int s = stdin.nextInt();
        int g = stdin.nextInt();
        int u = stdin.nextInt();
        int d = stdin.nextInt();
        Node[] graph = buildGraph(f, u, d);
        int ans = bfs(graph, s, g, f);
        if (ans == -1) {
            System.out.println("use the stairs");
        } else {
            System.out.println(ans);
        }

    }

    public static void printDistArr(int[] dist) {
        for (int idx = 0; idx < dist.length; idx++) {
            System.out.println("floor: " + (idx + 1) + " dist: " + dist[idx]);
        }
    }

    public static int bfs(Node[] graph, int s, int g, int f) {
        if (s == g) {
            return 0;
        }

        LinkedList<Node> queue = new LinkedList<Node>();
        queue.add(graph[s - 1]);
        int[] dist = new int[f];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s - 1] = 0;

        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            for (Node n : graph[curr.value - 1].neighbors) {
                if (dist[n.value - 1] == Integer.MAX_VALUE) {
                    queue.add(n);
                    dist[n.value - 1] = dist[curr.value - 1] + 1;
                    if (n.value == g) {
                        return dist[n.value - 1];
                    }
                }
            }
        }
        return -1;

    }

    public static Node[] buildGraph(int f, int u, int d) {
        Node[] graph = new Node[f];
        for (int idx = 1; idx <= f; idx++) {
            graph[idx - 1] = new Node(idx);
        }

        for (int idx = 1; idx <= f; idx++) {
            if (idx - d - 1 >= 0) {
                graph[idx - 1].neighbors.add(graph[idx - d - 1]);
            }
            if (idx + u - 1 < f) {
                graph[idx - 1].neighbors.add(graph[idx + u - 1]);

            }
        }
        return graph;
    }

    static class Node {
        public int value;
        public ArrayList<Node> neighbors;
        Node(int value) {
            this.value = value;
            this.neighbors = new ArrayList<Node>();
        }

    }
}
