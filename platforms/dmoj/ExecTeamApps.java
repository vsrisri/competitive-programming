import java.util.*;
import java.io.*;

public class ExecTeamApps {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        ArrayList<Integer[]> arr = new ArrayList<Integer[]>();
        for (int idx = 0; idx < n; idx++) {
            st = new StringTokenizer(reader.readLine(), " ");
            int Ai = Integer.parseInt(st.nextToken());
            int Bi = Integer.parseInt(st.nextToken());
            Integer[] a = new Integer[2];
            a[0] = Ai; 
            a[1] = Bi;
            arr.add(a);
        }

        Collections.sort(arr, new Comparator<Integer[]>() { // Sort by bothersomeness
            public int compare(Integer[] a, Integer[] b) {
                return a[1] - b[1];
            }
        });

        ArrayList<App> arr1 = new ArrayList<App>();      // Make array of apps in order of bothersomeness
        for (Integer[] a : arr) {
            arr1.add(new App(a[0], a[1]));
        }

        PriorityQueue<App> q = new PriorityQueue<App>(); // initialize priority queue in ascending order of Ais
        int maxB = arr1.get(m - 1).Bi;                  // keep track of maximum Bi
        for (int idx = 0; idx < m; idx++) {
            q.add(arr1.get(idx));                       // Add first m apps (sorted by bothersomeness) to queue to have m apps always be in q
        }
        int maxQ = q.peek().Ai - maxB;

        for (int idx = m; idx < n; idx++) { // Iterate through remaining apps 
            System.out.println("peek : " + q.peek().Ai + " " + q.peek().Bi);
            if (arr1.get(idx).Ai > q.peek().Ai) {
                maxB = arr1.get(idx).Bi;
            }
            q.add((arr1.get(idx))); // Add current app
            q.poll(); 
            maxQ = Math.max(maxQ, q.peek().Ai - maxB);
        }

        //System.out.println(minAApp.Ai + " " + minAApp.Bi + " - " + q.peek().Ai + " " + q.peek().Bi);
        System.out.println(Math.max(q.peek().Ai - maxB, maxQ));
        reader.close();
    }

    public static class App implements Comparable<App> {
        int Ai;
        int Bi;

        public App(int Ai, int Bi) {
            this.Ai = Ai;
            this.Bi = Bi;
        }

        public int compareTo(App other) {
            return this.Ai - other.Ai;
        }
    }
}
