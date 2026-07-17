import java.io.*;
import java.util.*;

public class CTRICK {
    public static int[] bit;
    public static int n, pw;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        in.nextToken();
        int t = (int) in.nval;
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            in.nextToken();
            n = (int) in.nval;
            bit = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                bit[i]++;
                int j = i + (i & (-i));
                if (j <= n) {
                    bit[j] += bit[i];
                }
            }
            pw = 1;
            while (pw * 2 <= n) {
                pw *= 2;
            }
            int[] ans = new int[n + 1];
            int start0 = 0;
            int m = n;
            for (int k = 1; k <= n; k++) {
                int dealtRank0 = (start0 + k) % m;
                int idx = helper(dealtRank0 + 1);
                ans[idx] = k;
                update(idx, -1);
                start0 = dealtRank0;
                m--;
            }
            for (int i = 1; i <= n; i++) {
                sb.append(ans[i]);
                sb.append(i == n ? '\n' : ' ');
            }
        }
        System.out.print(sb);
        br.close();
    }

    public static void update(int i, int v) {
        for (; i <= n; i += i & (-i)) {
            bit[i] += v;
        }
    }

    public static int helper(int k) {
        int pos = 0;
        for (int p = pw; p > 0; p >>= 1) {
            if (pos + p <= n && bit[pos + p] < k) {
                pos += p;
                k -= bit[pos];
            }
        }
        return pos + 1;
    }

}
