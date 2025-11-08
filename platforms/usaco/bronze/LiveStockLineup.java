import java.util.*;
import java.io.*;

public class LiveStockLineup {
    final public static String[] COWS = {"Beatrice", "Belinda", "Bella", "Bessie", "Betsy", "Blue", "Buttercup", "Sue"};
	public static int n;
	public static int[][] list;
	public static void main(String[] args) throws Exception {
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		for (int i = 0; i < COWS.length; i++) {
            map.put(COWS[i], i);
        }

		Scanner sc = new Scanner(new File("lineup.in"));
		n = sc.nextInt();

		list = new int[n][2];
		for (int i = 0; i < n; i++) {
			list[i][0] = map.get(sc.next());
			for (int j = 0; j < 4; j++) {
                sc.next();
            }
			list[i][1] = map.get(sc.next());
		}

		int[] perm = new int[COWS.length];
		boolean[] used = new boolean[COWS.length];
		LiveStockLineup.go(perm, used, 0);

		PrintWriter out = new PrintWriter(new FileWriter("lineup.out"));
		for (int i = 0; i<COWS.length; i++)
			out.println(COWS[perm[i]]);
		out.close();
		sc.close();
	}

	public static boolean go(int[] perm, boolean[] used, int k) {
		if (k == perm.length) {
            return LiveStockLineup.isValid(perm);
        }

		for (int i = 0; i < perm.length; i++) {
			if (!used[i]) {
				perm[k] = i;
				used[i] = true;
				boolean tmp =  LiveStockLineup.go(perm, used, k + 1);
				if (tmp) {
                    return true;
                }
				used[i] = false;
			}
		}
		return false;
	}

	public static boolean isValid(int[] perm) {
		int[] indexOf = new int[perm.length];
		for (int i = 0; i < perm.length; i++)
			indexOf[perm[i]] = i;

		for (int i = 0; i < n; i++)
			if (Math.abs(indexOf[list[i][0]] - indexOf[list[i][1]]) != 1)
				return false;

		return true;
	}
}
