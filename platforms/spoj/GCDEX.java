import java.io.*;
import java.util.*;

public class GCDEX {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        int max = 1000000;
        long[] phi = new long[max + 1];
        long[] ans = new long[max + 1];
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
            for (int j = 2; i * j <= max; j++) {
                ans[i * j] += phi[i] * (j - 1);
            }
        }
        for (int i = 1; i <= max; i++) {
            ans[i] += ans[i - 1];
        }

        while (true) {
            String s = br.readLine();
            if (s == null) {
                break;
            }
            s = s.trim();
            if (s.length() == 0) {
                continue;
            }
            int n = Integer.parseInt(s);
            if (n == 0) {
                break;
            }
            out.append(ans[n]).append('\n');
        }

        System.out.print(out);
        br.close();
    }
}

