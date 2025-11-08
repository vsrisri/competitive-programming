//USACO 2016 US Open Contest, Bronze Problem 2. Bull in a China Shop
import java.util.*;
import java.io.*;

public class BullInChinaShop {
    public static int n;
    public static int k;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("bcs.in"));
        PrintWriter pw = new PrintWriter(new File("bcs.out"));
        n = sc.nextInt();
        k = sc.nextInt();
        sc.nextLine();
        boolean[][] b = new boolean[n][n];
        int orig = 0;

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine();
            for (int j = 0; j < n; j++) {
                if (line.charAt(j) == '#') {
                    b[i][j] = true;
                }
                b[i][j] = false;
                if (b[i][j]) {
                    orig++;
                }
            }
        }
        int[] p  = new int[k];
        boolean[][][] pieceArr = new boolean[k][n][n];
        for (int idx = 0; idx < k; idx++) {
            for (int i = 0; i < n; i++) {
                String line = sc.nextLine();
                for (int j = 0; j < n; j++) {
                    if (line.charAt(j) == '#') {
                        pieceArr[idx][i][j] = true;
                    }
                    pieceArr[idx][i][j] = false;
                    if (pieceArr[idx][i][j]) {
                        p[idx]++;
                    }
                }
            }
        }

        int ans = -1;

        for (int idx = 0; idx < k; idx++) {
            boolean[][] left = BullInChinaShop.subtract(b, pieceArr[idx]);
            if (left == null) {
                continue;
            }

            boolean isFound = false;
            for (int j = 0; j < k; j++) {
                if ((j == idx) || (orig != p[idx] + p[j])) {
                    continue;
                }
                boolean[][] res = BullInChinaShop.subtract(left, pieceArr[j]);
                if (res == null) {
                    continue;
                }
                if (BullInChinaShop.isFalse(res)) {
                    isFound = true;
                    if (idx < j) {
                        ans = k*idx + j;
                    } else {
                        ans = k*j + idx;
                    }
                    break;
                }
            }
            if (isFound) {
                break;
            }
        }
        pw.print((ans/k + 1) + " " + (ans % k + 1));
        sc.close();
        pw.close();
    }

    public static boolean[][] subtract(boolean[][] original, boolean[][] piece) {
        int[] origLoc = BullInChinaShop.getLeft(original);
        int[] pieceLoc = BullInChinaShop.getLeft(piece);
        boolean[][] ans = new boolean[n][];
        for (int i = 0; i < n; i++) {
			ans[i] = Arrays.copyOf(original[i], n);
        }
        for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
                int x = i - pieceLoc[0] + origLoc[0];
                int y = j - pieceLoc[1] + origLoc[1];
                if ((piece[i][j]) && (!BullInChinaShop.isInBounds(x, y))) {
                    return null;
                }
                if ((piece[i][j]) && (!original[x][y])) {
                    return null;
                }
                if (piece[i][j]) {
                    ans[x][y] = false;
                }

            }
        }
        return ans;

    }
    public static int[] getLeft(boolean[][] piece) {
        int[] ans = new int[2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (piece[i][j]) {
                    ans[0] = i;
                    ans[1] = j;
                    return ans;
                }
            }
        }
        return ans;
    }

    public static boolean isFalse(boolean[][] piece) {
        for (int idx = 0; idx < n; idx++) {
            for (int j = 0; j < n; j++) {
                if (piece[idx][j]) {
                    return false;
                }
            }
        }
        return true;
    }
    public static boolean isInBounds(int x, int y) {
        return x >= 0 && x < n && y >=0 && y < n;
    }
}



