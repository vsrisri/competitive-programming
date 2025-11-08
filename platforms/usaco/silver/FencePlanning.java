import java.util.*;
import java.io.*;

public class FencePlanning {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("fenceplan.in"));
        PrintWriter pw = new PrintWriter(new File("fenceplan.out"));
        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();

        int[][] inArr = new int[n][2];
		for (int i = 0; i < n; i++) {
			inArr[i][0] = sc.nextInt();
			inArr[i][1] = sc.nextInt();
            sc.nextLine();
		}

		Pair pairSet = new Pair(n);
		for (int i = 0; i < m; i++) {
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();
			pairSet.union(v1,v2);
		}

		int[] arr = new int[n];
		TreeSet<Integer> values = new TreeSet<Integer>();
		for (int i = 0; i < n; i++) {
			arr[i] = pairSet.find(i);
			values.add(arr[i]);
		}

		TreeMap<Integer,Integer> map = new TreeMap<Integer,Integer>();
		int id = 0;
		while (values.size() > 0) {
            map.put(values.pollFirst(), id++);
        }
		for (int i = 0; i < n; i++) {
            arr[i] = map.get(arr[i]);
        }

		int[] minx = new int[id];
		Arrays.fill(minx, 1000000000);
		int[] maxx = new int[id];
		Arrays.fill(maxx, -1);
		int[] miny = new int[id];
		Arrays.fill(miny, 1000000000);
		int[] maxy = new int[id];
		Arrays.fill(maxy, -1);

		for (int i=0; i<n; i++) {
			minx[arr[i]] = Math.min(minx[arr[i]], inArr[i][0]);
			maxx[arr[i]] = Math.max(maxx[arr[i]], inArr[i][0]);
			miny[arr[i]] = Math.min(miny[arr[i]], inArr[i][1]);
			maxy[arr[i]] = Math.max(maxy[arr[i]], inArr[i][1]);
		}

		int ans = 1000000000;
		for (int i = 0; i < id; i++) {
			ans = Math.min(ans, 2 * (maxx[i] - minx[i]) + 2 * (maxy[i] - miny[i]));
		}

		pw.println(ans);
		pw.close();
		sc.close();
	}

    static class Pair {
	public int[] par;
	public int n;

	public Pair(int idx) {
		n = idx;
		par = new int[n];
		for (int i = 0; i < n; i++)
			par[i] = i;
	}

	public void union(int a, int b) {
		int p1 = find(a);
		int p2 = find(b);
		if (p1 == p2) return;
		par[p2] = p1;
	}

	public int find(int a) {
		if (a == par[a]) {
            return a;
        }
		return par[a] = find(par[a]);
	}
}

}
