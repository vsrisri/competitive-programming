import java.util.*;
import java.io.*;

public class Pawpsicles {
    public static class State implements Comparable<State> {
        public int loc;
        public int type;
        public long dist;

        public State(int loc, int type, long dist) {
            this.loc = loc;
            this.type = type;
            this.dist = dist;
        }

        public int compareTo(State other) {
            return Long.compare(this.dist, other.dist);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
        int[] locType = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        st = new StringTokenizer(reader.readLine(), " ");
        for (int i = 1; i <= n; i++) {
            locType[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(reader.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.get(u).add(new int[]{v, w});
            graph.get(v).add(new int[]{u, w});
        }

        System.out.println(dijkstra(graph, locType, n));
        reader.close();
    }

    public static long dijkstra(ArrayList<ArrayList<int[]>> graph, int[] locType, int n) {
        PriorityQueue<State> pq = new PriorityQueue<>();
        long[][] dist = new long[n + 1][6];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], Long.MAX_VALUE);
        }

        pq.add(new State(1, 1, 0));
        dist[1][1] = 0;
        while (!pq.isEmpty()) {
            State current = pq.poll();
            int loc = current.loc;
            int type = current.type;
            long currDist = current.dist;
            if (type == 5) { 
                return currDist;
            }

            for (int[] edge : graph.get(loc)) {
                int nextLoc = edge[0];
                int weight = edge[1];
                if (dist[nextLoc][type] > currDist + weight) {
                    dist[nextLoc][type] = currDist + weight;
                    pq.add(new State(nextLoc, type, dist[nextLoc][type]));
                }
            }

            if (type < 5 && locType[loc] == type) {
                if (dist[loc][type + 1] > currDist) {
                    dist[loc][type + 1] = currDist;
                    pq.add(new State(loc, type + 1, dist[loc][type + 1]));
                }
            }
        }

        return -1;
    }
}

