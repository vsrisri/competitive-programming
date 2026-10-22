import java.io.*;
import java.util.*;

public class NewBarns {
    public static int LOG = 17;
    public static int[][] binArr = new int[LOG][100003];
    public static int[] depth = new int[100003];

    public static void main(String[] args) throws Exception {
        StreamTokenizer st = new StreamTokenizer(new BufferedReader(new FileReader("newbarn.in")));
        st.nextToken();
        int q = (int) st.nval;
        int[] comp = new int[q + 1];
        int[] endA = new int[q + 1];
        int[] endB = new int[q + 1];
        int[] dArr = new int[q + 1];
        int count = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            st.nextToken();
            String type = st.sval;
            st.nextToken();
            int x = (int) st.nval;
            if (type.equals("B")) {
                count++;
                int v = count;
                if (x == -1) {
                    depth[v] = 0;
                    for (int k = 0; k < LOG; k++) {
                        binArr[k][v] = v;
                    }

                    comp[v] = v;
                    endA[v] = v;
                    endB[v] = v;
                    dArr[v] = 0;
                } else {
                    depth[v] = depth[x] + 1;
                    binArr[0][v] = x;
                    for (int k = 1; k < LOG; k++) {
                        binArr[k][v] = binArr[k - 1][binArr[k - 1][v]];
                    }

                    int c = comp[x];
                    comp[v] = c;
                    int da = dist(v, endA[c]);
                    int db = dist(v, endB[c]);
                    if (da >= db && da > dArr[c]) {
                        dArr[c] = da;
                        endB[c] = v;
                    } else if (db > da && db > dArr[c]) {
                        dArr[c] = db;
                        endA[c] = v;
                    }
                }
            } else {
                int c = comp[x];
                sb.append(Math.max(dist(x, endA[c]), dist(x, endB[c]))).append('\n');
            }
        }

        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("newbarn.out")));
        out.print(sb);
        out.close();
    }

    public static int helper(int a, int b) {
        if (depth[a] < depth[b]) {
            int t = a;
            a = b;
            b = t;
        }
        int diff = depth[a] - depth[b];
        for (int k = 0; k < LOG; k++) {
            if ((diff >> k & 1) == 1) {
                a = binArr[k][a];
            }
        }
        if (a == b) {
            return a;
        }

        for (int k = LOG - 1; k >= 0; k--) {
            if (binArr[k][a] != binArr[k][b]) {
                a = binArr[k][a];
                b = binArr[k][b];
            }
        }
        return binArr[0][a];
    }

    public static int dist(int a, int b) {
        return depth[a] + depth[b] - 2 * depth[helper(a, b)];
    }

}
