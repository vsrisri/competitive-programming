import java.util.*;
import java.io.*;

public class GuardMark {
    static long[] dp = new long[1 << 20];
    static long[] heighArr = new long[1 << 20];
    static Cow[] cowArr = new Cow[20];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("guard.in"));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("guard.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int numCows = Integer.parseInt(st.nextToken());
        long markHeight = Long.parseLong(st.nextToken());
        
        for (int i = 0; i < numCows; i++) {
            st = new StringTokenizer(br.readLine());
            long h = Long.parseLong(st.nextToken());
            long w = Long.parseLong(st.nextToken());
            long s = Long.parseLong(st.nextToken());
            cowArr[i] = new Cow(h, w, s);
        }

        Arrays.fill(dp, -1);
        dp[0] = Long.MAX_VALUE;
        long ans = -1;
        for (int mask = 1; mask < (1 << numCows); mask++) {
            for (int x = 0; x < numCows; x++) {
                if ((mask & (1 << x)) == 0) {
                    continue; 
                }

                int p = mask - (1 << x); 
                heighArr[mask] += cowArr[x].h; 
                dp[mask] = Math.max(dp[mask], Math.min(dp[p] - cowArr[x].w, cowArr[x].s)); 
            }

            if (heighArr[mask] >= markHeight) {
                ans = Math.max(ans, dp[mask]);
            }
        }

        if (ans < 0) {
            writer.println("Mark is too tall");
        } else {
            writer.println(ans);
        }
        br.close();
        writer.close();

    }

    public static class Cow {
        public long h;
        public long w;
        public long s;

        public Cow(long h, long w, long s) {
            this.h = h;
            this.w = w;
            this.s = s;
        }
    }

}
