import java.util.*;
import java.io.*;

public class CakeGame {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int tc = Integer.parseInt(tokenizer.nextToken());
        for (int t = 0; t < tc; t++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(tokenizer.nextToken());
            long ans = 0;
            long[] arr = new long[n + 1];
            long allCakes = 0;
            long[] prArr = new long[n + 1];
            long[] suArr = new long[n + 2];
            tokenizer = new StringTokenizer(reader.readLine());
            for (int idx = 1; idx <= n; idx++) {
                arr[idx] = Long.parseLong(tokenizer.nextToken());
                allCakes += arr[idx];
            }

            int curr = 0;
            for (int idx = 1; idx <= n; idx++) {
                curr += arr[idx]; 
                prArr[idx] = curr;

            }

            for (int idx = n; idx > 0; idx--) {
                suArr[idx] = suArr[idx + 1] + arr[idx];
            }

            prArr[0] = 0;
            suArr[n + 1] = 0;
            ans = findMax(prArr, suArr, n);
            long rem = allCakes - ans;

            System.out.println(rem + " " + ans);
        }
        reader.close();
    }

    public static long findMax(long[] prArr, long[] suArr, int n) {
        long maxSum = 0;
        for (int idx = 0; idx < n/2; idx++) {
            long lsum = prArr[idx];
            long rsum = suArr[n/2 + idx + 2];
            maxSum = Math.max(maxSum, lsum + rsum);
        }

        return maxSum;
    }
}
