import java.io.*;
import java.util.*;

public class Replication {
    static final int INF = (int) 1e9;
    static char[][] grid = new char[1000][1000];
    static boolean[][] visited = new boolean[1000][1000];
    static int[][] rockdist = new int[1000][1000];
    static int[][] replicationTime = new int[1000][1000];
    static int n, d;
    static int[] rowMoves = {1, -1, 0, 0};
    static int[] colMoves = {0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        Queue<int[]> rocks = new LinkedList<>();
        Queue<int[]> robots = new LinkedList<>();
        
        for (int i = 0; i < n; i++) {
            String row = br.readLine();
            for (int j = 0; j < n; j++) {
                grid[i][j] = row.charAt(j);
                rockdist[i][j] = INF;
                replicationTime[i][j] = -INF;
                if (grid[i][j] == '#') {
                    rockdist[i][j] = 0;
                    rocks.add(new int[]{i, j});
                } else if (grid[i][j] == 'S') {
                    robots.add(new int[]{i, j, 0});
                    visited[i][j] = true;
                }
            }
        }
        
        while (!rocks.isEmpty()) {
            int[] rock = rocks.poll();
            int r = rock[0], c = rock[1];
            int dist = rockdist[r][c];
            for (int i = 0; i < 4; i++) {
                int newRow = r + rowMoves[i], newCol = c + colMoves[i];
                if (isValid(newRow, newCol) && dist + 1 < rockdist[newRow][newCol]) {
                    rockdist[newRow][newCol] = dist + 1;
                    rocks.add(new int[]{newRow, newCol});
                }
            }
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(b[0], a[0]));
        while (!robots.isEmpty()) {
            int[] robot = robots.poll();
            int time = robot[2], r = robot[0], c = robot[1];
            pq.add(new int[]{rockdist[r][c] - 1, r, c});
            replicationTime[r][c] = rockdist[r][c] - 1;
            if (time / d == rockdist[r][c]){
                continue;
            }
            for (int i = 0; i < 4; i++) {
                int newRow = r + rowMoves[i], newCol = c + colMoves[i];
                if (isValid(newRow, newCol) && !visited[newRow][newCol] && time + 1 <= (long) d * rockdist[newRow][newCol]) {
                    visited[newRow][newCol] = true;
                    robots.add(new int[]{newRow, newCol, time + 1});
                }
            }
        }
        
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int time = current[0], r = current[1], c = current[2];
            if (time == 0 || replicationTime[r][c] != time) {
                continue;
            }
            for (int i = 0; i < 4; i++) {
                int newRow = r + rowMoves[i], newCol = c + colMoves[i];
                if (isValid(newRow, newCol) && grid[newRow][newCol] != '#' && time - 1 > replicationTime[newRow][newCol]) {
                    visited[newRow][newCol] = true;
                    replicationTime[newRow][newCol] = time - 1;
                    pq.add(new int[]{time - 1, newRow, newCol});
                }
            }
        }
        
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (replicationTime[i][j] >= 0) {
                    ans++;
                }
            }
        }
        
        System.out.println(ans);
    }

    public static boolean isValid(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < n && grid[r][c] != '#';
    }
}

