import java.util.*;
import java.io.*;

public class Wormhole {
    public static int n;
	public static Pos[] posArr;
	public static int ans;
	public static int[] next;
    static class Pos {

	    public int x;
	    public int y;

	    public Pos(int x, int y) {
		    this.x = x;
		    this.y = y;
	    }
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("wormhole.in"));
        n = sc.nextInt();
        posArr = new Pos[n];
        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            posArr[i] = new Pos(x, y);
        }
        sc.close();
        next = new int[n];
        Arrays.fill(next, -1);

        for (int i = 0; i < n; i++) {
            int currAns = -1;
            for (int j = 0; j < n; j++) {
                if (i == j || posArr[i].y != posArr[j].y) {
                    continue;
                }
                if (posArr[j].x < posArr[i].x) { 
                    continue;
                }
                if (currAns == -1 || posArr[j].x < posArr[currAns].x) {
                    currAns = j;
                }
            }
            next[i] = currAns;
        }
        PrintWriter pw = new PrintWriter(new File("wormhole.out"));
        pw.print(Wormhole.solve());
        pw.close();
    }

    public static int solve() {
        int[] perm = new int[n];
		boolean[] used = new boolean[n];
        return Wormhole.go(perm, used, 0);
    }
    public static int go(int[] perm, boolean[] used, int k) {
		if (k == n) {
            return eval(perm);
        }

		if (k % 2 == 0) {
			int loc = 0;
			while (used[loc]) loc++;
			perm[k] = loc;
			used[loc] = true;
			int res = go(perm, used, k+1);
			used[loc] = false;
			return res;
		}
		else {
			int res = 0;
			for (int i = perm[k - 1] + 1; i < n; i++) {
				if (!used[i]) {
					perm[k] = i;
					used[i] = true;
					res += go(perm, used, k+1);
					used[i] = false;
				}
			}

			return res;
		}
	}

	public static int eval(int[] perm) {
		int[] p = new int[n];
		for (int i = 0; i < n; i+=2) {
			p[perm[i]] = perm[i + 1];
			p[perm[i + 1]] = perm[i];
		}
		for (int start = 0; start < n; start++) {
			int cur = start;
			for (int step = 0; step < 2 * n + 1; step++) {
				cur = p[cur];
				cur = next[cur];
				if (cur == -1) {
                    break;
                }
			}
			if (cur != -1) {
                return 1;
            }
		}
		return 0;
	}
}
