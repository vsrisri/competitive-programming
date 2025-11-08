// USACO 2019 US Open Contest, Silver
// Problem 1. Left Out
import java.util.*;
import java.io.*;

public class LeftOut {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(new File("leftout.in"));
        PrintWriter pw = new PrintWriter(new File("leftout.out"));
        int n = stdin.nextInt();
        boolean[][] grid = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            String curr = stdin.next();
            for (int j = 0; j < n; j++) {
                if (curr.charAt(j) == 'R') {
                    grid[i][j] = true;
                }
            }
        }
        int badRow = helper(n, grid);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                boolean tmp = grid[i][j];
                grid[i][j] = grid[j][i];
                grid[j][i] = tmp;
            }
        }

        int badCol = helper(n, grid);
        if (badRow == -1 || badCol == -1) {
            pw.print(-1);
        } else {
            pw.println((badRow + 1) + " " + (badCol + 1));
        }
        stdin.close();
        pw.close();
    }

    public static int helper(int n, boolean[][] grid) {
        int[] total = new int[n + 1];
        int[] ans = new int[n];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[0][j] == grid[i][j]) {
                    ans[i]++;
                }
            }
            total[ans[i]]++;
        }

        if (total[0] + total[n] == n - 2) {
            for (int i = 1; i < n; i++) {
                if (ans[i] == 1 || ans[i] == n - 1) {
                    return i;
                }
            }
        }

        if (total[1] + total[n - 1] == n - 1) {
            return 0;
        }
        return -1;
    }
}
