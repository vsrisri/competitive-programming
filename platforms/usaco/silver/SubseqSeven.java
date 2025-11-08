// USACO 2016 January Contest, Silver 
// Problem 2. Subsequences Summing to Sevens
import java.io.*;
import java.util.*;

public class SubseqSeven {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(new File("div7.in"));
        PrintWriter pw = new PrintWriter(new File("div7.out"));
        int n = stdin.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
            arr[i] = stdin.nextInt();
        }

		int[] min = new int[7];
		Arrays.fill(min, n);
		int[] max = new int[7];
		Arrays.fill(max, -1);
		min[0] = 0;
		max[0] = 0;

		int[] diffArr = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			diffArr[i] = (diffArr[i - 1] + arr[i - 1]) % 7;
			min[diffArr[i]] = Math.min(min[diffArr[i]], i);
			max[diffArr[i]] = Math.max(max[diffArr[i]], i);
		}

		int res = 0;
		for (int i = 0; i < 7; i++) {
			res = Math.max(res, max[i] - min[i]);
        }

		pw.println(res);
		pw.close();
		stdin.close();
    }
}
