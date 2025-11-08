import java.util.*;
import java.io.*;

public class JohnActuallyFarms {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
        int tcs = Integer.parseInt(st.nextToken());
        for (int tc = 0; tc < tcs; tc++) {
            st = new StringTokenizer(reader.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());
            int[] h = new int[n];
            int[] a = new int[n];
            int[] t = new int[n];
            st = new StringTokenizer(reader.readLine(), " ");
            for (int idx = 0; idx < n; idx++) {
                h[idx] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(reader.readLine(), " ");
            for (int idx = 0; idx < n; idx++) {
                a[idx] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(reader.readLine(), " ");
            for (int idx = 0; idx < n; idx++) {
                t[idx] = Integer.parseInt(st.nextToken());
            }

            int[][] temp = new int[n][2];
            for (int idx = 0; idx < n; idx++) {
                int[] curr = new int[2];
                curr[0] = idx;
                curr[1] = t[idx];
                temp[idx] = curr;
            }
            Arrays.sort(temp, (x, y) -> Integer.compare(x[1], y[1]));
            int[] h2 = new int[n];
            for (int idx = n - 1; idx >= 0; idx--) {
                h2[n - 1 - idx] = temp[idx][0];
            }

            long ans = 0;
            long[] h3 = new long[n];
            for (int idx = 1; idx < n; idx++) {
                if (h[h2[idx - 1]] >= h[r] && a[r] > a[h2[idx - 1]]) {
                    ans = Math.max(ans, (h[h2[idx - 1]] - h[h2[idx]] + a[h2[idx]] - a[h2[idx - 1]]) / (a[h2[idx]] - a[h2[idx - 1]]));
                }
            }

            for (int idx = 0; idx < n; idx++) {
                h3[idx] = h[idx] + (ans * a[idx]);
            }

            for (int idx = 1; idx < n; idx++) {
                if (ans >= 0) {
                    if (h3[h2[idx - 1]] >= h3[h2[idx]]) {
                        ans = -1;
                    }
                } else {
                    break;
                }
            }
            System.out.println(ans);
        }
        reader.close();
    }
}

