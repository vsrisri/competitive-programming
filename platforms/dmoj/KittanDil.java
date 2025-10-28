import java.util.*;
import java.io.*;

public class KittanDil {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        ArrayList<Long> good = new ArrayList<Long>();
        ArrayList<Long> bad = new ArrayList<Long>();

        for (int idx = 0; idx < n; idx++) {
            st = new StringTokenizer(reader.readLine(), " ");
            Long sp = Long.parseLong(st.nextToken());
            int prot = Integer.parseInt(st.nextToken());
            if (prot == 1) {
                bad.add(sp);
            } else {
                good.add(sp);
            }
        }

        Collections.sort(good);
        Collections.sort(bad);
        ArrayList<Long> prefixGood = new ArrayList<Long>();
        ArrayList<Long> prefixBad = new ArrayList<Long>();
        prefixGood.add(Long.valueOf(0));
        prefixBad.add(Long.valueOf(0));
        if (good.size() > 0) {
            prefixGood.add(good.get(0));
        }
        if (bad.size() > 0) {
            prefixBad.add(bad.get(0));
        }

        for (int idx = 1; idx < good.size(); idx++) {
            prefixGood.add(prefixGood.get(idx) + good.get(idx));
        }

        for (int idx = 1; idx < bad.size(); idx++) {
            prefixBad.add(prefixBad.get(idx) + bad.get(idx));
        }

        int ans = -9999999;
        for (int idx = 0; idx < prefixGood.size(); idx++) {
            Long num = m - prefixGood.get(idx);
            if (num > 0) {
                int binIdx = binarySearch(prefixBad, num);
                int currAns =  binIdx + idx * 2;
                ans = Math.max(ans, currAns);
            } else if (num == 0) {
                ans = Math.max(ans, idx * 2);
            } else {
                continue;
            }
        }

        if (ans == -9999999) {
            System.out.println(0);
        } else {
            System.out.println(ans);
        }
        reader.close();
    }

    // Returns the index of the maximum value less than or equal to num
    public static int binarySearch(ArrayList<Long> prefix, Long num) {
        int left = 0;
        int right = prefix.size() - 1;

        while (left <= right) {
            int mid = left + (right - left + 1) / 2;

            if (prefix.get(mid) <= num) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return right;
    }
}
