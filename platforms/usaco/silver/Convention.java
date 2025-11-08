import java.util.*;
import java.io.*;

public class Convention {
    public static int n;
	public static int numBus;
	public static int busSize;
	public static int[] arrivals;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("convention.in"));
        PrintWriter pw = new PrintWriter(new File("convention.out"));
        n = sc.nextInt();
        numBus = sc.nextInt();
        busSize = sc.nextInt();
        arrivals = new int[n];
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            arrivals[i] = sc.nextInt();
        }
        Arrays.sort(arrivals);
        pw.println(Convention.helper());
        pw.close();
        sc.close();
    }

    public static int helper() {
        int low = 0, high = 1000000000;
        while (low < high) {
            int mid = (low + high) / 2;
            if (Convention.isPoss(mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    public static boolean isPoss(int gap) {
        int cur = 0;
		for (int i = 0; i < numBus; i++) {
			int lastI = cur;
            while (lastI < n && arrivals[lastI] - arrivals[cur] <= gap && lastI - cur < busSize) {
				lastI++;
            }
            if (lastI == n) {
                return true;
            }
            cur = lastI;
        }
        return false;
    }
}
