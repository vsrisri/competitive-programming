import java.io.*;
import java.util.*;

public class BalanceBeam {
    public static class Point {
        int x;
        long y;

        Point(int x, long y) {
            this.x = x;
            this.y = y;
        }

        public static double slope(Point a, Point b) {
            return (double)(b.y - a.y) / (b.x - a.x);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("balance.in"));
        int n = Integer.parseInt(br.readLine());
        long[] rewards = new long[n + 2];
        for (int i = 1; i <= n; i++) {
            rewards[i] = Long.parseLong(br.readLine());
        }

        Deque<Point> hull = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            Point p = new Point(i, rewards[i]);
            while (hull.size() >= 2) {
                Point b = hull.removeLast();
                Point a = hull.peekLast();
                if (Point.slope(a, b) >= Point.slope(b, p)) {
                    continue;
                }
                hull.addLast(b);
                break;
            }
            hull.addLast(p);
        }

        long[] ans = new long[n + 1];
        Point[] pts = hull.toArray(new Point[0]);
        int idx = 0;
        for (int i = 1; i <= n; i++) {
            while (idx + 1 < pts.length && pts[idx + 1].x <= i) {
                idx++;
            }
            int l = pts[idx].x;
            int r = pts[Math.min(idx + 1, pts.length - 1)].x;
            long ly = pts[idx].y;
            long ry = pts[Math.min(idx + 1, pts.length - 1)].y;
            double val;
            if (l == r) {
                val = ly;
            } else {
                val = ly + (double)(ry - ly) * (i - l) / (r - l);
            }
            ans[i] = Math.round(val * 100000);
        }

        PrintWriter pw = new PrintWriter("balance.out");
        for (int i = 1; i <= n; i++) {
            pw.println(ans[i]);
        }
        pw.close();
    }
}

