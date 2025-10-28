import java.io.*;
import java.util.*;

public class PT07Z {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(st.nextToken());
        List<List<Integer>> adjList = new ArrayList<>();
        if (N == 1) {
            System.out.println(0);
            return;
        }

        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(reader.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        int[] ans1 = bfs(1, N, adjList);
        int farNode = ans1[0];
        int[] ans2 = bfs(farNode, N, adjList);
        System.out.println(ans2[1]);
        reader.close();
    }

    public static int[] bfs(int start, int N, List<List<Integer>> adjList) {
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> queue = new LinkedList<>();
        int[] dist = new int[N + 1];
        queue.add(start);
        visited[start] = true;
        int farNode = start;
        int maxDist = 0;
        
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int neighb : adjList.get(node)) {
                if (!visited[neighb]) {
                    visited[neighb] = true;
                    dist[neighb] = dist[node] + 1;
                    queue.add(neighb);
                    if (dist[neighb] > maxDist) {
                        maxDist = dist[neighb];
                        farNode = neighb;
                    }
                }
            }
        }
        return new int[] {farNode, maxDist};
    }
}

