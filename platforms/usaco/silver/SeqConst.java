import java.io.*;
import java.util.*;

public class SeqConst {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            helper(br, out);
        }
        out.flush();
    }

    public static void helper(BufferedReader br, PrintWriter out) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        long m = Long.parseLong(st.nextToken());
        long k = Long.parseLong(st.nextToken());
        ArrayList<Long> ans = new ArrayList<>();
        long sum = 0;
        for (long i = 0; i <= 4; i++) {
            long val = (1L << (1L << i)) - 1;
            if (((k >> i) & 1) == 1) {
                ans.add(val);
                sum += val;
            }
        }
        m -= sum;
        if (m == 1) {
            m -= ans.get(0);
            ans.set(0, ans.get(0) * 2);
        } else if ((m & 1) == 1) {
            ans.add(1L);
            ans.add(2L);
            m -= 3;
        }
        if (m >= 0) {
            ans.add(m / 2);
            ans.add(m / 2);
            out.println(ans.size());
            Collections.sort(ans);
            for (long val : ans) out.print(val + " ");
            out.println();
        } else {
            out.println(-1);
        }
    }

}

