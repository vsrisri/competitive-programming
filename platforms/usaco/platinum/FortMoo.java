import java.io.*;
import java.util.*;

public class FortMoo {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("fortmoo.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("fortmoo.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int rows = Integer.parseInt(st.nextToken());
        int cols = Integer.parseInt(st.nextToken());
        int[][] land = new int[rows][cols];
        for (int r = 0; r < rows; r++) {
            String line = br.readLine();
            for (int c = 0; c < cols; c++) {
                land[r][c] = line.charAt(c) == 'X' ? 1 : 0;
            }
        }

        int[][] colSum = new int[rows + 1][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                colSum[r + 1][c] = colSum[r][c] + land[r][c];
            }
        }

        int ans = 0;
        for (int startRow = 0; startRow < rows; startRow++) {
            for (int endRow = startRow; endRow < rows; endRow++) {
                int left = -1;
                for (int c = 0; c < cols; c++) {
                    boolean isTopOrBottomX = (land[startRow][c] == 1 || land[endRow][c] == 1);
                    boolean hasXBetween = (colSum[endRow + 1][c] - colSum[startRow][c]) > 0;

                    if (isTopOrBottomX || hasXBetween) {
                        left = -1;
                    } else {
                        if (left == -1) {
                            left = c;
                        }
                        int width = c - left + 1;
                        int height = endRow - startRow + 1;
                        int area = width * height;
                        ans = Math.max(ans, area);
                    }
                }
            }
        }

        pw.println(ans);
        pw.close();
    }
}

