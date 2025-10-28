import java.io.*;
import java.util.*;

public class DesignInverseProblem {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        long[][] d = new long[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                d[i][j] = Long.parseLong(st.nextToken());
            }
        }

        if (isTreeDistanceMatrix(d, n)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    private static boolean isTreeDistanceMatrix(long[][] d, int n) {
        for (int i = 0; i < n; i++) {
            if (d[i][i] != 0) {
                return false; 
            }
            for (int j = i + 1; j < n; j++) {
                if (d[i][j] != d[j][i]) {
                    return false; 
                }
                if (d[i][j] <= 0) {
                    return false; 
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                for (int k = 0; k < n; k++) {
                    if (i != k && j != k) {
                        if (d[i][j] > d[i][k] + d[k][j]) {
                            return false; 
                        }
                    }
                }
            }
        }

        return true;
    }
}

