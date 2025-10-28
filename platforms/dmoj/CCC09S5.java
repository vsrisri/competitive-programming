import java.util.*;
import java.io.*;

public class CCC09S5 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int m = Integer.parseInt(reader.readLine());
        int n = Integer.parseInt(reader.readLine());
        int k = Integer.parseInt(reader.readLine());
        int[][] arr = new int[m + 3][n + 3];

        for (int idx = 0; idx < k; idx++) {
            StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int bit = Integer.parseInt(st.nextToken());

            for (int col = Math.max(1, x - r); col <= Math.min(x + r, n); col++) {
                int dist = (int) (Math.sqrt(sq(r) - sq(x - col)));
                arr[Math.max(1, y - dist)][col] += bit;
                arr[Math.min(m, y + dist) + 1][col] -= bit;
            }
        }

        int max = Integer.MIN_VALUE;
        int count = 0;

        for (int idx = 1; idx <= m; idx++) {
            for (int idx2 = 1; idx2 <= n; idx2++) {
                arr[idx][idx2] += arr[idx - 1][idx2];
                if (arr[idx][idx2] > max) {
                    max = arr[idx][idx2];
                    count = 1;
                } else if (arr[idx][idx2] == max) {
                    count++;
                }
            }
        }

        System.out.println(max);
        System.out.println(count);
        reader.close();
    }

    public static int sq(int i) {
        return i * i;
    }
}
