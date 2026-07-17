import java.util.*;
import java.io.*;

public class PFDEP {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());
        int[] list = new int[n + 1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int t0 = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            list[t0] += k;
            for (int j = 0; j < k; j++) {
                int t = Integer.parseInt(st.nextToken());
                adj.get(t).add(t0);
            }
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 1; i <= n; i++) {
            if (list[i] == 0) {
                pq.add(i);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            int curr = pq.poll();
            sb.append(curr).append(' ');
            for (int temp : adj.get(curr)) {
                if (--list[temp] == 0) {
                    pq.add(temp);
                }
            }
        }
        System.out.println(sb.toString().trim());
        br.close();
    }
}
