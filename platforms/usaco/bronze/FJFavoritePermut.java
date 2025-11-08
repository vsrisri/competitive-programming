import java.util.*;
import java.io.*;

public class FJFavoritePermut {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(reader.readLine());
        for (int tc = 0; tc < t; tc++) {
            int n = Integer.parseInt(reader.readLine());
            int[] h = new int[n - 1];
            StringTokenizer st = new StringTokenizer(reader.readLine());
            for (int idx = 0; idx < n - 1; idx++) {
                h[idx] = Integer.parseInt(st.nextToken());
            }

            helper(n, h);
        }
        reader.close();
    }

    public static void helper(int n, int[] h) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean[] in = new boolean[n + 1];
        int[] p = new int[n];
        List<Integer> notin = new ArrayList<>();

        for (int idx : h) {
            in[idx] = true;
        }

        for (int idx = 1; idx <= n; idx++) {
            if (!in[idx]) {
                notin.add(idx);
            }
        }

        int numOnes = (int) Arrays.stream(h).filter(x -> x == 1).count();
        if (notin.size() > 2 || h[n - 2] != 1 || (notin.size() == 2 && numOnes != 2)) {
            System.out.println(-1);
            return;
        }

        if (notin.size() == 1) {
            p[0] = 1;
            p[n - 1] = notin.get(0);
        } else {
            p[0] = notin.get(0);
            p[n - 1] = notin.get(1);

            if (p[0] > p[n - 1]) {
                int t = p[0];
                p[0] = p[n - 1];
                p[n - 1] = t;
            }
        }

        int lhs = 0;
        int rhs = n - 1;
        for (int idx = 0; idx < n - 1; idx++) {
            if (p[lhs] > p[rhs]) {
                lhs++;
                p[lhs] = h[idx];
            } else {
                rhs--;
                p[rhs] = h[idx];
            }
        }

        for (int idx = 0; idx < n - 1; idx++) {
            System.out.print(p[idx] + " ");
        }

        System.out.print(p[n - 1]);
        System.out.println();
    }
}

