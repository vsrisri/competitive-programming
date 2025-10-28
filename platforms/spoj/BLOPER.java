import java.io.*;
import java.util.*;

public class BLOPER {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        boolean[] isPlus = new boolean[N + 1];
        long sum = (long) N * (N + 1) / 2;
        long sum2 = sum - 1;
        long numer = sum2 + S - 1;
        if (N == 1) {
            if (S == 1) {
                System.out.println("1=1");
            } else {
                System.out.println("Impossible");
            }
            return;
        }

        if (numer < 0 || (numer & 1L) != 0L || numer > 2L * sum2) {
            System.out.println("Impossible");
            return;
        }

        long P = numer / 2;
        if (P < 0 || P > sum2) {
            System.out.println("Impossible");
            return;
        }

        isPlus[1] = true;
        long curr = P;
        for (int i = N; i >= 2; i--) {
            if (i <= curr) {
                isPlus[i] = true;
                curr -= i;
            }
        }

        sb.append(1);
        for (int i = 2; i <= N; i++) {
            if (isPlus[i]) {
                sb.append('+').append(i);
            } else {
                sb.append('-').append(i);
            }
        }
        sb.append('=').append(S);
        System.out.println(sb.toString());
        br.close();
    }
}

