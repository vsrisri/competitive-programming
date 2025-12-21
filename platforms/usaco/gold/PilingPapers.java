import java.io.*;
import java.util.*;

public class PilingPapers {
    static final int MOD = 1000000007;
    static int N, Q, Al, Bl;
    static long[][] L = new long[302][302], R = new long[302][302];
    static char[] A = new char[20], B = new char[20], P = new char[302];
    public static void helper2(long[][] DP, int pileLength, char[] target, int targetLength) {
        for (int i = 1; i <= pileLength; i++) {
            long[][][] dp = new long[targetLength + 1][targetLength + 1][3];
            for (int x = 0; x <= targetLength; x++) {
                for (int y = 0; y <= targetLength; y++) {
                    for (int z = 0; z <= 2; z++) {
                        dp[x][y][z] = 0;
                    }
                }
            }

            for (int j = i; j <= pileLength; j++) {
                for (int x = 1; x <= targetLength; x++) {
                    for (int y = targetLength; x < y; y--) {
                        if (P[j] > target[x]) {
                            for (int k = 0; k <= 2; k++) {
                                dp[x][y][2] += dp[x + 1][y][k];
                            }
                        } else if (P[j] == target[x]) {
                            for (int k = 0; k <= 2; k++) {
                                dp[x][y][k] += dp[x + 1][y][k];
                            }
                        } else {
                            for (int k = 0; k <= 2; k++) {
                                dp[x][y][0] += dp[x + 1][y][k];
                            }
                        }

                        dp[x][y][0] += dp[x][y - 1][0];
                        dp[x][y][helper(P[j], target[y])] += dp[x][y - 1][1];
                        dp[x][y][2] += dp[x][y - 1][2];

                        for (int k = 0; k <= 2; k++) {
                            dp[x][y][k] %= MOD;
                        }
                    }
                }

                for (int x = 1; x <= targetLength; x++) {
                    dp[x][x][helper(P[j], target[x])] += 2;
                }

                for (int x = 1; x <= targetLength; x++) {
                    DP[i][j] += dp[x][targetLength][0];
                    DP[i][j] += dp[x][targetLength][1];
                    if (x != 1) {
                        DP[i][j] += dp[x][targetLength][2];
                    }
                    DP[i][j] %= MOD;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        Al = Long.toString(a - 1).length();
        Bl = Long.toString(b).length();
        System.arraycopy((' ' + Long.toString(a - 1)).toCharArray(), 0, A, 0, Al);
        if (a == 0) {
            A[1] = '0';
        }
        System.arraycopy((' ' + Long.toString(b)).toCharArray(), 0, B, 0, Bl);
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            P[i] = (char) (Integer.parseInt(st.nextToken()) + '0');
        }

        helper2(L, N, A, Al);
        helper2(R, N, B, Bl);
        Q = Integer.parseInt(br.readLine());
        for (int i = 1; i <= Q; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            long ans = R[l][r] - L[l][r];
            ans += MOD;
            ans %= MOD;
            System.out.println(ans);
        }
    }

    public static int helper(char a, char b) {
        if (a > b) {
            return 2;
        }
        return (a == b) ? 1 : 0;
    }
}
