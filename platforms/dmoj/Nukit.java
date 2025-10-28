import java.util.*;
import java.io.*;

public class Nukit {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
        int t = Integer.parseInt(st.nextToken());
        int[][] reactions = {{2, 1, 0, 2}, {1, 1, 1, 1}, {0, 0, 2, 1}, {0, 3, 0, 0}, {1, 0, 0, 1}};
        boolean[][][][] dp = new boolean[31][31][31][31];
        for (int idx1 = 0; idx1 < 31; idx1++) {
            for (int idx2 = 0; idx2 < 31; idx2++) {
                for (int idx3 = 0; idx3 < 31; idx3++) {
                    for (int idx4 = 0; idx4 < 31; idx4++) {
                        for (int i = 0; i < reactions.length; i++) {
                            int[] curr = reactions[i];
                            if (helper(idx1 - curr[0], idx2 - curr[1], idx3 - curr[2], idx4 - curr[3], dp)) {
                                dp[idx1][idx2][idx3][idx4] = true;
                            }
                        }
                    }
                }
            }
        }

        for (int tc = 0; tc < t; tc++) {
            st = new StringTokenizer(reader.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            if (dp[a][b][c][d]) {
                System.out.println("Patrick");
            } else {
                System.out.println("Roland");
            }
        }
        reader.close();
    }

    public static boolean helper(int a, int b, int c, int d, boolean[][][][] dp) {
        int ans = 0;
        if (a < 0 || b < 0 || c < 0 || d < 0) {
            return false;
        }
        return !(dp[a][b][c][d]);
    }
}
