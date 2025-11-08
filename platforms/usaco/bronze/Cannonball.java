import java.util.*;
import java.io.*;

public class Cannonball {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int[][] arr = new int[n][2];
        for (int idx = 0; idx < n; idx++) {
            st = new StringTokenizer(reader.readLine(), " ");
            arr[idx][0] = Integer.parseInt(st.nextToken());
            arr[idx][1] = Integer.parseInt(st.nextToken());
        }

        int t = 0;
        int pos = s - 1;
        int power = 1;
        int x = 1;
        int ans = 0;
        int[] brokenArr = new int[n];

        while (pos >= 0 && pos < n && t < 100 * n) {
            if (arr[pos][0] == 0) {
                power += arr[pos][1];
                x *= -1;
            } else {
                if (power >= arr[pos][1]) {
                    brokenArr[pos] = 1;

                }
            }
            pos += power * x;
            t++;
        }

        for (int idx = 0; idx < n; idx++) {
            ans+= brokenArr[idx];
        }

        System.out.println(ans);
    }
}

