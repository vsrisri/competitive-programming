import java.io.*;
import java.util.*;

public class CowOp {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int n = s.length();
        int[] pc = new int[n + 1];
        int[] po = new int[n + 1];
        int[] pw = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            pc[i] = pc[i - 1];
            po[i] = po[i - 1];
            pw[i] = pw[i - 1];
            char ch = s.charAt(i - 1);
            if (ch == 'C') {
                pc[i]++;
            } else if (ch == 'O') {
                po[i]++;
            } else {
                pw[i]++;
            }
        }

        int q = Integer.parseInt(br.readLine());
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < q; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int cc = pc[r] - pc[l - 1];
            int oc = po[r] - po[l - 1];
            int ow = pw[r] - pw[l - 1];
            if (((oc + ow) & 1) == 0 && ((cc + oc) & 1) == 1) {
                ans.append('Y');
            } else {
                ans.append('N');
            }
        }

        System.out.println(ans.toString());
        br.close();
    }
}

