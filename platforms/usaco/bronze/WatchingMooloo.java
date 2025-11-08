import java.util.*;
import java.io.*;

public class WatchingMooloo {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        long k = Long.parseLong(st.nextToken());
        long[] arr = new long[n];
        long ans = k + 1;

        st = new StringTokenizer(reader.readLine(), " ");
        for (int idx = 0; idx < n; idx++) {
            arr[idx] = Long.parseLong(st.nextToken());
        }

        for (int idx = 1; idx < n; idx++) {
            ans+= Math.min(k + 1, arr[idx] - arr[idx - 1]);
        }

        System.out.println(ans);
        reader.close();
    }
}

