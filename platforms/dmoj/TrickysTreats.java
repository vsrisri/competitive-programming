import java.util.*;
import java.io.*;

public class TrickysTreats {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        int ans = 0;
        House[] arr = new House[n];

        for (int idx = 0; idx < n; idx++) {
            st = new StringTokenizer(reader.readLine(), " ");
            long p = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            House curr = new House(p, c);
            arr[idx] = curr;
        }

        Arrays.sort(arr);
        PriorityQueue<Integer> treats = new PriorityQueue<>();
        int currAns = 0;
        for (int idx = 0; idx < n; idx++) {
            int maxHouses = (int) ((m - 2 * arr[idx].p) / t);
            if (maxHouses <= 0) {
                break;
            }
            treats.add(arr[idx].c);
            currAns += arr[idx].c;
            while (treats.size() > maxHouses) {
                currAns -= treats.poll();
            }
            ans = Math.max(ans, currAns);
        }

        System.out.println(ans);
        reader.close();
    }

    static class House implements Comparable<House> {
        long p;
        int c;

        public House(long p, int c) {
            this.p = p;
            this.c = c;
        }

        public int compareTo(House other) {
            return (int) Long.compare(this.p, other.p);
        }
    }
}
