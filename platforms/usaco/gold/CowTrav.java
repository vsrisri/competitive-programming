import java.util.*;
import java.io.*;

public class CowTrav {
    public static int[] par, tp;
    public static long[] count;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        int[] best = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            best[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        int m = Integer.parseInt(br.readLine().trim());
        int[] cow = new int[m];
        int[] pty = new int[m];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            cow[i] = Integer.parseInt(st.nextToken()) - 1;
            pty[i] = charHelper(st.nextToken().charAt(0));
        }

        par = new int[n];
        tp = new int[n];
        count = new long[3];
        Arrays.fill(par, -1);
        Arrays.fill(tp, -1);
        Set<Integer> roots = new HashSet<>();
        TreeMap<Integer, Integer>[] pts = new TreeMap[n];
        for (int i = 0; i < n; i++) {
            pts[i] = new TreeMap<>();
        }

        for (int i = 0; i < m; i++) {
            roots.add(cow[i]);
            pts[cow[i]].put(i, pty[i]);
        }

        for (int i = 0; i < n; i++) {
            if (roots.contains(i)) {
                helper(i, pts[i].lastEntry().getValue());
            } else {
                join(best[i], i);
            }
        }

        long[][] ans = new long[m][3];
        for (int i = m - 1; i >= 0; i--) {
            ans[i][0] = count[0];
            ans[i][1] = count[1];
            ans[i][2] = count[2];
            int c = cow[i];
            Map.Entry<Integer, Integer> prev = pts[c].lowerEntry(i);
            if (prev == null) {
                join(best[c], c);
            } else {
                helper(c, prev.getValue());
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            sb.append(ans[i][0]).append(' ').append(ans[i][1]).append(' ').append(ans[i][2]).append('\n');
        }
        System.out.print(sb);
        br.close();
    }

    public static int find(int x) {
        return par[x] < 0 ? x : (par[x] = find(par[x]));
    }

    public static void helper(int x, int tt) {
        x = find(x);
        if (tp[x] == tt) {
            return;
        }
        if (tp[x] != -1) {
            count[tp[x]] += par[x];
        }
        tp[x] = tt;
        if (tt != -1) {
            count[tt] -= par[x];
        }
    }

    public static void join(int px, int ch) {
        px = find(px);
        ch = find(ch);
        if (px == ch) {
            helper(px, -1);
            return;
        }
        helper(ch, tp[px]);
        if (par[px] > par[ch]) {
            int temp = px; 
            px = ch; 
            ch = temp;
        }
        par[px] += par[ch];
        par[ch] = px;
    }

    public static int charHelper(char c) {
        if (c == 'C') {
            return 0;
        }
        if (c == 'O') {
            return 1;
        }
        return 2;
    }
}
