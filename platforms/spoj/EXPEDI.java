import java.util.*;
import java.io.*;

public class EXPEDI {
    public static void main(String[] args) throws Exception {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int tc = Integer.parseInt(reader.readLine());
            for (int t = 0; t < tc; t++) {
                int n = Integer.parseInt(reader.readLine());
                Stop[] stops = new Stop[n]; 

                for (int idx = 0; idx < n; idx++) {
                    StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
                    int d = Integer.parseInt(st.nextToken());
                    int f = Integer.parseInt(st.nextToken());
                    stops[idx] = new Stop(d, f);
                }

                StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
                int dist = Integer.parseInt(st.nextToken()); 
                int fuel = Integer.parseInt(st.nextToken());
                for (int idx = 0; idx < n; idx++) {
                    stops[idx].d = dist - stops[idx].d;
                }
                Arrays.sort(stops);

                PriorityQueue<Integer> heap = new PriorityQueue<Integer>(Collections.reverseOrder());
                int ans = 0;
                int prevDist = 0;
                int currIdx = 0;
                boolean isImposs = false;
                while (currIdx < n) {
                    //System.out.println("heap.peek(): " + heap.peek());
                    Stop curr = stops[currIdx];
                    if (fuel >= curr.d - prevDist) {
                        //System.out.println(curr.d);
                        //System.out.println(curr.f);
                        //System.out.println("fuel before: " + fuel);
                        fuel -= (curr.d - prevDist);
                        //System.out.println("fuel after: " + fuel);
                        //System.out.println("adding ans have enough");
                        prevDist = curr.d;
                        heap.offer(curr.f);
                    } else {
                        if (heap.isEmpty()) {
                            isImposs = true;
                            break;
                        }
                        //System.out.println("curr dist: " + curr.d + " curr fuel: " + curr.f);
                        //System.out.println("fuel before: " + fuel);
                        fuel += heap.poll();
                        //System.out.println("fuel after: " + fuel);
                        //System.out.println("adding ans else if");
                        ans++;
                        continue;
                    }
                    currIdx++;
                }

                if (isImposs) {
                    System.out.println(-1);
                    continue;
                }

                dist -= stops[n - 1].d;
                //System.out.println("fuel at end: " + fuel + " dist: " + dist);
                if (fuel >= dist) {
                    System.out.println(ans);
                    continue;
                } else {
                    //System.out.println("dist: " + dist + " fuel: " + fuel);
                    while (fuel < dist) {
                        if (!heap.isEmpty()) {
                            fuel += heap.poll();
                            //System.out.println("adding ans f < d");
                            ans++;
                            continue;
                        } else {
                            isImposs = true;
                            break;
                        }
                    }
                }

                if (isImposs) {
                    System.out.println(-1);
                } else {
                    System.out.println(ans);
                }
            }
            reader.close();
        } catch (Exception e) {
            return;
        }
    }

    public static class Stop implements Comparable<Stop> {
        public int d; public int f;

        public Stop(int d, int f) {
            this.d = d;
            this.f = f;
        }

        public int compareTo(Stop other) {
            return this.d - other.d;
        }
    }
}
