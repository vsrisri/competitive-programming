import java.io.*;
import java.util.*;

public class ABSP1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < t; tc++) {
            int n = Integer.parseInt(br.readLine());
            long[] a = new long[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            long ans = 0;
            for (int i = 0; i < n; i++) {
                a[i] = Long.parseLong(st.nextToken());
            }

            for (int i = 0; i < n; i++) {
                ans += (a[i] * i) - a[i] * (n - i - 1);
            }
            sb.append(ans).append("\n");
        }
        System.out.print(sb);
        br.close();
    }
}

