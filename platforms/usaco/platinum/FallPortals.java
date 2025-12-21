import java.io.*;
import java.util.*;

public class FallPortals {
    static Pair[] aArr = new Pair[200000];
    static Pair[] qArr = new Pair[200000];
    static Pair[] result = new Pair[200000];
    static int[] order = new int[200000];
    public static void main(String[] args) throws Exception {
        BufferedReader rd = new BufferedReader(new FileReader("falling.in"));
        BufferedWriter wr = new BufferedWriter(new FileWriter("falling.out"));
        int n = Integer.parseInt(rd.readLine());
        for (int i = 0; i < n; i++) {
            long val = Long.parseLong(rd.readLine());
            aArr[i] = new Pair(val, -(i + 1));
            qArr[i] = aArr[i];
        }

        String[] line = rd.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            order[i] = Integer.parseInt(line[i]) - 1;
        }

        Arrays.sort(qArr, 0, n, (p1, p2) -> {
            if (p1.x != p2.x) {
                return Long.compare(p2.x, p1.x);
            }
            return Long.compare(p2.y, p1.y);
        });

        helper(n);

        for (int i = 0; i < n; i++) {
            aArr[i] = new Pair(-aArr[i].x, -aArr[i].y);
            qArr[i] = new Pair(-qArr[i].x, -qArr[i].y);
        }

        Collections.reverse(Arrays.asList(qArr).subList(0, n));
        helper(n);

        for (int i = 0; i < n; i++) {
            if (result[i].x == -1) {
                wr.write("-1\n");
            } else {
                wr.write(result[i].x + "/" + result[i].y + "\n");
            }
        }

        rd.close();
        wr.close();
    }

    public static void helper(int len) {
        Deque<Line> buf = new ArrayDeque<>();
        for (int i = 0; i < len; i++) {
            Line l = new Line(qArr[i].y, qArr[i].x);
            while ((buf.size() >= 2 && buf.get(1).isBetter(l, buf.get(0))) || (!buf.isEmpty() && l.slope < buf.getFirst().slope)) {
                buf.removeFirst();
            }

            buf.addFirst(l);
            int idx = Math.abs((int) qArr[i].y) - 1;
            if (aArr[order[idx]].x < qArr[i].x) {
                Line g = new Line(aArr[order[idx]].y, aArr[order[idx]].x);
                if (g.slope < buf.getLast().slope) {
                    result[idx] = new Pair(-1, -1);
                } else {
                    int left = 0, right = buf.size() - 1;
                    List<Line> list = new ArrayList<>(buf);
                    while (left < right) {
                        int mid = (left + right) / 2;
                        if (g.isBetter(list.get(mid), list.get(mid + 1))) {
                            left = mid + 1;
                        } else {
                            right = mid;
                        }
                    }

                    long num = Math.abs(list.get(left).intercept - g.intercept);
                    long den = Math.abs(g.slope - list.get(left).slope);
                    long d = gcd(num, den);
                    result[idx] = new Pair(num / d, den / d);
                }
            }
        }
    }

    public static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static class Pair {
        long x, y;
        Pair(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    public static class Line {
        long slope, intercept;
        Line(long m, long c) {
            slope = m;
            intercept = c;
        }

        boolean isBetter(Line g, Line h) {
            return (h.intercept - intercept) * (slope - g.slope) < (g.intercept - intercept) * (slope - h.slope);
        }
    }
}

