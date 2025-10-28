import java.io.*;
import java.util.*;

public class Main {
    public static final int MOD = 10007;
    public static int[] height = new int[1002];
    public static int[][] TLCount = new int[1002][1002];
    public static int[][] BRCount = new int[1002][1002];
    public static char[][] grid = new char[1002][1002];
    public static char[][] tempRotGrid = new char[1002][1002];
    public static char[][] rotatedGrid = new char[1002][1002];
    public static int[][] rectCount = new int[1002][1002];
    public static int N = 0;
    public static int ans = 0;
    public static int[][] prefixSum = new int[1002][1002];
    public static void rotateGrid90() {
        for (int i = 1; i <= N; ++i) {
            System.arraycopy(rotatedGrid[i], 1, tempRotGrid[i], 1, N);
        }

        for (int i = 1; i <= N; ++i) {
            for (int j = 1; j <= N; ++j) {
                rotatedGrid[i][j] = tempRotGrid[N - j + 1][i];
            }
        }
    }

    public static void helper() {
        countRectangles();
        for (int i = 1; i <= N; ++i) {
            System.arraycopy(rectCount[i], 1, TLCount[i], 1, N);
        }

        rotateGrid90();
        rotateGrid90();
        countRectangles();
        for (int i = 1; i <= N; ++i) {
            for (int j = 1; j <= N; ++j) {
                BRCount[i][j] = rectCount[N - i + 1][N - j + 1];
            }
        }
    }

    public static void subtractOverlap() {
        helper();
        for (int i = 1; i <= N; ++i) {
            for (int j = 1; j <= N; ++j) {
                prefixSum[i][j] = TLCount[i][j] + prefixSum[i - 1][j] + prefixSum[i][j - 1] - prefixSum[i - 1][j - 1];
                prefixSum[i][j] %= MOD;
                if (prefixSum[i][j] < 0) {
                    prefixSum[i][j] += MOD;
                }
            }
        }

        for (int i = 1; i <= N; ++i) {
            for (int j = 1; j <= N; ++j) {
                ans -= BRCount[i][j] * prefixSum[i - 1][j - 1];
                ans %= MOD;
                if (ans < 0) {
                    ans += MOD;
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 1; i <= N; i++) {
            String line = br.readLine();
            for (int j = 1; j <= N; j++) {
                grid[i][j] = line.charAt(j - 1);
                rotatedGrid[i][j] = grid[i][j];
            }
        }

        helper();

        int totalSuffix = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                totalSuffix += BRCount[i][j];
                if (totalSuffix >= MOD) totalSuffix -= MOD;
            }
        }

        int suffixLeft = totalSuffix;
        for (int i = 1; i <= N; i++) {
            int rowPrefix = 0;
            for (int j = 1; j <= N; j++) {
                rowPrefix += TLCount[i][j];
                if (rowPrefix >= MOD) rowPrefix -= MOD;

                suffixLeft -= BRCount[i][j];
                if (suffixLeft < 0) suffixLeft += MOD;
            }
            ans += rowPrefix * suffixLeft;
            ans %= MOD;
        }

        suffixLeft = totalSuffix;
        for (int j = 1; j <= N; j++) {
            int colPrefix = 0;
            for (int i = 1; i <= N; i++) {
                colPrefix += TLCount[i][j];
                if (colPrefix >= MOD) colPrefix -= MOD;

                suffixLeft -= BRCount[i][j];
                if (suffixLeft < 0) suffixLeft += MOD;
            }
            ans += colPrefix * suffixLeft;
            ans %= MOD;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                rotatedGrid[i][j] = grid[i][j];
            }
        }

        subtractOverlap();

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                rotatedGrid[i][j] = grid[i][j];
            }
        }

        rotateGrid90();
        subtractOverlap();

        System.out.println(ans);
    }


    public static void countRectangles() {
        Arrays.fill(height, 0);
        for (int row = 1; row <= N; row++) {
            Deque<int[]> stack = new ArrayDeque<>();
            int sum = 0;
            for (int col = 1; col <= N; col++) {
                height[col] = (rotatedGrid[row][col] == 'C') ? height[col] + 1 : 0;

                int width = 1;
                while (!stack.isEmpty() && stack.peek()[0] >= height[col]) {
                    int[] popped = stack.pop();
                    sum -= popped[0] * popped[1];
                    width += popped[1];
                }

                if (height[col] == 0) {
                    rectCount[row][col] = 0;
                    continue;
                }

                stack.push(new int[]{height[col], width});
                sum += height[col] * width;
                int count = (sum - 1) % MOD;
                rectCount[row][col] = (count < 0) ? count + MOD : count;
            }
        }
    }
}

