import java.util.*;
import java.io.*;

public class KittanDilRedo {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int ans = Integer.MIN_VALUE;
        ArrayList<Long> good = new ArrayList<Long>();
        ArrayList<Long> bad = new ArrayList<Long>();

        for (int idx = 0; idx < n; idx++) {
            st = new StringTokenizer(reader.readLine(), " ");
            long s = Long.parseLong(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            if (p == 1) {
                bad.add(s);
            } else {
                good.add(s);
            }
        }
        Collections.sort(good);
        Collections.sort(bad);
        ArrayList<Long> pGood = new ArrayList<Long>();
        pGood.add((long) 0);
        ArrayList<Long> pBad = new ArrayList<Long>();
        pBad.add((long) 0);

        for (int idx = 0; idx < good.size(); idx++) {
            pGood.add(pGood.get(idx) + good.get(idx));
        }

        for (int idx = 0; idx < bad.size(); idx++) {
            pBad.add(pBad.get(idx) + bad.get(idx));
        }

        for (int idx = 0; idx < pGood.size(); idx++) {
            long key = m - pGood.get(idx);
            int b = binSearch(pBad, key);
            if (b == 0) {
                ans = Math.max(ans, idx * 2);
            } else {
                ans = Math.max(ans, (idx * 2) + b);
            }
        }

        if (ans > 0) {
            System.out.println(ans);
        } else {
            System.out.println(0);
        }

        reader.close();
    }

    // Returns the index of the greatest value in p less than or equal to num
    public static int binSearch(ArrayList<Long> p, Long num) {
        int low = 0;
        int high = p.size() - 1;

        while (low <= high) {
            int mid = low + (high - low + 1) / 2;

            if (p.get(mid) <= num) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return high;
    }
}
