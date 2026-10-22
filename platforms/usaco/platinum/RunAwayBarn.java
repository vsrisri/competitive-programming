import java.io.*;
import java.util.*;
public class RunAwayBarn {
    public static void main(String[] args) throws Exception {
        StreamTokenizer st = new StreamTokenizer(new BufferedReader(new FileReader("runaway.in")));
        st.resetSyntax();
        st.wordChars('0', '9');
        st.whitespaceChars(0, ' ');
        st.nextToken();
        int n = Integer.parseInt(st.sval);
        st.nextToken();
        long L = Long.parseLong(st.sval);
        int LOG = 18;
        int[][] binArr = new int[LOG][n + 1];
        int[] par = new int[n + 1];
        long[] depth = new long[n + 1];
        for (int i = 2; i <= n; i++) {
            st.nextToken();
            int p = Integer.parseInt(st.sval);
            st.nextToken();
            long l = Long.parseLong(st.sval);
            par[i] = p;
            binArr[0][i] = p;
            depth[i] = depth[p] + l;
        }

        for (int k = 1; k < LOG; k++) {
            for (int i = 1; i <= n; i++) {
                binArr[k][i] = binArr[k - 1][binArr[k - 1][i]];
            }
        }

        long[] count = new long[n + 1];
        for (int v = 1; v <= n; v++) {
            int curr = v;
            for (int k = LOG - 1; k >= 0; k--) {
                int u = binArr[k][curr];
                if (u != 0 && depth[v] - depth[u] <= L) {
                    curr = u;
                }
            }
            count[v]++;
            count[par[curr]]--;
        }

        for (int i = n; i >= 2; i--) {
            count[par[i]] += count[i];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(count[i]).append('\n');
        }

        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("runaway.out")));
        out.print(sb);
        out.close();
    }
}
