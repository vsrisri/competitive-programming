// USACO 2018 February Contest, Bronze Problem 2. Hoofball
import java.util.*;
import java.io.*;

public class HoofBall {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("hoofball.in"));
        PrintWriter p = new PrintWriter(new File("hoofball.out"));
        int n = sc.nextInt();
        sc.nextLine();
        int[] vals = new int[n];
        for (int i = 0; i < n; i++) {
            vals[i] = sc.nextInt();
        }
        Arrays.sort(vals);
        p.println(HoofBall.solve(vals));
        sc.close();
        p.close();
    }

    public static int solve(int[] vals) {
        int n = vals.length;
        if (n < 3) {
            return 1;
        }
        boolean[] right = new boolean[n];
		right[0] = true;
        for (int i = 1; i < n - 1; i++) {
            if (vals[i] - vals[i - 1] > vals[i + 1] - vals[i]) {
                right[i] = true;
            }
        }

        int ans = 0;
        int i = 0;
        while (i < n) {
			int rightIdx = 0;
			while (right[i]) {
				i++;
				rightIdx++;
			}
			int left = 0;
			while (i < n && !right[i]) {
				i++;
				left++;
			}
			if (rightIdx > 1 && left > 1)
				ans += 2;
			else
				ans++;
        }
        return ans;
    }
}
