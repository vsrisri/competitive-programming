import java.util.*;
import java.io.*;

public class BalticOI11p6 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        long[] nums = new long[n];
        st = new StringTokenizer(reader.readLine(), " ");
        for (int idx = 0; idx < n; idx++) {
            nums[idx] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(nums); 
        long ans = 0;
        for (int idx = 0; idx < n; idx++) {
            int minFileSize = (int) (1 + (9 * nums[idx] - 1) / 10);
            int low = 0;
            int high = n;
            int mid = 0;
            while (low < high) {
                mid = low + (high - low) / 2;
                if (minFileSize <= nums[mid]) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }

            if (low < nums.length && nums[low] < minFileSize) {
                low++;
            }
            ans += (idx - low);
        }

        System.out.println(ans);
        reader.close();
    }
}

