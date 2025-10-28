import java.io.*;
import java.util.*;

public class TheNumberOfPairs {
    public static int[] pArr = new int[(20000000 + 1) * 2];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine().trim());
        ESieve();
        for (int t = 0; t < tc; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            long ans = 0;

            for (long gdiv = 1; gdiv * gdiv <= x; gdiv++) {
                if (x % gdiv == 0) {
                    int divisbyc = (int)(x / gdiv + d);
                    if (divisbyc % c == 0) {
                        ans += (long) Math.pow(2, pArr[(divisbyc / c) * 2 + 1]);
                    }

                    divisbyc = (int)(gdiv + d);
                    if (gdiv * gdiv != x && divisbyc % c == 0) {
                        ans += (long) Math.pow(2, pArr[(divisbyc / c) * 2 + 1]);
                    }
                }
            }
            System.out.println(ans);
        }
    }

    public static void ESieve() {
        for (int i = 0; i <= 20000000; i++) {
            pArr[i * 2] = 1;
            pArr[i * 2 + 1] = 0;
        }

        for (int i = 2; i <= 20000000; i++) {
            if (pArr[i * 2] == 1) {
                for (int x = i; x <= 20000000; x += i) {
                    pArr[x * 2] = i;
                    pArr[x * 2 + 1]++;
                }
            }
        }
    }
}

