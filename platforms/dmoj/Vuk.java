import java.io.*;
import java.util.*;

public class Vuk {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int r = scanner.nextInt();
        int c = scanner.nextInt();
        char[][] grid = new char[r][];
        Queue<Coord> q = new LinkedList<Coord>();
        int[] xDir = {0, 0, -1, 1};
        int[] yDir = {-1, 1, 0, 0};
        int sX = 0; int sY = 0;
        int eX = 0; int eY = 0;
        int[][] minDist = new int[r][c];
        scanner.nextLine();

        for (int idx = 0; idx < r; idx++) {
            grid[idx] = scanner.nextLine().toCharArray();
            for (int idx2 = 0; idx2 < c; idx2++) {
                minDist[idx][idx2] = -1;
                if (grid[idx][idx2] == '+') {
                    q.offer(new Coord(idx, idx2, 0));
                    minDist[idx][idx2] = 0;
                } else if (grid[idx][idx2] == 'V') {
                    sX = idx;
                    sY = idx2;
                 } else if (grid[idx][idx2] == 'J') {
                     eX = idx;
                     eY = idx2;
                }
            }
        }

        while (!q.isEmpty()) {
            Coord curr = q.poll();
            for (int i = 0; i < 4; i++) {
                int x = curr.x + xDir[i];
                int y = curr.y + yDir[i];
                if (x < 0 || y < 0 || x >= r || y >= c || minDist[x][y] != -1) {
                    continue;
                }
                minDist[x][y] = curr.m + 1;
                q.offer(new Coord(x, y, minDist[x][y]));
            }
        }

        boolean[][] visited = new boolean[r][c];
        visited[sX][sY] = true;
        PriorityQueue<Coord> pq = new PriorityQueue<Coord>();
        pq.offer(new Coord(sX, sY, minDist[sX][sY]));
        while (!pq.isEmpty()) {
            Coord curr = pq.poll();
            if (curr.x == eX && curr.y == eY) {
                System.out.println(curr.m);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int x = curr.x + xDir[i];
                int y = curr.y + yDir[i];
                if (x < 0 || y < 0 || x >= r || y >= c || visited[x][y]) {
                    continue;
                }

                visited[x][y] = true;
                pq.offer(new Coord(x, y, Math.min(curr.m, minDist[x][y])));
            }
        }
    }

    public static class Coord implements Comparable<Coord> {
        public int x;
        public int y;
        public int m;

        public Coord(int x, int y, int m) {
            this.x = x;
            this.y = y;
            this.m = m;
        }

        public int compareTo(Coord o) {
            return o.m - m;
        }
    }
}
