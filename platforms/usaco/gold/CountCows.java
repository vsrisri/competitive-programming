import java.io.*;
import java.util.*;

public class CountCows {
    public static long count(long x, long len) {
        if (len == 1) {
            return 1;
        }
        if (x < len / 3) {
            return count(x, len / 3) * 3;
        }
        x -= len / 3;
        if (x == 0) {
            return 0;
        }
        x--;
        if (x < len / 3) {
            return count(len / 3 - x - 1, len / 3);
        }
        return count(x - len / 3 + 1, len / 3);
    }

    public static long solveRec(long x, long y, long len) {
        if (len == 1) {
            return 1;
        }
        len /= 3;
        int gx = (int)(x / len);
        int gy = (int)(y / len);
        x %= len;
        y %= len;

        if (gx > gy) {
            int tmp = gx; gx = gy; gy = tmp;
            long tmp2 = x; x = y; y = tmp2;
        }

        long res = 0;
        if (gx == 0) {
            if (gy == 1) {
                if (x > y) {
                    res = count(len - x + y, len);
                }
            }
        }

        if (gx == 1) {
            if (gy == 2) {
                if (x > y) {
                    res = 2 * count(len - x + y, len);
                }
                else if (x != y) {
                    res = count(len - y + x, len);
                }
            }
        }

        if (gx == gy && gx > 0) {
            res += gx * count(Math.abs(x - y), len);
        }
        if (gx % 2 == gy % 2) {
            res += solveRec(x, y, len);
        }

        return res;
    }

    public static long solve(long x, long y) {
        long len = 1;
        while (len <= Math.max(x, y)) {
            len *= 3;
        }
        return solveRec(x, y, len);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int q = Integer.parseInt(in.readLine());
        while (q-- > 0) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            long d = Long.parseLong(st.nextToken());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());
            long ans = solve(x + d, y + d);
            if (x > 0 && y > 0) {
                ans -= solve(x - 1, y - 1);
            }
            System.out.println(ans);
        }
    }
}

