import java.util.*;
import java.io.*;

public class Workstations {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        ArrayList<Researcher> arr = new ArrayList<Researcher>();

        for (int idx = 0; idx < n; idx++) {
            StringTokenizer sta = new StringTokenizer(reader.readLine(), " ");
            int start = Integer.parseInt(sta.nextToken());
            int timeToEnd = Integer.parseInt(sta.nextToken());
            Researcher a1 = new Researcher(start, timeToEnd);
            arr.add(a1);
        }

        Collections.sort(arr);
        PriorityQueue<Station> q = new PriorityQueue<Station>();
        int numUnlock = 0;
        for (Researcher curr : arr) {
            while (!q.isEmpty() && q.peek().exp < curr.start) {
                q.poll();
            }

            if (q.isEmpty()) {
                numUnlock++;
                Station s = new Station(curr.start + curr.timeToEnd, curr.start + curr.timeToEnd + m);
                q.add(s);
            } else {
                if (q.peek().sessionEnd > curr.start) {
                    numUnlock++;
                    Station s = new Station(curr.start + curr.timeToEnd, curr.start + curr.timeToEnd + m);
                    q.add(s);
                } if (q.peek().exp >= curr.start && q.peek().sessionEnd <= curr.start) {
                    Station s = new Station(curr.start + curr.timeToEnd, curr.start + curr.timeToEnd + m);
                    q.poll();
                    q.add(s);

                }
            }
        }

        System.out.println(n - numUnlock);
        reader.close();
    }

    public static class Researcher implements Comparable<Researcher> {
        public int start;
        public int timeToEnd;

        public Researcher(int start, int timeToEnd) {
            this.start = start;
            this.timeToEnd = timeToEnd;
        }

        public int compareTo(Researcher other) {
            return this.start - other.start;
        }
    }

    public static class Station implements Comparable<Station> {
        public int sessionEnd;
        public int exp;

        public Station(int sessionEnd, int exp) {
            this.sessionEnd = sessionEnd;
            this.exp = exp;
        }

        public int compareTo(Station other) {
            return this.sessionEnd - other.sessionEnd;
        }
    }
}


