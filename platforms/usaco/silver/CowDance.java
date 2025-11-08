// USACO 2017 January Contest, Silver
// Problem 1. Cow Dance Show
import java.io.*;
import java.util.*;

public class CowDance {
    public static void main(String[] args) throws Exception{
        Scanner stdin = new Scanner(new File("cowdance.in"));
        PrintWriter pw = new PrintWriter(new File("cowdance.out"));
        int n = stdin.nextInt();
        int maxT = stdin.nextInt();
        int[] cows = new int[n];

        for (int i = 0; i < n; i++) {
			cows[i] = stdin.nextInt();
        }

		int low = 1 , high = n;
		while (low < high) {
			int mid = (low + high) / 2;

			if (isPoss(mid, cows, n, maxT)) {
				high = mid;
			} else {
				low = mid + 1;
            }
		}

		pw.println(low);
		pw.close();
		stdin.close();
	}

	public static boolean isPoss(int k, int[] cows, int n, int maxT) {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		for (int i = 0; i < k; i++) {
			pq.offer(cows[i]);
        }

		for (int i = k; i < n; i++) {
			int curT = pq.poll();
			pq.offer(cows[i] + curT);
		}

		int ans = 0;
		while (pq.size() > 0) {
			ans = Math.max(ans, pq.poll());
        }

		return ans <= maxT;
	}
}

