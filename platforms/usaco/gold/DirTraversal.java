import java.io.*;
import java.util.*;

public class DirTraversal {
    public Node[] nodes;
    public long[] subtreeCount;
    public long[] subtreeCost;
    public long[] upwardCost;
    public long minTotal = Long.MAX_VALUE;
    public static class Node {
        String name;
        List<Integer> children = new ArrayList<>();

        Node(String name) {
            this.name = name;
        }

        boolean isFile() {
            return children.isEmpty();
        }
    }

    public static void main(String[] args) throws IOException {
        new DirectoryTraversal().run();
    }

    public void run() throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("dirtraverse.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dirtraverse.out")));
        int n = Integer.parseInt(in.readLine());
        nodes = new Node[n + 1];
        subtreeCount = new long[n + 1];
        subtreeCost = new long[n + 1];
        upwardCost = new long[n + 1];

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            nodes[i] = new Node(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            while (k-- > 0) {
                int child = Integer.parseInt(st.nextToken());
                nodes[i].children.add(child);
            }
        }

        buildCost(1);
        propCost(1);
        out.println(minTotal);
        out.close();
    }

    public void buildCost(int curr) {
        for (int child : nodes[curr].children) {
            if (nodes[child].isFile()) {
                subtreeCost[curr] += nodes[child].name.length();
                subtreeCount[curr]++;
            } else {
                buildCost(child);
                subtreeCost[curr] += subtreeCost[child] + subtreeCount[child] * (nodes[child].name.length() + 1);
                subtreeCount[curr] += subtreeCount[child];
            }
        }
    }

    public void propCost(int curr) {
        minTotal = Math.min(minTotal, subtreeCost[curr] + upwardCost[curr]);
        for (int child : nodes[curr].children) {
            if (nodes[child].isFile()) {
                continue;
            }

            upwardCost[child] = upwardCost[curr] + subtreeCost[curr] - subtreeCost[child] - subtreeCount[child] * (nodes[child].name.length() + 1) + 3 * (subtreeCount[1] - subtreeCount[child]);

            propCost(child);
        }
    }
}
