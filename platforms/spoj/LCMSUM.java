import java.io.*;
import java.util.*;

public class LCMSUM {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());
        int max = 1000000;
        int[] phi = new int[max + 1];
        long[] ans = new long[max + 1];
        long[] tmp = new long[max + 1];
        for (int i = 1; i <= max; i++) {
            phi[i] = i;
        }
        for (int i = 2; i <= max; i++) {
            if (phi[i] == i) {
                for (int j = i; j <= max; j += i) {
                    phi[j] -= phi[j] / i;
                }
            }
        }

        for (int i = 1; i <= max; i++) {
            for (int j = i; j <= max; j += i) {
                tmp[j] += (long) i * phi[i];
            }
        }

        ans[1] = 1;
        for (int n = 2; n <= max; n++) {
            ans[n] = (long) n * (tmp[n] + 1) / 2;
        }

        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < t; tc++) {
            int n = Integer.parseInt(br.readLine());
            sb.append(ans[n]).append('\n');
        }
        System.out.print(sb.toString());
        br.close();
    }
}

