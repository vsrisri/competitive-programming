import java.io.*;
import java.util.*;

public class Invitations {
    public static int N;
    public static int C;
    public static int[] f;
    public static int[] p;
    public static ArrayList<Integer>[] cArr;
    public static boolean[] dec;
    public static int[] curr;
    public static int[] count;
    public static int[] point;
    public static long ans;

    public static void helper(int i) {
        while (count[i] < f[i]) {
            boolean moved = false;
            while (point[i] < cArr[i].size()) {
                int r = cArr[i].get(point[i]);
                point[i]++;
                if (dec[r]) {
                    continue;
                }

                if (curr[r] == 0) {
                    curr[r] = i;
                    count[i]++;
                    ans += r;
                    moved = true;
                    break;
                }

                if (curr[r] > i) {
                    int j = curr[r];
                    curr[r] = i;
                    count[i]++;
                    count[j]--;
                    ans += r;
                    ans -= r;
                    moved = true;
                    helper(j);
                    break;
                }
            }
            if (!moved) {
                return;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        cArr = new ArrayList[C + 1];
        f = new int[C + 1];
        p = new int[N];
        dec = new boolean[N + 1];
        curr = new int[N + 1];
        count = new int[C + 1];
        point = new int[C + 1];
        ans = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= C; i++) {
            f[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            p[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= C; i++) {
            cArr[i] = new ArrayList<>();
        }

        for (int r = 1; r <= N; r++) {
            st = new StringTokenizer(br.readLine());
            int ni = Integer.parseInt(st.nextToken());
            for (int j = 0; j < ni; j++) {
                int c = Integer.parseInt(st.nextToken());
                cArr[c].add(r);
            }
        }

        for (int i = 1; i <= C; i++) {
            Collections.sort(cArr[i]);
        }

        for (int i = 1; i <= C; i++) {
            helper(i);
        }

        StringBuilder out = new StringBuilder();
        out.append(ans).append('\n');
        for (int k = 1; k < N; k++) {
            int r = p[k - 1];
            dec[r] = true;
            if (curr[r] != 0) {
                int i = curr[r];
                curr[r] = 0;
                count[i]--;
                ans -= r;
                helper(i);
            }
            out.append(ans).append('\n');
        }

        System.out.print(out.toString());
        br.close();
    }
}

