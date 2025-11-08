// USACO 2016 January Contest, Gold
//Problem 1. Angry Cows
Contest has ended.
import java.util.*;
import java.io.*;

public class AngryCowsGold {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(new File("angry.in"));
        PrintWriter out = new PrintWriter(new File("angry.out"));
        int n = stdin.nextInt();
        long[] vals = new long[n];
        for (int i = 0; i < n; i++) {
            vals[i] = (long) stdin.nextInt();
        }

        Arrays.sort(vals);
        long[] reverseList = new long[n];
        for (int i = 0; i < n; i++) {
            reverseList[i] = vals[n - 1 - i];
        }

        double low = vals[0], high = vals[n - 1];
        double res = vals[n - 1] - vals[0];
        while (high - low > .001) {
            double mid = (low + high) / 2;
            int lowIdx = getLowIdx(vals, mid);
            double left = binSearch(vals, mid, lowIdx);
            double right = binSearch(reverseList, mid, n - 2 - lowIdx);
            res = Math.min(Math.max(left,right), res);
            if (left < right) {
                low = mid;
            } else {
                high = mid;
            }

        }

        long newRes = (long)(100 * res);
        long whole = (long) res;
        int tens = (int) (newRes % 100 - newRes % 10) / 10;
        if (newRes % 10 >= 5) {
            tens++;
        }
        if (tens == 10) {
            whole = whole + 1;
            tens = 0;
        }

        out.println( (newRes / 100) + "." + tens);
        out.close();
        stdin.close();
    }

    public static int getLowIdx(long[] vals, double x) {
        int i = 0;
        while (i < vals.length && vals[i] < x) {
            i++;
        }
        return i - 1;
    }

    public static double binSearch(long[] arr, double value, int index) {
        double low = .5, high = Math.abs(arr[0] - value);
        while (high - low > .001) {
            double mid = (low + high) / 2;
            double saveMid = mid, curr = value;
            int i = index;

            while (i > 0) {
                int j = i;
                while (j >= 0 && Math.abs(arr[j] - curr) <= mid+1e-9) {
                    j--;
                }
                if (j == i) {
                    break;
                }

                i = j + 1;
                mid = mid - 1;
                curr = arr[i];
                i--;
            }

            if (i <= 0) {
                high = saveMid;
            } else {
                low = saveMid;
            }
        }
        return high;
    }

}

