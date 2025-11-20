// Incomplte - TLE 
import java.io.*;
import java.util.*;

public class MATSUM {
    public static int N;
    public static int[][] bit;
    public static int[][] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < t; tc++) {
            N = Integer.parseInt(br.readLine());
            bit = new int[N + 1][N + 1];
            arr = new int[N][N];
            while (true) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String comm = st.nextToken();
                if (comm.equals("END")) {
                    break;
                }

                if (comm.equals("SET")) {
                    int x = Integer.parseInt(st.nextToken());
                    int y = Integer.parseInt(st.nextToken());
                    int v = Integer.parseInt(st.nextToken());
                    int diff = v - arr[x][y];
                    arr[x][y] = v;
                    update(x + 1, y + 1, diff);
                } else {
                    int x1 = Integer.parseInt(st.nextToken());
                    int y1 = Integer.parseInt(st.nextToken());
                    int x2 = Integer.parseInt(st.nextToken());
                    int y2 = Integer.parseInt(st.nextToken());
                    out.append(rangeSum(x1 + 1, y1 + 1, x2 + 1, y2 + 1)).append("\n");
                }
            }
            out.append("\n");
        }
        System.out.print(out.toString());
        br.close();
    }
    
    public static void update(int x, int y, int val) {
        int dx = x;
        while (dx <= N) {
            int dy = y;
            while (dy <= N) {
                bit[dx][dy] += val;
                dy += dy & -dy;
            }
            dx += dx & -dx;
        }
    }

    public static int query(int x, int y) {
        int sum = 0;
        int dx = x;
        while (dx > 0) {
            int dy = y;
            while (dy > 0) {
                sum += bit[dx][dy];
                dy -= dy & -dy;
            }
            dx -= dx & -dx;
        }
        return sum;
    }

    public static int rangeSum(int x1, int y1, int x2, int y2) {
        return query(x2, y2) - query(x1 - 1, y2) - query(x2, y1 - 1) + query(x1 - 1, y1 - 1);
    }
}
