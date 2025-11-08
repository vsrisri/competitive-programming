import java.io.*;
import java.util.*;

public class Deforestation {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            
            int[] arr = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            
            Arrays.sort(arr);
            List<Triple> events = new ArrayList<>();
            
            for (int i = 0; i < n; i++) {
                events.add(new Triple(arr[i], 0, 0));
            }
            
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int l = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());
                
                int existing = countInRange(arr, l, r);
                events.add(new Triple(l, -1, r, existing - t));
            }
            
            events.sort(Comparator.comparingInt(a -> a.l));
            
            PriorityQueue<Triple> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.r));
            int ans = 0;
            
            for (Triple event : events) {
                int l = event.l;
                int tp = event.tp;
                int r = event.r;
                int cut = event.cut;
                
                if (tp == -1) {
                    pq.offer(new Triple(ans + cut, r, 0));
                } else {
                    while (!pq.isEmpty() && pq.peek().r < l) {
                        pq.poll();
                    }
                    
                    if (pq.isEmpty() || pq.peek().r != ans) {
                        ans++;
                    }
                }
            }
            
            System.out.println(ans);
        }
    }

    static int countInRange(int[] arr, int l, int r) {
        return (int) Arrays.stream(arr).filter(x -> x >= l && x <= r).count();
    }

    static class Triple {
        int l, tp, r, cut;

        Triple(int l, int tp, int r) {
            this.l = l;
            this.tp = tp;
            this.r = r;
            this.cut = 0;
        }

        Triple(int l, int tp, int r, int cut) {
            this.l = l;
            this.tp = tp;
            this.r = r;
            this.cut = cut;
        }
    }
}

