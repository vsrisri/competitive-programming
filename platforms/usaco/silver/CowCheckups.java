import java.util.*;
import java.io.*;

public class CowCheckups {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        long[] a = new long[(int) n];
        long[] b = new long[(int) n];
        ArrayList<ArrayList<Long>> v = new ArrayList<>();
        ArrayList<ArrayList<Long>> pref = new ArrayList<>();

        for (long i = 0; i < n; i++) {
            a[(int) i] = sc.nextLong();
        }

        for (long i = 0; i < n; i++) {
            b[(int) i] = sc.nextLong();
        }

        for (int i = 0; i <= n; i++) {
            v.add(new ArrayList<>());
        }

        for (long i = 0; i < n; i++) {
            long d = Math.min(i, n - 1 - i);
            v.get((int) b[(int) i]).add(d); 
        }

        for (int i = 0; i <= n; i++) {
            Collections.sort(v.get(i)); 
        }

        for (int s = 0; s <= n; s++) {
            pref.add(new ArrayList<>());
            pref.get(s).add(0L);
        }

        for (int s = 0; s <= n; s++) {
            for (int k = 0; k < v.get(s).size(); k++) {
                pref.get(s).add(pref.get(s).get(k) + v.get(s).get(k));
            }
        }

        long ans = 0;
        for (long i = 0; i < n; i++) {
            ans = ans + v.get((int) a[(int) i]).size(); 
            long md = Math.min(i, n - 1 - i);
            ArrayList<Long> xArr = v.get((int) a[(int) i]);
            ArrayList<Long> pArr = pref.get((int) a[(int) i]);
            int idx = Collections.binarySearch(xArr, md);
            if (idx < 0) {
                idx = -(idx + 1);
            }

            long sumBelow = pArr.get(idx);
            long countAbove = (long) xArr.size() - idx;
            ans += sumBelow + countAbove * md;
            if (a[(int) i] == b[(int) i]) {
                long d1 = i;
                long d2 = n - 1 - i;
                ans += ((d1 * (d1 - 1)) / 2) + d1;
                ans += ((d2 * (d2 - 1)) / 2) + d2;
            }
        }

        System.out.println(ans);
    }
}

