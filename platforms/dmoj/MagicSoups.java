import java.util.*;
import java.io.*;

public class MagicSoups {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
        int x = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int maxMins = -1;
        int maxTimeToCool = -1;
        HashMap<Integer, ArrayList<Soup>> soupMap = new HashMap<Integer, ArrayList<Soup>>();
        ArrayList<Soup> inArr = new ArrayList<Soup>();

        for (int idx = 0; idx < n; idx++) {
            st = new StringTokenizer(reader.readLine(), " ");
            int temp = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            int timeTilCool = Math.max(0, temp - x);
            if (time - timeTilCool >= 0) {
                inArr.add(new Soup(temp, time));
                maxMins = Math.max(maxMins, time);
                maxTimeToCool = Math.max(maxTimeToCool, timeTilCool);
            }
        }

        for (Soup s : inArr) {
            int timeTilCool = Math.max(0, s.temp - x);
            if (soupMap.containsKey(timeTilCool)) {
                 soupMap.get(timeTilCool).add(s);
            } else {
                soupMap.put(timeTilCool, new ArrayList<Soup>());
                soupMap.get(timeTilCool).add(s);
            }
        }

        for (Map.Entry<Integer, ArrayList<Soup>> set : soupMap.entrySet()) {
            if (set.getValue().size() > 1) {
                Collections.sort(set.getValue());
            }
        }

        PriorityQueue<Soup> q = new PriorityQueue<Soup>();
        int ans = 0;
        for (int min = 0; min <= maxMins; min++) {
            if (soupMap.containsKey(min)) {
                for (Soup s : soupMap.get(min)) {
                    q.add(s);
                }
            }

            while (q.size() > 0 && q.peek().time - min < 0) {
                q.poll();
            }

            if (q.size() > 0) {
                if (q.peek().time - min >= 0) {
                    ans++;
                }
                q.poll();
            }
        }

        System.out.println(ans);
        reader.close();
    }

    public static class Soup implements Comparable<Soup> {
        int temp;
        int time;

        public Soup (int temp, int time) {
            this.temp = temp;
            this.time = time;
        }

        public int compareTo(Soup b) {
            return this.time - b.time;
        }
    }
}

