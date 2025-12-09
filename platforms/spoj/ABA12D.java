// Incomplete
import java.io.*;
import java.util.*;

class ABA12D {
    public static final int max = 1000000;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        int[] sum = new int[max + 1];
        int[] pref = new int[max + 1];
        boolean[] prime = new boolean[30000005];
        Arrays.fill(prime, true);
        for (int i = 1; i <= max; i++) {
            for (int j = i; j <= max; j += i) {
                sum[j] += i;
            }
        }

        prime[0] = prime[1] = false;
        for (int i = 2; i * i < prime.length; i++) {
            if (prime[i]) {
                for (int j = i * i; j < prime.length; j += i) {
                    prime[j] = false;
                }
            }
        }

        for (int i = 1; i <= max; i++) {
            pref[i] = pref[i - 1] + (prime[sum[i]] ? 1 : 0);
        }

        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            sb.append(pref[B] - pref[A - 1]).append('\n');
        }
        System.out.print(sb.toString());
        br.close();
    }
}

