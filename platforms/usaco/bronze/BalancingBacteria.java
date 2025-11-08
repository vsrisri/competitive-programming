import java.util.*;
import java.io.*;

public class BalancingBacteria {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        long[] arr = new long[n];
        st = new StringTokenizer(reader.readLine(), " ");
        for (int idx = 0; idx < n; idx++) {
            arr[idx] = Long.parseLong(st.nextToken());
        }

        long ans = 0;
        long sum1 = 0;
        long sum2 = 0;
        for (int idx = 0; idx < n; idx++) {
            arr[idx] += sum2;
            sum1 -= arr[idx];
            sum2 += sum1 - arr[idx];
            ans+= Math.abs(arr[idx]);
        }

        System.out.println(ans);
        reader.close();
    }
}

