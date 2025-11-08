import java.util.*;
import java.io.*;

public class BerryPicking {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("berries.in"));
        PrintWriter pw = new PrintWriter(new File("berries.out"));
		int n = sc.nextInt();
		int k = sc.nextInt();
		int[] items = new int[n];
        for (int i = 0; i < n; i++) {
			items[i] = sc.nextInt();
        }
		int ans = 0;
        for (int i = 1; i<= 1000; i++)  {
            ans = Math.max(ans, BerryPicking.helper(items, i, k/2));
        }
	    // Finished for loop
        
        pw.println(ans);
        sc.close();
        pw.close();
    }

    public static int helper(int[] items, int limit, int baskets) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(10, Collections.reverseOrder());
        for (int item: items) {
            pq.offer(item);
        }
        for (int i = 0; i < baskets; i++) {
            if (pq.size() == 0) {
                return -1;
            }
            int next = pq.poll();
            if (next < limit) {
                return -1;
            }
            if (next > limit) {
                pq.offer(next - limit);
            }
        }
        int bessie = 0, count = 0;
        while (pq.size() > 0 && count < baskets) {
            int next = pq.poll();
            count++;
            if (next > limit) {
                bessie += limit;
                pq.offer(next - limit);
            } else {
                bessie += next;
            }
        }

        return bessie;
    }
}
