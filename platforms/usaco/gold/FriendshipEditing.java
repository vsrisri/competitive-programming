import java.io.*;
import java.util.*;

public class FriendshipEditing {
    public static final int INF = (int) 1e9;
    public static int n, m;
    public static int[][] arr;
    public static int[] adj, uv, pc, ec, bc;

    public static void get(int x) {
        pc = new int[x];
        for (int cur = 0; cur < x; cur++) pc[cur] = Integer.bitCount(cur);
    }

    public static void calc(int x) {
        ec = new int[x];
        for (int cur = 0; cur < x; cur++) {
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                if ((cur & (1 << i)) != 0) {
                    for (int j = i + 1; j < n; j++) {
                        if ((cur & (1 << j)) != 0) cnt += arr[i][j];
                    }
                }
            }
            ec[cur] = cnt;
        }
    }

    public static void finds(int x) {
        bc = new int[x];
        for (int cur = 0; cur < x; cur++) {
            if (pc[cur] <= 1) bc[cur] = 0;
            else {
                int tp = pc[cur] * (pc[cur] - 1) / 2;
                int c0 = ec[cur];
                int c1 = tp - ec[cur];
                bc[cur] = Math.min(c0, c1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int x = 1 << n;
        arr = new int[n][n];
        adj = new int[n];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            arr[a][b] = 1;
            arr[b][a] = 1;
            adj[a] |= (1 << b);
            adj[b] |= (1 << a);
        }

        uv = new int[n];
        for (int i = 0; i < n; i++) uv[i] = (((1 << n) - 1) ^ (1 << i)) & ~adj[i];
        get(x);
        calc(x);
        finds(x);
        int[] dp = new int[x];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        for (int cur = 1; cur < x; cur++) {
            for (int s = cur; s != 0; s = (s - 1) & cur) {
                int r = cur ^ s;
                int itr = 0;
                int t = s;
                while (t != 0) {
                    int i = Integer.numberOfTrailingZeros(t);
                    itr += Integer.bitCount(uv[i] & r);
                    t &= (t - 1);
                }
                dp[cur] = Math.min(dp[cur], dp[r] + bc[s] + itr);
            }
        }
        System.out.println(dp[x - 1]);
    }
}

