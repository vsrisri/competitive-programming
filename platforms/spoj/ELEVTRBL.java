import java.util.*;
import java.io.*;

public class ELEVTRBL {
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

    public static int bfs(Node[] graph, int s, int g, int f) {
        if (s == g) {
            return 0;
        }
        
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(s);
        HashSet<Integer> allNumsSet = new HashSet<Integer>();

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            if (allNumsSet.contains(curr) ) {
                continue;
            }

            for (Node n : graph[curr - 1].neighbors) {
                if (!allNumsSet.contains(n)) {
                    queue.add(n.value);
                    n.count = graph[curr - 1].count + 1;
                    if (n.value == g) {
                        return n.count;
                    }
                }
            }
            allNumsSet.add(graph[curr - 1].value);
        }
        return -1;

    }

    public static Node[] buildGraph(int f, int u, int d) {
        Node[] graph = new Node[f + 1];
        for (int idx = 1; idx <= f; idx++) {
            graph[idx - 1] = new Node(idx, 0);
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
        public int count;
        public ArrayList<Node> neighbors;
        Node(int value, int count) {
            this.value = value;
            this.count = count;
            this.neighbors = new ArrayList<Node>();
        }

    }
}
