import java.io.*;
import java.util.*;

public class MAKEMAZE {
    static int t, m, n;
    static char[][] g;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            g = new char[m][n];
            boolean[][] done = new boolean[m][n];
            List<int[]> entrances = new ArrayList<>();
            
            for (int i = 0; i < m; i++) {
                String line = br.readLine();
                for (int j = 0; j < n; j++) {
                    g[i][j] = line.charAt(j);
                    if ((i == 0 || j == 0 || i == m - 1 || j == n - 1) && g[i][j] == '.') {
                        entrances.add(new int[]{i, j});
                    }
                }
            }
            
            if (entrances.size() != 2) {
                bw.write("invalid\n");
                continue;
            }

            int[] start = entrances.get(0);
            int[] end = entrances.get(1);
            if (bfs(start[0], start[1], end[0], end[1], m, n, done)) {
                bw.write("valid\n");
            } else {
                bw.write("invalid\n");
            }
        }
        
        br.close();
        bw.close();
    }

    public static boolean bfs(int startX, int startY, int endX, int endY, int m, int n, boolean[][] done) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startX, startY});
        done[startX][startY] = true;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0];
            int y = curr[1];
            if (x == endX && y == endY) {
                return true;
            }

            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];

                if (newX >= 0 && newX < m && newY >= 0 && newY < n && !done[newX][newY] && g[newX][newY] == '.') {
                    done[newX][newY] = true;
                    queue.offer(new int[]{newX, newY});
                }
            }
        }
        return false;
    }
}

