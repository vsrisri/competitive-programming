import java.io.*;
import java.util.*;

public class BSHEEP {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int t = Integer.parseInt(line.trim());
        for (int tc = 0; tc < t; tc++) {
            br.readLine();
            int n = Integer.parseInt(br.readLine().trim());
            Map<String, Point> map = new HashMap<>();
            for (int i = 1; i <= n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                String key = x + "," + y;
                if (!map.containsKey(key)) {
                    map.put(key, new Point(x, y, i));
                }
            }

            List<Point> pArr = new ArrayList<>(map.values());
            if (pArr.size() <= 1) {
                System.out.printf("0.00%n");
                if (pArr.size() == 1) {
                    System.out.println(pArr.get(0).idx);
                } else {
                    System.out.println();
                } if (tc != t - 1) {
                    System.out.println();
                }
                continue;
            }

            List<Point> hull = cHull(pArr);
            rotate(hull);
            double perim = 0;
            for (int i = 0; i < hull.size(); i++) {
                perim += dist(hull.get(i), hull.get((i + 1) % hull.size()));
            }

            System.out.printf("%.2f%n", perim);
            for (int i = 0; i < hull.size(); i++) {
                if (i > 0) {
                    System.out.print(" ");
                }
                System.out.print(hull.get(i).idx);
            }

            System.out.println();
            if (tc != t - 1) {
                System.out.println();
            }
        }
    }

    public static class Point {
        int x, y, idx;
        Point(int x, int y, int idx) {
            this.x = x;
            this.y = y;
            this.idx = idx;
        }
    }

    public static long cross(Point o, Point a, Point b) {
        return (long) (a.x - o.x) * (b.y - o.y) - (long) (a.y - o.y) * (b.x - o.x);
    }

    public static double dist(Point a, Point b) {
        long dx = a.x - b.x;
        long dy = a.y - b.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static List<Point> cHull(List<Point> pArr) {
        if (pArr.size() <= 1) {
            return pArr;
        }

        pArr.sort((a, b) -> {
            if (a.x != b.x) {
                return a.x - b.x;
            }
            return a.y - b.y;
        });

        List<Point> lower = new ArrayList<>();
        for (Point p : pArr) {
            while (lower.size() >= 2 && cross(lower.get(lower.size() - 2), lower.get(lower.size() - 1), p) <= 0) {
                lower.remove(lower.size() - 1);
            }
            lower.add(p);
        }

        List<Point> upper = new ArrayList<>();
        for (int i = pArr.size() - 1; i >= 0; i--) {
            Point p = pArr.get(i);
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

    public static void rotate(List<Point> hull) {
        int n = hull.size();
        int ans = 0;
        for (int i = 1; i < n; i++) {
            if (hull.get(i).y < hull.get(ans).y || (hull.get(i).y == hull.get(ans).y && hull.get(i).x < hull.get(ans).x)) {
                ans = i;
            }
        }
        Collections.rotate(hull, -ans);
    }
}
