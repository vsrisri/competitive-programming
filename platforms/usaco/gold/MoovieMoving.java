import java.util.*;
import java.io.*;

public class MoovieMoving {
    public static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int dur = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            int[] sTimes = new int[C];
            for (int j = 0; j < C; j++) {
                sTimes[j] = Integer.parseInt(st.nextToken());
            }
            movies.add(new Movie(dur, sTimes));
        }

        int[] dp = new int[1 << N];
        Arrays.fill(dp, -1);
        dp[0] = 0;  

        for (int mask = 0; mask < (1 << N); mask++) {
            if (dp[mask] == -1) {
                continue;
            }

            for (int i = 0; i < N; i++) {
                if ((mask & (1 << i)) != 0) {
                    continue;
                }

                Movie movie = movies.get(i);
                int[] sTimes = movie.sTimes;
                int lastWatchedTime = dp[mask];
                int newTime = -1;
                for (int t : sTimes) {
                    if (t >= lastWatchedTime) {
                        newTime = t + movie.dur;
                    }
                }
                
                if (newTime != -1 && newTime <= L) {
                    dp[mask | (1 << i)] = Math.max(dp[mask | (1 << i)], newTime);
                }
            }
        }

        int ans = INF;
        for (int mask = 0; mask < (1 << N); mask++) {
            if (dp[mask] >= L) {
                ans = Math.min(ans, Integer.bitCount(mask));
            }
        }

        if (ans == INF) {
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }
    }

    public static class Movie {
        int dur;
        int[] sTimes;

        Movie(int dur, int[] sTimes) {
            this.dur = dur;
            this.sTimes = sTimes;
        }
    }
}

