import java.io.*;
import java.util.*;

public class WorldDomFun {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        long k = Long.parseLong(st.nextToken());
        long[] arr = new long[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        long ans = findMaxStrength(n, m, k, arr);
        System.out.println(ans);
        br.close();
    }

    public static long findMaxStrength(int n, int m, long k, long[] arr) {
        long low = Arrays.stream(arr).min().orElse(0);
        long high = 2000000000;
        long ans = 0;

        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (isPoss(arr, n, m, k, mid)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }


    public static boolean isPoss(long[] arr, int n, int m, long k, long target) {
        long[] hdiffArr = new long[n];
        long curr = 0;
        long totalSnow = 0;

        for (int idx = 0; idx < n; idx++) {
            curr += hdiffArr[idx];
            if (arr[idx] + curr < target) {
                long currSnow = (target - arr[idx] - curr);
                totalSnow += currSnow; curr += currSnow;
                if (idx + m < n) {
                    hdiffArr[idx + m] -= currSnow;
                }
            }
            if (totalSnow > k) {
                return false;
            }
        }
        return totalSnow <= k;
    }
}

