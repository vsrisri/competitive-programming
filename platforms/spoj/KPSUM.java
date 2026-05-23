import java.util.*;
import java.io.*;

public class KPSUM {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLong()) {
            long n = sc.nextLong();
            if (n == 0) {
                break;
            }
            System.out.println(solve(n));
        }
    }

    public static long solve(long n) {
        long ans = 0;
        int d = 1;
        long L = 1;
        while (L <= n) {
            long R = Math.min(pow10(d) - 1, n);
            long posL = 1 + tDig(L - 1);
            long baseSign = (posL % 2 == 1) ? 1 : -1;
            if (d % 2 == 0) {
                long s = 0;
                for (int j = 0; j < d; j++) {
                    long digitSum = sumD1(L, R, j, d);
                    s += ((j % 2 == 0) ? 1 : -1) * digitSum;
                }
                ans += baseSign * s;
            } else {
                long s = 0;
                for (int j = 0; j < d; j++) {
                    long bVal = sumD2(L, R, j, d) - sumD2(L + 1, R, j, d);
                    s += ((j % 2 == 0) ? 1 : -1) * bVal;
                }
                ans += baseSign * s;
            }
            d++;
            L = pow10(d - 1);
        }
        return ans;
    }

    public static long sumD1(long L, long R, int j, int d) {
        long div = pow10(d - 1 - j);
        long mod = div * 10;
        long M = R - L;
        return floorSum(L, 1, div, M) - 10 * floorSum(L, 1, mod, M);
    }

    public static long sumD2(long start, long endVal, int j, int d) {
        if (start > endVal) {
            return 0;
        }
        long div = pow10(d - 1 - j);
        long mod = div * 10;
        long M = (endVal - start) / 2;
        return floorSum(start, 2, div, M) - 10 * floorSum(start, 2, mod, M);
    }

    public static long floorSum(long a, long b, long c, long m) {
        if (c == 0 || m < 0) {
            return 0;
        }
        long ans = (a / c) * (m + 1) + (b / c) * (m / 2) * (m + 1);
        if (m % 2 == 1) {
            ans += (b / c) * ((m + 1) / 2);
        }
        a %= c;
        b %= c;
        if (a == 0 && b == 0) {
            return ans;
        }
        long yMax = (a + b * m) / c;
        if (yMax == 0) {
            return ans;
        }
        ans += yMax * m - floorSum(c - a - 1, c, b, yMax - 1);
        return ans;
    }

    static long tDig(long n) {
        if (n <= 0) {
            return 0;
        }
        long count = 0;
        int d = 1;
        long start = 1;
        while (start <= n) {
            long end = Math.min(pow10(d) - 1, n);
            count += (long) d * (end - start + 1);
            d++;
            start = pow10(d - 1);
        }
        return count;
    }

    public static long pow10(int e) {
        long ans = 1;
        for (int i = 0; i < e; i++) {
            ans *= 10;
        }
        return ans;
    }
}
