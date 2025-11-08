// USACO 2017 January Contest, Silver
// Problem 2. Hoof, Paper, Scissors
import java.util.*;
import java.io.*;

public class HoofPaperScissorsSilver {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("hps.in"));
        PrintWriter pw = new PrintWriter(new File("hps.out"));
        int n = sc.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = convert(sc.next());
        }

		int[][] games = new int[3][n];
		for (int i = 0; i < n; i++) {
			games[arr[i]][i]++;
        }

		for (int i = 0; i < 3; i++) {
			for (int j = 1; j < n; j++) {
				games[i][j]+= games[i][j - 1];
            }
        }

		int best = 0; 
		for (int i = 0; i <= n; i++) {
			best = Math.max(best, max(games, 0, i - 1) + max(games, i, n - 1)); 
        }

		pw.println(best);
		pw.close();
		sc.close();
	}

	public static int max(int[][] arr, int start, int end) {
		if (start > end) {
            return 0;
        }
		int res = 0;
		for (int i = 0; i < 3; i++) {
			int sub = start > 0 ? arr[i][start - 1] : 0;
			res = Math.max(res, arr[i][end] - sub);
		}
		return res;
	}

	public static int convert(String s) {
		if (s.equals("H")) {
            return 0;
        }
		if (s.equals("P")) {
            return 1;
        }
		return 2;
	}
}


