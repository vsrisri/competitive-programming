import java.io.*;
import java.util.*;

public class SeqConstruction {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            boolean found = false;

            for (int N = 1; N <= 100; N++) {
                int[] a = new int[N];
                if (helper(M, K, N, a)) {
                    found = true;
                    System.out.println(N);
                    for (int i = 0; i < N; i++) {
                        System.out.print(a[i] + " ");
                    }
                    System.out.println();
                    break;
                }
            }

            if (!found) {
                System.out.println(-1);
            }
        }
    }

    public static boolean helper(int M, int K, int N, int[] a {
        int sum = 0;
        int xorPopcount = 0;

        for (int i = 0; i < N; i++) {
            a[i] = 1;
            sum += a[i];
            xorPopcount ^= Integer.bitCount(a[i]);
        }

        if (sum > M || xorPopcount > K) {
            return false;
        }

        for (int i = 0; i < N; i++) {
            int remaining = M - sum;
            if (remaining > 0) {
                a[i] += remaining;
                sum += remaining;
                xorPopcount ^= Integer.bitCount(a[i] - remaining);
                xorPopcount ^= Integer.bitCount(a[i]);
            }
            if (sum == M && xorPopcount == K) {
                return true;
            }
        }

        return false;
    }
}

