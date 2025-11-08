import java.util.*;
import java.io.*;

public class CowCrossingSilver1 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("helpcross.in"));
        PrintWriter pw = new PrintWriter(new File("helpcross.out"));
        int n = sc.nextInt();
		int m = sc.nextInt();

		Animal[] all = new Animal[m + n];

		for (int i = 0; i < n; i++) {
			int t = sc.nextInt();
			all[i] = new Animal(t, t, false);
		}

		for (int i = n; i < n + m; i++) {
			int s = sc.nextInt();
			int e = sc.nextInt();
			all[i] = new Animal(s, e, true);
		}

		Arrays.sort(all);

		int res = 0;
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

		for (int i = 0; i < all.length; i++) {
			if (all[i].isCow) {
				pq.offer(all[i].end);
			} else {
				while (pq.size() > 0 && pq.peek() < all[i].start) {
                    pq.poll();
                }
				if (pq.size() > 0) {
					res++;
					pq.poll();
				}
			}
		}

		pw.println(res);
		pw.close();
		sc.close();
	}

    static class Animal implements Comparable<Animal> {
        public int start;
        public int end;
        public boolean isCow;

        public Animal(int s, int e, boolean b) {
            start = s;
            end = e;
            isCow = b;
        }

        public int compareTo(Animal other) {
            if (this.start != other.start) {
                return this.start - other.start;
            }
            if (this.isCow && !other.isCow) {
                return -1;
            }
            if (!this.isCow && other.isCow) {
                return 1;
            }
            return 0;
        }
    }
}

