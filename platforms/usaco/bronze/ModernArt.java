// USACO 2017 US Open Contest, Bronze Problem 3. Modern Art
import java.io.*;
import java.util.*;

public class ModernArt {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("art.in"));
        PrintWriter pw = new PrintWriter(new File("art.out"));
        int n = sc.nextInt();
        int[][] grid = new int[n][n];
		int[] minX = new int[n * n + 1];
		int[] maxX = new int[n * n + 1];
		int[] minY = new int[n * n + 1];
		int[] maxY = new int[n * n + 1];
		Arrays.fill(minX, n * n + 1);
		Arrays.fill(minY, n * n + 1);
		Arrays.fill(maxX, -1);
		Arrays.fill(maxY, -1);

        HashSet<Integer> set = new HashSet<Integer>();
        boolean[] count = new boolean[n * n + 1];
        for (int i = 0; i < n; i++) {
            String line = sc.nextLine();
            for (int j = 0; j < n; j++) {
                grid[i][j] = line.charAt(j) - '0';
                minX[grid[i][j]] = Math.min(minX[grid[i][j]], i);
				minY[grid[i][j]] = Math.min(minY[grid[i][j]], j);
				maxX[grid[i][j]] = Math.max(maxX[grid[i][j]], i);
				maxY[grid[i][j]] = Math.max(maxY[grid[i][j]], j);
                if (grid[i][j] != 0) {
                    set.add(grid[i][j]);
                    count[grid[i][j]] = true;
                }
            }
        }

        for (int i = 1; i < n * n; i++) {
            for (int j = minX[i]; j <= maxX[i]; j++) {
				for (int k = minY[i]; k <= maxY[i]; k++) {
                    if (grid[j][k] != i) {
                        count[grid[j][k]] = false;
                    }
                }
            }
        }

        int res = 0;
		for (int i = 1; i <= n*n; i++) {
			if (count[i]) {
				res++;
            }
        }
        pw.print(res);
        sc.close();
        pw.close();

    }
    
}
