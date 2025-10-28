import java.util.*;
import java.io.*;

public class COLCOIN {
    public static void main(String[] args) throws Exception {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int tc = Integer.parseInt(reader.readLine());
            for (int t = 1; t <= tc; t++) {
                int n = Integer.parseInt(reader.readLine());
                StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
                long[] arr = new long[n];
                for (int idx = 0; idx < n; idx++) {
                    arr[idx] = Long.parseLong(st.nextToken());
                }
                Arrays.sort(arr);
                StringBuilder str = new StringBuilder();
                int ans = helper(arr);
                str.append("Case #");
                str.append(t);
                str.append(": ");
                str.append(ans);
                str.append("\n");
                System.out.print(str);
            }
            reader.close();
        } catch (Exception e) {
            return;
        }
    }

    public static int helper(long[] arr) {
        if (arr.length <= 2) {
            return arr.length;
        }

        long toWithdraw = arr[0]; 
        int ans = 1;
        for (int idx = 1; idx < arr.length - 1; idx++) {
            if (toWithdraw + arr[idx] < arr[idx + 1]) {
                toWithdraw += arr[idx];
                ans++;
            }
        }

        return ans + 1;
    }
}

