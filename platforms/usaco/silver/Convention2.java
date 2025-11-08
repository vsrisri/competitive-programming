import java.util.*;
import java.io.*;

public class Convention2 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("convention2.in"));
        PrintWriter pw = new PrintWriter(new File("convention2.out"));
        int n = sc.nextInt();
        sc.nextLine();
        Cow[] arr = new Cow[n];
        for (int i = 0; i < n; i++) {
            int s = sc.nextInt();
            int t = sc.nextInt();
            arr[i] = new Cow(s, t, i);
        }
        Arrays.sort(arr);
        PriorityQueue<Cow> pq = new PriorityQueue<Cow>(10, new Comparator<Cow>() {
            public int compare(Cow a, Cow b) {
				return a.priority - b.priority;
			}
		});

        int curT = 0, ans = 0;
		int curCow = 0;

		while (curCow < n || pq.size() > 0) {
			if (curCow < n && arr[curCow].start <= curT) {
				pq.offer(arr[curCow++]);
			}

			else if (pq.size() == 0) {
				Cow cur = arr[curCow++];
				curT = cur.start + cur.time;
			}

			else {
				Cow cur = pq.poll();
				ans = Math.max(ans, curT-cur.start);
				curT += cur.time;
			}
		}

        pw.print(ans);
        sc.close();
        pw.close();


    }
    static class Cow implements Comparable<Cow> {

	public int start;
	public int time;
	public int priority;

	public Cow(int s, int t, int p) {
		start = s;
		time = t;
		priority = p;
	}

	public int compareTo(Cow other) {
		return this.start - other.start;
	}

    }
}
