import java.util.*;
import java.io.*;

public class UCV2013H {
    public static int num;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int[][] g = new int[251][251];
            boolean[][] visited = new boolean[251][251];
            int[] sizeArr = new int[62501];
            int numS = 0;

            if (r == 0 && c == 0)
                break;

            for (int idx = 0; idx < r; idx++) {
                st = new StringTokenizer(br.readLine());
                for (int idx2 = 0; idx2 < c; idx2++) {
                    visited[idx][idx2] = false;
                    g[idx][idx2] = Integer.parseInt(st.nextToken());
                    sizeArr[idx * c + idx2 + 1] = 0;
                }
            }

            for (int idx = 0; idx < r; idx++) {
                for (int idx2 = 0; idx2 < c; idx2++) {
                    if (g[idx][idx2] == 1 && !visited[idx][idx2]) {
                        num = 0;
                        numS++;
                        dfs(g, visited, idx, idx2, r, c);
                        sizeArr[num]++;
                    }
                }
            }

            System.out.println(numS);
            for (int idx = 1; idx <= r * c; idx++) {
                if (sizeArr[idx] > 0) {
                    System.out.println(idx + " " + sizeArr[idx]);
                }
            }
        }
        br.close();
    }

    public static void dfs(int[][] g, boolean[][] visited, int idx, int idx2, int r, int c) {
        visited[idx][idx2] = true;
        num++;

        if ((idx - 1 >= 0 && idx2 >= 0 && idx - 1< r && idx2 < c) && g[idx - 1][idx2] == 1 && !visited[idx - 1][idx2]) {
            dfs(g, visited, idx - 1, idx2, r, c);
        }

        if ((idx + 1 >= 0 && idx2 >= 0 && idx + 1 < r && idx2 < c) && g[idx + 1][idx2] == 1 && !visited[idx + 1][idx2]) {
            dfs(g, visited, idx + 1, idx2, r, c);
        }

        if ((idx >= 0 && idx2 - 1 >= 0 && idx < r && idx2 - 1 < c) && g[idx][idx2 - 1] == 1 && !visited[idx][idx2 - 1]) {
            dfs(g, visited, idx, idx2 - 1, r, c);
        }

        if ((idx >= 0 && idx2 + 1>= 0 && idx < r && idx2 + 1 < c) && g[idx][idx2 + 1] == 1 && !visited[idx][idx2 + 1]) {
            dfs(g, visited, idx, idx2 + 1, r, c);
        }
    }
}
