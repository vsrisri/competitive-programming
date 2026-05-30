import java.io.*;
import java.util.*;

public class TCUTTER {
    public static int[] xs, ys;
    public static boolean[][] hcut, vcut;
    public static boolean[][] visited;
    public static int W, H;
    public static HashMap<Integer, Integer> xmap;
    public static HashMap<Integer, Integer> ymap;
    public static int compressX(int v) {
        return xmap.get(v);
    }

    public static int compressY(int v) {
        return ymap.get(v);
    }

    public static void bfs(int sr, int sc) {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{sr, sc});
        visited[sr][sc] = true;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1];
            if (r - 1 >= 0 && !visited[r - 1][c] && !hcut[r][c]) {
                visited[r - 1][c] = true;
                q.add(new int[]{r - 1, c});
            }

            if (r + 1 < H && !visited[r + 1][c] && !hcut[r + 1][c]) {
                visited[r + 1][c] = true;
                q.add(new int[]{r + 1, c});
            }

            if (c - 1 >= 0 && !visited[r][c - 1] && !vcut[r][c]) {
                visited[r][c - 1] = true;
                q.add(new int[]{r, c - 1});
            }

            if (c + 1 < W && !visited[r][c + 1] && !vcut[r][c + 1]) {
                visited[r][c + 1] = true;
                q.add(new int[]{r, c + 1});
            }
        }
    }

    public static void fill(int sr, int sc) {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{sr, sc});
        visited[sr][sc] = true;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1];
            if (r - 1 >= 0 && !visited[r - 1][c]) {
                visited[r - 1][c] = true;
                q.add(new int[]{r - 1, c});
            }

            if (r + 1 < H && !visited[r + 1][c]) {
                visited[r + 1][c] = true;
                q.add(new int[]{r + 1, c});
            }

            if (c - 1 >= 0 && !visited[r][c - 1]) {
                visited[r][c - 1] = true;
                q.add(new int[]{r, c - 1});
            }

            if (c + 1 < W && !visited[r][c + 1]) {
                visited[r][c + 1] = true;
                q.add(new int[]{r, c + 1});
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String line = br.readLine();
            if (line == null) {
                break;
            }
            line = line.trim();
            if (line.length() == 0) {
                continue;
            }
            int n = Integer.parseInt(line);
            if (n == 0) {
                break;
            }

            int[][] segs = new int[n][4];
            TreeSet<Integer> xset = new TreeSet<>();
            TreeSet<Integer> yset = new TreeSet<>();
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                segs[i][0] = Integer.parseInt(st.nextToken());
                segs[i][1] = Integer.parseInt(st.nextToken());
                segs[i][2] = Integer.parseInt(st.nextToken());
                segs[i][3] = Integer.parseInt(st.nextToken());

                xset.add(segs[i][0]);
                xset.add(segs[i][2]);
                yset.add(segs[i][1]);
                yset.add(segs[i][3]);
            }

            xs = new int[xset.size()];
            ys = new int[yset.size()];
            xmap = new HashMap<>();
            ymap = new HashMap<>();
            int idx = 0;
            for (int v : xset) {
                xs[idx] = v;
                xmap.put(v, idx + 1);
                idx++;
            }

            idx = 0;
            for (int v : yset) {
                ys[idx] = v;
                ymap.put(v, idx + 1);
                idx++;
            }

            W = xs.length + 2;
            H = ys.length + 2;
            hcut = new boolean[H][W + 1];
            vcut = new boolean[H + 1][W];
            for (int i = 0; i < n; i++) {
                int x1 = segs[i][0], y1 = segs[i][1], x2 = segs[i][2], y2 = segs[i][3];
                if (x1 > x2) {
                    int t = x1; x1 = x2; x2 = t;
                }
                if (y1 > y2) {
                    int t = y1; y1 = y2; y2 = t;
                }

                if (y1 == y2) {
                    int cy = compressY(y1);
                    int cx1 = compressX(x1);
                    int cx2 = compressX(x2);
                    for (int c = cx1; c < cx2; c++) {
                        hcut[cy][c] = true;
                    }
                } else {
                    int cx = compressX(x1);
                    int cy1 = compressY(y1);
                    int cy2 = compressY(y2);
                    for (int r = cy1; r < cy2; r++) {
                        vcut[r][cx] = true;
                    }
                }
            }

            visited = new boolean[H][W];
            bfs(0, 0);
            int holes = 0;
            for (int r = 0; r < H; r++) {
                for (int c = 0; c < W; c++) {
                    if (!visited[r][c]) {
                        fill(r, c);
                        holes++;
                    }
                }
            }

            sb.append(holes).append('\n');
        }

        System.out.print(sb.toString());
    }
}
