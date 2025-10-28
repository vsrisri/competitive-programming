//Incomplete
import java.io.*;
import java.util.*;

class Pos {
    int r, c;

    Pos(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

public class DontFollowLights {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int rows = Integer.parseInt(st.nextToken());
        int cols = Integer.parseInt(st.nextToken());

        String[] grid = new String[rows];
        ArrayList<Pos>[][] poss = new ArrayList[rows][cols];
        int[][] dist = new int[rows][cols];

        for (int r = 0; r < rows; r++) {
            grid[r] = br.readLine();
        }

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                poss[r][c] = new ArrayList<>();
            }
        }

        for (int r = 0; r < rows; r++) {
            for (int c = 0, numT = 0; c < cols; c++) {
                if (grid[r].charAt(c) == '*') numT++;
                if (c - 1 >= 0 && grid[r].charAt(c - 1) != '*' && numT < 2) {
                    poss[r][c].add(new Pos(r, c - 1));
                }
            }

            for (int c = cols - 1, numT = 0; c >= 0; c--) {
                if (grid[r].charAt(c) == '*') numT++;
                if (c + 1 < cols && grid[r].charAt(c + 1) != '*' && numT < 2) {
                    poss[r][c].add(new Pos(r, c + 1));
                }
            }
        }

        for (int c = 0; c < cols; c++) {
            for (int r = 0, numT = 0; r < rows; r++) {
                if (grid[r].charAt(c) == '*') numT++;
                if (r - 1 >= 0 && grid[r - 1].charAt(c) != '*' && numT < 2) {
                    poss[r][c].add(new Pos(r - 1, c));
                }
            }

            for (int r = rows - 1, numT = 0; r >= 0; r--) {
                if (grid[r].charAt(c) == '*') numT++;
                if (r + 1 < rows && grid[r + 1].charAt(c) != '*' && numT < 2) {
                    poss[r][c].add(new Pos(r + 1, c));
                }
            }
        }

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                dist[r][c] = -1;
            }
        }

        Queue<Pos> Q = new LinkedList<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r].charAt(c) == 'S') {
                    Q.add(new Pos(r, c));
                    dist[r][c] = 0;
                }
            }
        }

        while (!Q.isEmpty()) {
            Pos curr = Q.poll();
            int distV = dist[curr.r][curr.c];

            if (grid[curr.r].charAt(curr.c) == 'D') {
                System.out.println(distV);
                return;
            }

            for (Pos neighb : poss[curr.r][curr.c]) {
                if (dist[neighb.r][neighb.c] < 0) {
                    Q.add(neighb);
                    dist[neighb.r][neighb.c] = distV + 1;
                }
            }
        }

        System.out.println(-1);
    }
}
