import java.io.*;
import java.util.*;

public class Prob3Feb25 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(reader.readLine().trim());
        int[][] grid = new int[size + 1][size + 1];
        int[][] updatedGrid = new int[size + 1][size + 1];
        int[][] ansGrid = new int[size + 1][size + 1];
        int[] count = new int[2 * size + 1];
        int[] visitedCount = new int[2 * size + 1];
        int[] aArr = new int[2 * size + 1];
        boolean currGrid = getVals(size, grid, count, visitedCount, aArr, updatedGrid);
        fill(reader, size, grid, count);

        if (!currGrid) {
            upd(size, grid, count, visitedCount, aArr, updatedGrid);
        }

        StringBuilder sb = new StringBuilder();
        if (updatedGrid[1][1] <= size + 1) {
            add(size, updatedGrid, sb);
        } else {
            fixGrid(size, updatedGrid, ansGrid, sb);
        }

        System.out.print(sb);
        reader.close();
    }

    public static void fill(BufferedReader reader, int size, int[][] grid, int[] count) throws IOException {
        for (int i = 1; i <= size; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 1; j <= size; j++) {
                grid[i][j] = Integer.parseInt(tokenizer.nextToken());
                count[grid[i][j]]++;
            }
        }
    }

    public static boolean getVals(int size, int[][] grid, int[] count, int[] visitedCount, int[] aArr, int[][] updatedGrid) {
        for (int i = 1; i <= size; i++) {
            boolean hasUniqueVal = false;
            for (int j = 1; j <= size; j++) {
                if (count[grid[i][j]] == 1) {
                    hasUniqueVal = true;
                    break;
                }
            }

            if (hasUniqueVal) {
                for (int j = 1; j <= size; j++) {
                    if (aArr[grid[i][j]] != 0) {
                        updatedGrid[i][j] = aArr[grid[i][j]];
                    } else {
                        int currentCount = count[grid[i][j]];
                        fixVals(grid[i][j], currentCount, visitedCount, aArr, updatedGrid, i, j);
                    }
                }
                return true;
            }
        }
        return false;
    }

    public static void upd(int size, int[][] grid, int[] count, int[] visitedCount, int[] aArr, int[][] updatedGrid) {
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                if (aArr[grid[i][j]] != 0) {
                    updatedGrid[i][j] = aArr[grid[i][j]];
                } else {
                    int currentCount = count[grid[i][j]];
                    fixVals(grid[i][j], currentCount, visitedCount, aArr, updatedGrid, i, j);
                }
            }
        }
    }

    public static void fixVals(int value, int count, int[] visitedCount, int[] aArr, int[][] updatedGrid, int i, int j) {
        if (visitedCount[2 + count - 1] != 0) {
            aArr[value] = 2 * visitedCount.length - count + 1;
            updatedGrid[i][j] = aArr[value];
            visitedCount[2 + count - 1] = 1;
        } else {
            aArr[value] = 2 + count - 1;
            updatedGrid[i][j] = aArr[value];
            visitedCount[2 + count - 1] = 1;
        }
    }

    public static void add(int size, int[][] grid, StringBuilder sb) {
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                sb.append(grid[i][j]);
                if (j != size) {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }
    }

    public static void fixGrid(int size, int[][] updatedGrid, int[][] ansGrid, StringBuilder sb) {
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                ansGrid[i][j] = 2 * size - updatedGrid[i][j] + 2;
                sb.append(ansGrid[i][j]);
                if (j != size) sb.append(" ");
            }
            sb.append("\n");
        }
    }
}

