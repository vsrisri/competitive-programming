import java.util.*;
import java.io.*;

public class MilkMeasurementSilver {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("measurement.in"));
        PrintWriter pw = new PrintWriter(new File("measurement.out"));
        int n = sc.nextInt();
        sc.nextLine();
		int[] cowID = new int[1000001];
		int[] diff = new int[1000001];
		HashMap<Integer,Integer> cows = new HashMap<Integer, Integer>();
		int ID = 1;

		for (int i = 0; i < n; i++) {
			int day = sc.nextInt();
			cowID[day] = sc.nextInt();
			if (!cows.containsKey(cowID[day])) {
                cows.put(cowID[day], ID++);
            }
			String tmp = sc.next();
			if (tmp.charAt(0) == '+') {
                tmp = tmp.substring(1);
            }
			diff[day] = Integer.parseInt(tmp);
            sc.nextLine();
		}

		for (int i = 0; i < cowID.length; i++)
			if (cowID[i] != 0)
				cowID[i] = cows.get(cowID[i]);

		int[] milk = new int[ID];
		TreeMap<Integer,Integer> tm = new TreeMap<Integer,Integer>();
		tm.put(0, ID);
		int res = 0, max = 0;
		for (int i = 0; i < cowID.length; i++) {
			if (cowID[i] == 0) {
                continue;
            }
			int prev = milk[cowID[i]];
			int cur = prev + diff[i];
			milk[cowID[i]] = cur;
			int numOld = tm.get(prev);
			boolean flag = false;
			if (numOld == 1) {
				flag = true;
				tm.remove(prev);
			} else {
				tm.put(prev, numOld-1);
            }
			if (tm.containsKey(cur)) {
				tm.put(cur, tm.get(cur)+1);
			} else {
				tm.put(cur, 1);
            }
			if (prev < max && cur >= max) {
                res++;
            }
			if (prev == max && numOld > 1 && cur > max) {
                res++;
            }

			int newtop = tm.lastKey();
			if (prev == max && cur < newtop) {
                res++;
            }
			if (prev == max && cur == newtop && tm.get(newtop) > 1) {
                res++;
            }
			max = newtop;
		}
		pw.println(res);
		pw.close();
		sc.close();
    }
}
