// USACO 2016 US Open Contest, Silver
// Problem 2. Diamond Collector
import java.util.*;
import java.io.*;

public class DiamondCollector {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(new File("diamond.in"));
        PrintWriter pw = new PrintWriter(new File("diamond.out"));
		int n = stdin.nextInt();
		int k = stdin.nextInt();
        int[] vals = new int[n];
        for (int i = 0; i < n; i++) {
            vals[i] = stdin.nextInt();
            stdin.nextLine();
        }

		Arrays.sort(vals);

		int low = 0, high = 0;
		int[] results = new int[n];

		while (high < n) {
            if (vals[high] - vals[low] <= k) {
                results[low] = Math.max(results[low], high - low + 1);
                high++;
            } else {
                low++;
            }
		}

		for (int i = low + 1; i < n; i++)
            results[i] = n - i;

        int[] lookup = new int[n];
        lookup[n - 1] = results[n - 1];
        for (int i = n - 2; i >= 0; i--)
            lookup[i] = Math.max(lookup[i + 1], results[i]);

		int res = 0;
		for (int i = 0; i < n; i++) {
            int tmp = results[i];
            if (i + results[i] < n) {
                tmp += lookup[i + results[i]];
            }
            res = Math.max(res, tmp);
		}

		pw.println(res);
		pw.close();
		stdin.close();
    }
    
}
