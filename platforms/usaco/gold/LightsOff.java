import java.io.*;
import java.util.*;

public class LightsOff {
    public static final int mxB = 21;
    public static int[][] dp = new int[mxB][1 << mxB];
    public static int[][] cyc = new int[mxB][mxB];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        for (int moves = 1; moves <= N; moves++) {
            int mask = (1 << moves) - 1;
            mask <<= (N - moves);
            cyc[moves][1] = mask;
            for (int pos = 1; pos <= N; pos++) {
                cyc[moves][pos] = mask;
                mask = (mask >> 1) + (mask & 1) * (1 << (N - 1));
            }
        }

        dp[0][0] = 1;
        for (int moves = 1; moves <= N; moves++) {
            for (int mask = 0; mask < (1 << N); mask++) {
                for (int pos = 1; pos <= N; pos++) {
                    if (dp[moves - 1][mask ^ cyc[moves][pos]] == 1) {
                        dp[moves][mask] = 1;
                        break;
                    }
                }
            }
        }

        while (T-- > 0) {
            String lights = br.readLine();
            String switches = br.readLine();
            StringBuilder sb = new StringBuilder(lights);
            lights = sb.reverse().toString();
            sb = new StringBuilder(switches);
            switches = sb.reverse().toString();
            int l = 0, s = 0;
            for (int i = 0; i < N; i++) {
                if (lights.charAt(i) == '1') {
                    l ^= (1 << i);
                }
                if (switches.charAt(i) == '1') {
                    s ^= (1 << i);
                }
            }

            if (l == 0) {
                System.out.println(0);
                continue;
            }
            for (int moves = 1; moves <= N; moves++) {
                l ^= s;
                if (dp[moves][l] == 1) {
                    System.out.println(moves);
                    break;
                }
                s = (s >> 1) + (s & 1) * (1 << (N - 1));
            }
        }
    }
}

