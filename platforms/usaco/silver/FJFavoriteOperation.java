import java.io.*;
import java.util.*;

public class FJFavoriteOperation {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < t; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int[] a = new int[n];
            int ans = Integer.MAX_VALUE;
            int[] ext = new int[2 * n];
            int[] prefix = new int[2 * n + 1];
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(st.nextToken()) % m;
            }
            Arrays.sort(a);
            for (int i = 0; i < n; i++) {
                ext[i] = a[i];
                ext[i + n] = a[i] + m;
            }

            for (int i = 0; i < 2 * n; i++) {
                prefix[i + 1] = prefix[i] + ext[i];
            }

            for (int i = 0; i < n; i++) {
                ans = Math.min(ans, prefix[i] +  prefix[i + n] - prefix[i + n - n / 2] - prefix[i + n / 2]);
            }

            sb.append(ans).append("\n");
        }
        System.out.print(sb);
        br.close();
    }
}

