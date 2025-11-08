// USACO 2017 February Contest, Silver
// Problem 2. Why Did the Cow Cross the Road II
import java.io.*;
import java.util.*;

public class CowCrossing2Silver {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("maxcross.in"));
        PrintWriter pw = new PrintWriter(new File("maxcross.out"));
        int n = sc.nextInt();
        int goal = sc.nextInt();
        int bad = sc.nextInt();

        int[] sum = new int[n];
		Arrays.fill(sum, 1);

		for (int idx = 0; idx < bad; idx++) {
			sum[sc.nextInt() - 1] = 0;
        }

		int curr = 0;
		for (int idx = 0; idx < goal; idx++) {
			curr+= sum[idx];
        }

		int best = curr;
		for (int idx = goal; idx < n; idx++) {
			curr+= (sum[idx] - sum[idx - goal]);
			best = Math.max(best, curr);
		}

		pw.println(goal - best);
		pw.close();
		sc.close();
        
    }
}
