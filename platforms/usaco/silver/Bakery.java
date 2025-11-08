import java.io.*;
import java.util.*;

public class Bakery {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            try {
                br.readLine();
                StringTokenizer st = new StringTokenizer(br.readLine());
                int n = Integer.parseInt(st.nextToken());
                long cookieTime = Long.parseLong(st.nextToken());
                long muffinTime = Long.parseLong(st.nextToken());

                long [] cookies = new long[n];
                long[] muffins = new long[n];
                long[] waitTimeMax = new long[n];
                for (int j = 0; j < n; j++) {
                    st = new StringTokenizer(br.readLine());
                    cookies[j] = Long.parseLong(st.nextToken());
                    muffins[j] = Long.parseLong(st.nextToken());
                    waitTimeMax[j] = Long.parseLong(st.nextToken());
                }

                long ans = findMinMoons(n, cookieTime, muffinTime, cookies, muffins, waitTimeMax);
                System.out.println(ans);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format: " + e.getMessage());
            }
        }
    }

    public static long findMinMoons(int n, long cookieTime, long muffinTime, long[] cookies, long[] muffins, long[] waitTimeMax) {
        long low = 0;
        long high = cookieTime + muffinTime - 2;
        while (low < high) {
            long mid = (low + high) / 2;
            if (canSatisfy(mid, n, cookieTime, muffinTime, cookies, muffins, waitTimeMax)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    public static boolean canSatisfy(long moonsSpent, int n, long cookieTime, long muffinTime, long[] cookies, long[] muffins, long[] waitTimeMax) {
        long minCookieTime = Math.max(1, cookieTime - moonsSpent);
        long maxCookieTime = Math.min(cookieTime + muffinTime - moonsSpent - 1, cookieTime);
        for (int i = 0; i < n; i++) {
            long cookieReq = cookies[i];
            long muffinReq = muffins[i];
            long maxWaitTime = waitTimeMax[i];
            long totalTime = cookieTime + muffinTime - moonsSpent;
            if (muffinReq - cookieReq > 0) {
                minCookieTime = Math.max(minCookieTime, (-maxWaitTime + muffinReq * totalTime + (muffinReq - cookieReq - 1)) / (muffinReq - cookieReq));
            } else if (cookieReq - muffinReq > 0) {
                maxCookieTime = Math.min(maxCookieTime, (maxWaitTime - muffinReq * totalTime) / (cookieReq - muffinReq));
            } else {
                if (cookieReq * totalTime > maxWaitTime) {
                    return false;
                }
            }
        }
        return minCookieTime <= maxCookieTime;
    }
}

