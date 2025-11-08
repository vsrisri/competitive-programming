// USACO 2020 US Open Contest, Silver
// Problem 1. Social Distancing
import java.io.*;
import java.util.*;

public class SocialDistancingSilver {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("socdist.in"));
        PrintWriter pw = new PrintWriter(new File("socdist.out"));
        int n = sc.nextInt();
        String s = sc.next();
		ArrayList<Integer> inArr = new ArrayList<Integer>();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '1') {
				inArr.add(i);
            }
        }

		int low = 1, high = n - 1;
		for (int i = 1; i < inArr.size(); i++) {
            high = Math.min(inArr.get(i) - inArr.get(i - 1), high);
        }

		while (low < high) {
			int mid = (low + high + 1) / 2;
			if (isPoss(mid, inArr, n)) {
				low = mid;
			} else {
				high = mid - 1;
            }
		}

		pw.println(low);
		pw.close();
		sc.close();
	}

	public static boolean isPoss(int d, ArrayList<Integer> inArr, int n) {
		if (inArr.size() == 0) {
            return d < n;
        }

		int placeIdx = inArr.get(0) / d + (n - 1 - inArr.get(inArr.size() - 1)) / d;
		for (int i = 0; i < inArr.size() - 1; i++) {
			placeIdx += Math.max(0, ((inArr.get(i + 1) - inArr.get(i)) / d - 1));
        }
		return placeIdx >= 2;
	}
}

