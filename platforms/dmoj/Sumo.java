iimport java.io.*;
import java.util.*;

public class Main {
    public static int numFighters;
    public static int numFights;
    public static List<Edge>[] graph;
    public static Pair[] fightList;
    public static int[] color;
    public static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        numFighters = Integer.parseInt(tokenizer.nextToken());
        tokenizer = new StringTokenizer(reader.readLine());
        numFights = Integer.parseInt(tokenizer.nextToken());
        fightList = new Pair[numFights];
        graph = new ArrayList[numFighters + 1];
        for (int i = 1; i <= numFighters; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < numFights; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            fightList[i] = new Pair(a, b);
            graph[a].add(new Edge(b, i));
            graph[b].add(new Edge(a, i));
        }

        int low = 0;
        int high = numFights - 1;
        int ans = numFights; 
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (hasConflict(mid)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        System.out.println(ans + 1);
    }

    public static boolean hasConflict(int lastFightIdx) {
        visited = new boolean[numFighters + 1];
        color = new int[numFighters + 1];

        for (int i = 1; i <= numFighters; i++) {
            if (!visited[i]) {
                if (dfs(i, 0, lastFightIdx)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean dfs(int node, int team, int limit) {
        visited[node] = true;
        color[node] = team;
        for (Edge edge : graph[node]) {
            if (edge.fightIndex <= limit) {
                if (!visited[edge.neighbor]) {
                    if (dfs(edge.neighbor, (team ^ 1), limit)) {
                        return true;
                    }
                } else if (color[edge.neighbor] == team) {
                    return true;
                }
            }
        }
        return false;
    }

    public static class Pair {
        int a;
        int b;

        Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    public static class Edge {
        int neighbor;
        int fightIndex;

        Edge(int neighbor, int fightIndex) {
            this.neighbor = neighbor;
            this.fightIndex = fightIndex;
        }
    }
}
