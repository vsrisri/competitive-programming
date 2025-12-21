import java.io.*;
import java.util.*;

public class CowCurlingGold {
    public static boolean isInside(List<Point> hull, Point p) {
        int n = hull.size();
        if (n < 3) {
            return false;
        }
        if (cross(hull.get(0), hull.get(1), p) < 0) {
            return false;
        }
        if (cross(hull.get(0), hull.get(n - 1), p) > 0) {
            return false;
        }
        int lo = 1;
        int hi = n - 1;
        while (lo + 1 < hi) {
            int mid = (lo + hi) / 2;
            if (cross(hull.get(0), hull.get(mid), p) > 0) {
                lo = mid;
            } else {
                hi = mid;
            }
        }
        return cross(hull.get(lo), hull.get(hi), p) >= 0;
    }

    public static List<Point> convexHull(Point[] points) {
        Arrays.sort(points);
        int n = points.length;
        List<Point> lower = new ArrayList<>();
        for (Point p : points) {
            while (lower.size() >= 2 && cross(lower.get(lower.size() - 2), lower.get(lower.size() - 1), p) <= 0) {
                lower.remove(lower.size() - 1);
            }
            lower.add(p);
        }
        List<Point> upper = new ArrayList<>();
        for (int i = n - 1; i >= 0; i--) {
            Point p = points[i];
            while (upper.size() >= 2 && cross(upper.get(upper.size() - 2), upper.get(upper.size() - 1), p) <= 0) {
                upper.remove(upper.size() - 1);
            }
            upper.add(p);
        }
        lower.remove(lower.size() - 1);
        upper.remove(upper.size() - 1);
        lower.addAll(upper);
        return lower;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("curling.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("curling.out")));
        int N = Integer.parseInt(br.readLine());
        Point[] teamA = new Point[N];
        Point[] teamB = new Point[N];
        for (int i = 0; i < N; i++) {
            String[] parts = br.readLine().split(" ");
            teamA[i] = new Point(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
        }
        for (int i = 0; i < N; i++) {
            String[] parts = br.readLine().split(" ");
            teamB[i] = new Point(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
        }
        List<Point> hullA = convexHull(teamA);
        List<Point> hullB = convexHull(teamB);
        int scoreA = 0;
        int scoreB = 0;
        for (Point p : teamB) {
            if (isInside(hullA, p)) {
                scoreA++;
            }
        }
        for (Point p : teamA) {
            if (isInside(hullB, p)) {
                scoreB++;
            }
        }
        pw.println(scoreA + " " + scoreB);
        pw.close();
    }

    public static class Point implements Comparable<Point> {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = this.y = y;
        }

        public int compareTo(Point o) {
            if (this.x != o.x) {
                return Integer.compare(this.x, o.x);
            }
            return Integer.compare(this.y, o.y);
        }
    }

    public static long cross(Point a, Point b, Point c) {
        long x1 = b.x - a.x;
        long y1 = b.y - a.y;
        long x2 = c.x - a.x;
        long y2 = c.y - a.y;
        return x1 * y2 - x2 * y1;
    }
}

