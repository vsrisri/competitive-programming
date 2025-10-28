import java.util.*;
import java.io.*;

public class CERC07K {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            int[] dx = {-1, 1, 0, 0};
            int[] dy = {0, 0, -1, 1};
            Queue<Node> queue = new LinkedList<>();
            String[] dim = br.readLine().trim().split(" ");
            int r = Integer.parseInt(dim[0]);
            int c = Integer.parseInt(dim[1]);
            if (r == 0 && c == 0) {
                break;
            }

            char[][] grid = new char[r][c];
            Node node = null;
            for (int idx = 0; idx < r; idx++) {
                String line = br.readLine();
                for (int idx2 = 0; idx2 < c; idx2++) {
                    grid[idx][idx2] = line.charAt(idx2);
                    if (grid[idx][idx2] == '*') {
                        node = new Node(idx, idx2, 0, 0);
                        grid[idx][idx2] = '.';
                    }
                }
            }

            queue.add(node);
            boolean isE = false;
            boolean[][][] isVisited = new boolean[r][c][16];
            while (!queue.isEmpty()) {
                Node curr = queue.poll();
                if (isOutOfBounds(curr.x, curr.y, r, c) || grid[curr.x][curr.y] == '#') {
                    continue;
                }

                if (grid[curr.x][curr.y] == 'X') {
                    System.out.println("Escape possible in " + curr.steps + " steps.");
                    isE = true;
                    break;
                }

                updBm(curr, grid[curr.x][curr.y]);
                if (isVisited[curr.x][curr.y][curr.bm] || !canGo(curr, grid[curr.x][curr.y])) {
                    continue;
                }

                isVisited[curr.x][curr.y][curr.bm] = true;
                for (int idx = 0; idx < 4; idx++) {
                    int x2 = curr.x + dx[idx];
                    int y2 = curr.y + dy[idx];
                    queue.add(new Node(x2, y2, curr.steps + 1, curr.bm));
                }
            }

            if (!isE) {
                System.out.println("The poor student is trapped!");
            }
            br.readLine(); 
        }

        br.close();
    }

    public static boolean isOutOfBounds(int x, int y, int r, int c) {
        return x < 0 || y < 0 || x >= r || y >= c;
    }

    public static void updBm(Node node, char cell) {
        switch (cell) {
            case 'b': node.bm |= 1; break;
            case 'y': node.bm |= 2; break;
            case 'r': node.bm |= 4; break;
            case 'g': node.bm |= 8; break;
        }
    }

    public static boolean canGo(Node node, char cell) {
        switch (cell) {
            case 'B': return (node.bm & 1) != 0;
            case 'Y': return (node.bm & 2) != 0;
            case 'R': return (node.bm & 4) != 0;
            case 'G': return (node.bm & 8) != 0;
            default: return true;
        }
    }

}

class Node {
    int x;
    int y;
    int steps;
    int bm;

    public Node(int x, int y, int steps, int bm) {
        this.x = x;
        this.y = y;
        this.steps = steps;
        this.bm = bm;
    }
}


