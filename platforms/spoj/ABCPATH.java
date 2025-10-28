import java.io.*;
import java.util.*;

public class ABCPATH {
    static int H, W;
    static char[][] grid;
    static int[][] dp;
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int tc = 1;
        while (true) {
            st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            if (H == 0 && W == 0) {
                break;
            }

            grid = new char[H][W];
            for (int i = 0; i < H; i++) {
                String line = br.readLine();
                for (int j = 0; j < W; j++) {
                    grid[i][j] = line.charAt(j);
                }
            }

            dp = new int[H][W];
            for (int i = 0; i < H; i++) {
                Arrays.fill(dp[i], -1);
            }

            int ans = 0;
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    if (grid[i][j] == 'A') {
                        ans = Math.max(ans, dfs(i, j));
                    }
                }
            }

            System.out.println("Case " + tc + ": " + ans);
            tc++;
        }
        br.close();
    }

    public static int dfs(int x, int y) {
        if (dp[x][y] != -1) {
            return dp[x][y];
        }
        int max = 1;
        for (int d = 0; d < 8; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (nx >= 0 && nx < H && ny >= 0 && ny < W) {
                if (grid[nx][ny] == grid[x][y] + 1) {
                    max = Math.max(max, 1 + dfs(nx, ny));
                }
            }
        }
        dp[x][y] = max;
        return dp[x][y];
    }
}

