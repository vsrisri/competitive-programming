import java.util.*;
import java.io.*;

public class RTreeGen {
    public static final long MOD = 1_000_000_007L;
    public static long[] fact, invF;
    public static long power(long base, long exp) {
        long ans = 1;
        while (exp > 0) {
            if (exp % 2 == 1) {
                ans = ans * base % MOD;
            }
            base = base * base % MOD;
            exp >>= 1;
        }
        return ans;
    }

    public static long modInv(long x) {
        return power(x, MOD - 2);
    }

    public static void helper(int n) {
        fact = new long[n + 1];
        fact[0] = 1;
        for (int i = 1; i <= n; i++) {
            fact[i] = fact[i - 1] * i % MOD;
        }
        invF = new long[n + 1];
        invF[n] = modInv(fact[n]);
        for (int i = n - 1; i > 0; i--) {
            invF[i] = invF[i + 1] * (i + 1) % MOD;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        helper(500_000);
        int t = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine().trim());
            List<List<Integer>> adj = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                adj.add(new ArrayList<>());
            }
            for (int i = 0; i < n - 1; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;
                adj.get(a).add(b);
                adj.get(b).add(a);
            }

            int[] parent = new int[n];
            Arrays.fill(parent, 0);
            parent[0] = -1;
            int[] order = new int[n];
            int orderSize = 0;
            Deque<Integer> stack = new ArrayDeque<>();
            stack.push(0);
            while (!stack.isEmpty()) {
                int a = stack.pop();
                order[orderSize++] = a;
                for (int b : adj.get(a)) {
                    if (b == parent[a]) {
                        continue;
                    }
                    parent[b] = a;
                    stack.push(b);
                }
            }

            long[] sz = new long[n];
            long[] prob = new long[n];
            long ans = 0;
            for (int i = orderSize - 1; i >= 0; i--) {
                int a = order[i];
                sz[a] = 1;
                for (int b : adj.get(a)) {
                    if (b == parent[a]) {
                        continue;
                    }
                    sz[a] += sz[b];
                }
            }

            prob[0] = 1L;
            for (int i = 0; i < n; i++) {
                prob[0] = prob[0] * modInv(sz[i]) % MOD;
            }

            for (int i = 0; i < orderSize; i++) {
                int a = order[i];
                for (int b : adj.get(a)) {
                    if (b == parent[a]) {
                        continue;
                    }
                    prob[b] = prob[a] * sz[b] % MOD;
                    prob[b] = prob[b] * modInv(n - sz[b]) % MOD;
                }
            }

            for (long x : prob) {
                ans = (ans + x) % MOD;
            }
            sb.append(ans * invF[n - 1] % MOD).append('\n');
        }
        System.out.print(sb);
        br.close();
    }
}
